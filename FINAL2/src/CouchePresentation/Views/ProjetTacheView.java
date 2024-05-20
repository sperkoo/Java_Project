package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionTache;
import CoucheMetier.POJO.Tache;
import CouchePresentation.Controllers.ModifierTacheController;
import CouchePresentation.Models.TacheModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProjetTacheView extends JFrame {
        private JTable tasksTable;
        private TacheModel tableModel;
        private GestionTache gestionTache;
        private JComboBox<String> categoryDropdown;
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(40);
        JButton backButton = new JButton("Retour");
        private String selectedTaskId;
        private String IdProjet;


        public ProjetTacheView(String IdProjet) {
            setTitle("Mes Tâches");
            setSize(1000, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            setLocationRelativeTo(null);
            setVisible(true);
            this.IdProjet = IdProjet;

            JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));


            searchPanel.add(logoLabel);
            searchPanel.add(searchField);
            searchPanel.add(backButton);
            topPanel.add(searchPanel, BorderLayout.CENTER);

            add(topPanel, BorderLayout.NORTH);

            JPanel centerPanel = new JPanel(new BorderLayout());
            JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            categoryDropdown = new JComboBox<>(new String[]{"Catégorie", "Enseignement", "Encadrement"});

            JButton applyFilterButton = new JButton("Appliquer le filtre");

            filtersPanel.add(categoryDropdown);
            filtersPanel.add(applyFilterButton);

            String[] columns = {"Catégorie", "Date de debut", "Date de fin","Description"};
            gestionTache = new GestionTache();
            List<Tache> data = gestionTache.getAllTaches();
            List<Tache> filteredData = new ArrayList<>();
            for (Tache tache : data) {
                System.out.println("Tache IdProjet: " + tache.getIdProjet());
                System.out.println("IdProjet passé en paramètre: " + IdProjet);
                if (tache.getIdProjet() != null && tache.getIdProjet().equals(IdProjet)) {
                    filteredData.add(tache);
                }
            }

            tableModel = new TacheModel(filteredData) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tasksTable = new JTable(tableModel);
            tasksTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int clickedRow = tasksTable.rowAtPoint(e.getPoint());
                        if (clickedRow != -1) {
                            selectedTaskId = (String) tableModel.getValueAt(clickedRow, 4);
                            System.out.println("ID de la tâche double-cliqué : " + selectedTaskId);
                            openAfficherTask(IdProjet);
                        }
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(tasksTable);

            centerPanel.add(filtersPanel, BorderLayout.NORTH);
            centerPanel.add(scrollPane, BorderLayout.CENTER);

            add(centerPanel, BorderLayout.CENTER);


            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new ProjetView();
                }
            });

            categoryDropdown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filterTableData();
                }
            });
            applyFilterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filterTableData();
                }
            });

            searchField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchTasksByDescription();
                }
            });

            setVisible(true);
        }

    private void updateTableModel(List<Tache> data) {
        tableModel = new TacheModel(data);
        tasksTable.setModel(tableModel);
    }
        public void openAfficherTask(String idProjet){
            dispose();
            ModifierTacheView view = new ModifierTacheView();
            TacheModel model = new TacheModel();
            ModifierTacheController controller = new ModifierTacheController(view, model, idProjet, selectedTaskId);
            view.setVisible(true);
        }

        public void retour(ActionListener listener){
        backButton.addActionListener(listener);
    }

    private void filterTableData() {
        String selectedCategory = (String) categoryDropdown.getSelectedItem();
        List<Tache> allTasks = gestionTache.getAllTaches();
        List<Tache> filtredTasks = new ArrayList<>();
        for (Tache tache : allTasks) {
            boolean matchesCategory = selectedCategory.equals("Catégorie") || tache.getCategorie().equals(selectedCategory);

            if (matchesCategory && tache.getIdProjet().equals(IdProjet)) {
                filtredTasks.add(tache);
            }
        }

        updateTableModel(filtredTasks);
    }

    private void searchTasksByDescription() {
        String searchKeyword = searchField.getText().trim().toLowerCase();
        List<Tache> filteredTasks = new ArrayList<>();
        List<Tache> allTasks = gestionTache.getAllTaches();
        for (Tache tache : allTasks) {
            if (tache.getDescription().toLowerCase().contains(searchKeyword) && tache.getIdProjet().equals(IdProjet)) {
                filteredTasks.add(tache);
            }
        }
        updateTableModel(filteredTasks);
    }


}
