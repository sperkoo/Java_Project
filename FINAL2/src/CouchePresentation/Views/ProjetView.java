package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;
import CouchePresentation.Controllers.*;
import CouchePresentation.Models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetView extends JFrame {
    private JTable projectsTable;
    private ProjetModel tableModel;
    private GestionProjet gestionProjet;
    private JPopupMenu popupMenu;
    private JComboBox<String> categoryDropdown;
    private JComboBox<String> typeDropdown;
    private JTextField searchField;
    private String selectedProjectId;
    private JButton backButton;
    private JButton newProjectButton;

    public ProjetView() {
        setTitle("Mes Projets");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(40);
        JButton searchButton = new JButton("Chercher");
        JButton backButton = new JButton("Retour");
        searchPanel.add(logoLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(backButton);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        categoryDropdown = new JComboBox<>(new String[]{"Catégorie", "Enseignement", "Encadrement"});
        typeDropdown = new JComboBox<>(new String[]{"Type", "Thèse", "PFE", "PFA", "Cours", "Examen"});


        filtersPanel.add(categoryDropdown);
        filtersPanel.add(typeDropdown);

        gestionProjet = new GestionProjet();
        List<Projet> data = gestionProjet.getAllProjets();
        tableModel = new ProjetModel(data) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Créer le menu contextuel
        popupMenu = new JPopupMenu();
        JMenuItem afficher = new JMenuItem("Afficher/Modifier");
        JMenuItem tache = new JMenuItem("Ajouter une tâche");
        JMenuItem seance = new JMenuItem("Ajouter une séance");
        JMenuItem document = new JMenuItem("Ajouter un document");
        JMenuItem archiver = new JMenuItem("Archiver");
        popupMenu.add(afficher);
        popupMenu.addSeparator();
        popupMenu.add(tache);
        popupMenu.addSeparator();
        popupMenu.add(seance);
        popupMenu.addSeparator();
        popupMenu.add(document);
        popupMenu.addSeparator();
        popupMenu.add(archiver);

        projectsTable = new JTable(tableModel);
        // Ajouter un écouteur de souris au tableau pour détecter le double clic
        projectsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int clickedRow = projectsTable.rowAtPoint(e.getPoint());
                    if (clickedRow != -1) {
                        popupMenu.show(projectsTable, e.getX(), e.getY());
                        selectedProjectId = (String) tableModel.getValueAt(clickedRow, 6);
                        System.out.println("ID du projet double-cliqué : " + selectedProjectId);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(projectsTable);

        centerPanel.add(filtersPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton newProjectButton = new JButton("+ Nouveau projet");
        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewProjectForm();
            }
        });
        bottomPanel.add(newProjectButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeView();
            }
        });

        tache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewTaskForm();
            }
        });

        seance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewSessionForm();
            }
        });

        document.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewDocForm();
            }
        });

        afficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAfficherProjet();
            }
        });

        categoryDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
        typeDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });

        setVisible(true);
    }

    private void updateTableModel(List<Projet> data) {
        tableModel = new ProjetModel(data);
        projectsTable.setModel(tableModel);
    }

    public String getSelectedProjectIdFromTable(){
        int selectedRow = projectsTable.getSelectedRow();
        if (selectedRow != -1) {
            String projectId = (String) tableModel.getValueAt(selectedRow, 6);
            return projectId;
        } else {
            return null;
        }
    }

    private void openAfficherProjet(){
        dispose();
        ProjetModel prjModel = new ProjetModel();
        ModifierProjetView prjView = new ModifierProjetView();
        ModifierProjetController controller = new ModifierProjetController(prjView, prjModel, getSelectedProjectIdFromTable());
        prjView.setVisible(true);
    }

    private void openNewDocForm(){
        dispose();
        DocModel docModel = new DocModel();
        AddDocumentView docForm = new AddDocumentView();
        AddDocumentController controller = new AddDocumentController(docForm, docModel , getSelectedProjectIdFromTable());
        docForm.setVisible(true);
    }

    private void openNewSessionForm(){
        dispose();
        SeanceModel sessionModel = new SeanceModel();
        AddSeanceView sessionForm = new AddSeanceView();
        AddSeanceController controller = new AddSeanceController(sessionForm, sessionModel, getSelectedProjectIdFromTable());
        sessionForm.setVisible(true);
    }

    private void openNewTaskForm(){
        dispose();
        TacheModel taskModel = new TacheModel();
        AddTacheView taskForm = new AddTacheView(false);
        AddTacheController controller = new AddTacheController(taskForm, taskModel, getSelectedProjectIdFromTable(), null);
        taskForm.setVisible(true);
    }

    private void openNewProjectForm() {
        dispose();
        ProjetModel projectModel = new ProjetModel();
        AddProjetView projectForm = new AddProjetView();
        AddProjetController controller = new AddProjetController(projectForm,projectModel);
        projectForm.setVisible(true);
    }

    public String getSelectedProjectId() {
        return selectedProjectId;
    }

    public static void main(String[] args) {
        ProjetView projectPage = new ProjetView();
        projectPage.setLocationRelativeTo(null);
        projectPage.setVisible(true);
    }

    public AbstractButton getBackButton() {
        return backButton;
    }

    public AbstractButton getNewProjectButton() {
        return newProjectButton;
    }

    public JComboBox<String> getCategoryDropdown() {
        return categoryDropdown;
    }

    private void filterTableData() {
        String selectedCategory = (String) categoryDropdown.getSelectedItem();
        String selectedType = (String) typeDropdown.getSelectedItem();
        String keyword = searchField.getText().trim().toLowerCase();

        List<Projet> allProjects = gestionProjet.getAllProjets();
        List<Projet> filteredProjects = new ArrayList<>();
        for (Projet projet : allProjects) {
            boolean matchesCategory = selectedCategory.equals("Catégorie") || projet.getCategorie().equals(selectedCategory);
            boolean matchesType = selectedType.equals("Type") || projet.getType().equals(selectedType);
            boolean matchesKeyword = keyword.isEmpty() || projet.getDescription().toLowerCase().contains(keyword);

            if (matchesCategory && matchesType && matchesKeyword) {
                filteredProjects.add(projet);
            }
        }
        updateTableModel(filteredProjects);
    }
}