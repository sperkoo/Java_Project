package CouchePresentation.Views;

import CouchePresentation.Models.ArchiveModel;
import CoucheMetier.POJO.Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArchivePage extends JFrame {
    public ArchivePage() {
        setTitle("Projets Archivés");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header panel setup
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));
        JTextField searchField = new JTextField("Search");
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Search")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        JButton returnButton = new JButton("<-Retour");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard();
            }
        });
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(searchField, BorderLayout.CENTER);
        headerPanel.add(returnButton, BorderLayout.EAST);
        headerPanel.setPreferredSize(new Dimension(700, 30));

        // Title label setup
        JLabel titleLabel = new JLabel("Projets Archivés");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Archive panel setup
        JPanel archivePanel = new JPanel();
        archivePanel.setLayout(new GridLayout(0, 1));

        List<Projet> archivedProjects = ArchiveModel.getArchivedProjects();
        for (Projet project : archivedProjects) {
            JPanel projectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            projectPanel.setPreferredSize(new Dimension(800, 40));

            JLabel projectCategoryLabel = new JLabel("Catégorie: " + project.getCategorie());
            JLabel projectStartDateLabel = new JLabel("Date de début: " + project.getDate_debut());
            JLabel projectEndDateLabel = new JLabel("Date de fin: " + project.getDate_fin());

            JButton detailsButton = new JButton("Voir les détails");
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProjectDetailsView(project);
                }
            });

            projectPanel.add(projectCategoryLabel);
            projectPanel.add(projectStartDateLabel);
            projectPanel.add(projectEndDateLabel);
            projectPanel.add(detailsButton);

            archivePanel.add(projectPanel);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(titleLabel, BorderLayout.CENTER);
        add(new JScrollPane(archivePanel), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArchivePage();
            }
        });
    }
}
