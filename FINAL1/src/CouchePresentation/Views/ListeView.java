package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionListe;
import CoucheMetier.POJO.Liste;
import CouchePresentation.Controllers.AddListeController;
import CouchePresentation.Controllers.AddTacheController;
import CouchePresentation.Controllers.ModifierListeController;
import CouchePresentation.Models.ListeModel;
import CouchePresentation.Models.TacheModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class ListeView extends JFrame {
    private JTable listsTable;
    private ListeModel tableModel;
    private GestionListe gestionListe;
    private JPopupMenu popupMenu;
    private String selectedListId;
    private JTextField searchField;
    private JButton backButton;
    private JButton newListeButton;

    public ListeView() {
        setTitle("Mes Listes To-Do");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(40);
        JButton searchButton = new JButton("Chercher");
        backButton = new JButton("Retour");
        searchPanel.add(logoLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        gestionListe = new GestionListe();
        List<Liste> data = gestionListe.getAllListes();
        tableModel = new ListeModel(data) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create the popup menu
        popupMenu = new JPopupMenu();
        JMenuItem afficher = new JMenuItem("Afficher");
        JMenuItem modifier = new JMenuItem("Modifier");
        JMenuItem tache = new JMenuItem("Ajouter une tâche");
        JMenuItem supprimer = new JMenuItem("Supprimer");
        popupMenu.add(afficher);
        popupMenu.addSeparator();
        popupMenu.add(modifier);
        popupMenu.addSeparator();
        popupMenu.add(tache);
        popupMenu.addSeparator();
        popupMenu.add(supprimer);

        listsTable = new JTable(tableModel);
        listsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int clickedRow = listsTable.rowAtPoint(e.getPoint());
                    if (clickedRow != -1) {
                        popupMenu.show(listsTable, e.getX(), e.getY());
                        selectedListId = (String) tableModel.getValueAt(clickedRow, 2);
                        System.out.println("ID de la liste double-cliquée : " + selectedListId);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(listsTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newListeButton = new JButton("+ Nouvelle liste");
        newListeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewListeForm();
            }
        });
        bottomPanel.add(newListeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchListesByNameOrDescription();
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchListesByNameOrDescription();
            }
        });

        setVisible(true);
    }

    public String getSelectedListeIdFromTable() {
        int selectedRow = listsTable.getSelectedRow();
        if (selectedRow != -1) {
            String listeId = (String) tableModel.getValueAt(selectedRow, 2);
            return listeId;
        } else {
            return null;
        }
    }

    public void openModifierListe() {
        this.dispose();
        ListeModel listeModel = new ListeModel();
        ModifierListeView modifierListeView = new ModifierListeView();
        ModifierListeController controller = new ModifierListeController(modifierListeView, listeModel, getSelectedListeIdFromTable());
        modifierListeView.setVisible(true);
    }

    private void deleteList(String listId) {
        gestionListe.deleteListe(listId);
        JOptionPane.showMessageDialog(this, "Liste supprimée avec succès.");
        refreshTable();
    }

    private void searchListesByNameOrDescription() {
        String keyword = searchField.getText().trim().toLowerCase();
        List<Liste> filteredData = gestionListe.getAllListes().stream()
                .filter(liste -> liste.getNom().toLowerCase().contains(keyword) || liste.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        tableModel.setListes(filteredData);
        tableModel.fireTableDataChanged();
    }

    private void openNewListeForm() {
        dispose();
        ListeModel listeModel = new ListeModel();
        AddListeView addListeView = new AddListeView();
        AddListeController controller = new AddListeController(addListeView, listeModel);
        addListeView.setVisible(true);
    }

    private void openNewTaskForm() {
        String listId = getSelectedListeIdFromTable();
        if (listId != null) {
            dispose();
            TacheModel taskModel = new TacheModel();
            AddTacheView taskForm = new AddTacheView(true);
            AddTacheController controller = new AddTacheController(taskForm, taskModel, null, listId);
            taskForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une liste pour ajouter une tâche.");
        }
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getNewListeButton() {
        return newListeButton;
    }

    public void refreshTable() {
        List<Liste> data = gestionListe.getAllListes();
        tableModel.setListes(data);
        tableModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        ListeView listeView = new ListeView();
        listeView.setLocationRelativeTo(null);
        listeView.setVisible(true);
    }
}
