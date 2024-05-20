package CouchePresentation.Views;

import CoucheMetier.POJO.Projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectDetailsView extends JFrame {
    private Projet project;

    public ProjectDetailsView(Projet project) {
        this.project = project;

        setTitle("Détails du Projet");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(5, 2));
        infoPanel.add(new JLabel("Catégorie:"));
        infoPanel.add(new JLabel(project.getCategorie()));
        infoPanel.add(new JLabel("Type:"));
        infoPanel.add(new JLabel(project.getType()));
        infoPanel.add(new JLabel("Date de début:"));
        infoPanel.add(new JLabel(project.getDate_debut().toString()));
        infoPanel.add(new JLabel("Date de fin:"));
        infoPanel.add(new JLabel(project.getDate_fin().toString()));
        infoPanel.add(new JLabel("Description:"));
        infoPanel.add(new JLabel(project.getDescription()));

        add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton viewTasksButton = new JButton("Voir les Tâches");
        JButton viewSessionsButton = new JButton("Voir les Séances");
        JButton viewDocumentsButton = new JButton("Voir les Documents");

        buttonPanel.add(viewTasksButton);
        buttonPanel.add(viewSessionsButton);
        buttonPanel.add(viewDocumentsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskListView(project).setVisible(true);
            }
        });

        viewSessionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SessionListView(project).setVisible(true);
            }
        });

        viewDocumentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the document view functionality here
                JOptionPane.showMessageDialog(ProjectDetailsView.this, "Afficher les documents du projet: " );
            }
        });

        setVisible(true);
    }
}
