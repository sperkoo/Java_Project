package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Seance;
import CouchePresentation.Controllers.*;
import CouchePresentation.Models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectPage extends JFrame {
    private JTable projectsTable;
    private ProjectModel tableModel;
    private GestionProjet gestionProjet;
    private JPopupMenu popupMenu;
    private JComboBox<String> categoryDropdown;
    private JComboBox<String> typeDropdown;
    private String selectedProjectId;
    private NewProjectForm form;
    private JTextField searchField;

    private JButton backButton;
    private JButton newProjectButton;
    private JButton applyFilterButton;

    public ProjectPage() {
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
        backButton = new JButton("Retour");
        searchPanel.add(logoLabel);
        searchPanel.add(searchField);
        searchPanel.add(backButton);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        categoryDropdown = new JComboBox<>(new String[]{"Catégorie", "Enseignement", "Encadrement"});
        typeDropdown = new JComboBox<>(new String[]{"Type", "Thèse", "PFE", "PFA", "Cours", "Examen"});

        applyFilterButton = new JButton("Appliquer le filtre");

        filtersPanel.add(categoryDropdown);
        filtersPanel.add(typeDropdown);
        filtersPanel.add(applyFilterButton);

        String[] columns = {"Catégorie", "Type", "Date de debut", "Date de fin", "Description"};
        gestionProjet = new GestionProjet();
        List<Projet> data = gestionProjet.getAllProjets();
        tableModel = new ProjectModel(data) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        popupMenu = new JPopupMenu();
        JMenuItem afficher = new JMenuItem("Afficher/Modifier");
        JMenuItem tache = new JMenuItem("Ajouter une tâche");
        JMenuItem seance = new JMenuItem("Ajouter une séance");
        JMenuItem document = new JMenuItem("Ajouter un document");
        JMenuItem archiver = new JMenuItem("Archiver");
        JMenuItem cloner = new JMenuItem("Cloner");

        cloner.addActionListener(e -> cloneSelectedProject());

        popupMenu.add(afficher);
        popupMenu.addSeparator();
        popupMenu.add(tache);
        popupMenu.addSeparator();
        popupMenu.add(seance);
        popupMenu.addSeparator();
        popupMenu.add(document);
        popupMenu.addSeparator();
        popupMenu.add(archiver);
        popupMenu.add(cloner);

        projectsTable = new JTable(tableModel);
        projectsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int clickedRow = projectsTable.rowAtPoint(e.getPoint());
                    if (clickedRow != -1) {
                        selectedProjectId = (String) tableModel.getValueAt(clickedRow, 5);
                        popupMenu.show(projectsTable, e.getX(), e.getY());
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
        newProjectButton = new JButton("+ Nouveau projet");
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
                new Dashboard();
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
                openProject();
            }
        });

        categoryDropdown.addActionListener(new FilterActionListener());
        typeDropdown.addActionListener(new FilterActionListener());

        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProjectsByDescription();
            }
        });

        archiver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                archiveSelectedProject();
            }
        });

        // Assuming `form` is properly initialized somewhere in your code
        if (form != null) {
            form.enregistrer(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String date = form.getStartDateField().getText();
                    LocalDate startDate = LocalDate.parse(date);
                    LocalTime startTime = LocalTime.parse(form.getStartDateField().getText());
                    LocalTime endTime = LocalTime.parse(form.getEndDateField().getText());
                    String name = form.getName();

                    Seance seance = new Seance(selectedProjectId, form.getDescriptionTextArea().getText(), startDate, startTime, endTime, name);
                    // Assuming ProjectModel has an addSession method
                    tableModel.addSession(seance);

                    JOptionPane.showMessageDialog(form, "Données de la séance sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    form.dispose();
                    new ProjectPage();
                }
            });
        }

        setVisible(true);
    }

    private void archiveSelectedProject() {
        int selectedRow = projectsTable.getSelectedRow();
        if (selectedRow >= 0) {
            Projet selectedProject = tableModel.getProjectAt(selectedRow);
            ArchiveModel.addProjectToArchive(selectedProject); // Add to archive
            gestionProjet.removeProject(selectedProject); // Remove from active projects
            updateTableModel(gestionProjet.getAllProjets()); // Refresh table model
            JOptionPane.showMessageDialog(this, "Projet archivé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun projet sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cloneSelectedProject() {
        int selectedRow = projectsTable.getSelectedRow();
        if (selectedRow >= 0) {
            Projet selectedProject = tableModel.getProjectAt(selectedRow);
            Projet clonedProject = gestionProjet.cloneProject(selectedProject);
            updateTableModel(gestionProjet.getAllProjets());
        } else {
            JOptionPane.showMessageDialog(this, "Aucun projet sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTableModel(List<Projet> data) {
        tableModel = new ProjectModel(data);
        projectsTable.setModel(tableModel);
    }

    public String getSelectedProjectIdFromTable() {
        int selectedRow = projectsTable.getSelectedRow();
        if (selectedRow != -1) {
            return (String) tableModel.getValueAt(selectedRow, 5);
        } else {
            return null;
        }
    }

    private void openProject() {
        dispose();
        ProjectModel prjModel = new ProjectModel();
        UpdateProjectView prjView = new UpdateProjectView();
        UpdateProjectController controller = new UpdateProjectController(prjView, prjModel, getSelectedProjectIdFromTable());
        prjView.setVisible(true);
    }

    private void openNewDocForm() {
        dispose();
        DocModel docModel = new DocModel();
        NewDocForm docForm = new NewDocForm();
        AddDocFormController controller = new AddDocFormController(docForm, docModel, getSelectedProjectIdFromTable());
        docForm.setVisible(true);
    }

    private void openNewSessionForm() {
        dispose();
        SessionModel sessionModel = new SessionModel();
        NewSessionForm sessionForm = new NewSessionForm();
        AddSessionFormController controller = new AddSessionFormController(sessionForm, sessionModel, getSelectedProjectIdFromTable());
        sessionForm.setVisible(true);
    }

    private void openNewTaskForm() {
        dispose();
        TacheModel tacheModel = new TacheModel();
        NewTaskForm taskForm = new NewTaskForm();
        AddTaskFormController controller = new AddTaskFormController(taskForm, tacheModel, getSelectedProjectIdFromTable());
        taskForm.setVisible(true);
    }

    private void openNewProjectForm() {
        dispose();
        ProjectModel projectModel = new ProjectModel();
        NewProjectForm projectForm = new NewProjectForm();
        NewProjectFormController controller = new NewProjectFormController(projectForm, projectModel);
        projectForm.setVisible(true);
    }

    public String getSelectedProjectId() {
        return selectedProjectId;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new ProjectPage().setVisible(true);
        });
    }

    public AbstractButton getBackButton() {
        return backButton;
    }

    public AbstractButton getNewProjectButton() {
        return newProjectButton;
    }

    public AbstractButton getApplyFilterButton() {
        return applyFilterButton;
    }

    private class FilterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            filterTableData();
        }
    }

    private void filterTableData() {
        String selectedCategory = (String) categoryDropdown.getSelectedItem();
        String selectedType = (String) typeDropdown.getSelectedItem();

        List<Projet> filteredProjects = gestionProjet.getAllProjets().stream()
                .filter(projet -> (selectedCategory.equals("Catégorie") || projet.getCategorie().equals(selectedCategory)) &&
                        (selectedType.equals("Type") || projet.getType().equals(selectedType)))
                .collect(Collectors.toList());

        updateTableModel(filteredProjects);
    }

    private void searchProjectsByDescription() {
        String searchKeyword = searchField.getText().trim().toLowerCase();
        List<Projet> filteredProjects = gestionProjet.getAllProjets().stream()
                .filter(projet -> projet.getDescription().toLowerCase().contains(searchKeyword))
                .collect(Collectors.toList());

        updateTableModel(filteredProjects);
    }
}
