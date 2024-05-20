package CouchePresentation.Views;

import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Seance;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SessionListView extends JFrame {

    public SessionListView(Projet project) {
        setTitle("Liste des Séances pour ce Projet: ");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel sessionPanel = new JPanel();
        sessionPanel.setLayout(new GridLayout(0, 1));

        List<Seance> sessions = project.getSessions();
        for (Seance session : sessions) {
            JPanel sessionDetailsPanel = new JPanel(new GridLayout(0, 1));
            sessionDetailsPanel.setBorder(BorderFactory.createTitledBorder("Séance: " + session.getDescription()));
            sessionDetailsPanel.add(new JLabel("Date: " + session.getDate_seance()));
            sessionDetailsPanel.add(new JLabel("Heure début: " + session.getHeure_Debut()));
            sessionDetailsPanel.add(new JLabel("Heure de fin: " + session.getHeure_fin()));
            sessionDetailsPanel.add(new JLabel("Note: " + session.getNote()));
            sessionPanel.add(sessionDetailsPanel);
        }

        add(new JScrollPane(sessionPanel), BorderLayout.CENTER);

        setVisible(true);
    }
}
