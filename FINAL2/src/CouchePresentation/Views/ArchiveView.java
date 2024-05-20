package CouchePresentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArchiveView extends JFrame {

    public ArchiveView() {
        setTitle("Projets Archivés");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //CENTRALISER LA PAGE SUR L'ECRAN
        setLocationRelativeTo(null);
        setVisible(true);

        // Header panel with logo, search bar, and return button
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
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(searchField, BorderLayout.CENTER);
        headerPanel.add(returnButton, BorderLayout.EAST);
        headerPanel.setPreferredSize(new Dimension(700, 30)); // Adjust header width

        //ACTION DU RETOUR
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomeView();
            }
        });

        JLabel titleLabel = new JLabel("Projets Archivés");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel archivePanel = new JPanel();
        archivePanel.setLayout(new GridLayout(0, 1));

        for (int i = 1; i <= 10; i++) {
            JPanel projectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            projectPanel.setPreferredSize(new Dimension(800, 40)); 
            projectPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            JLabel projectLabel = new JLabel("Projet Archivé " + i);
            JButton detailsButton = new JButton("Voir les détails");
            detailsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Détails du projet archivé: " + projectLabel.getText());
                }
            });

            projectPanel.add(projectLabel);
            projectPanel.add(detailsButton);

            archivePanel.add(projectPanel);
        }

        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(titleLabel, BorderLayout.CENTER);
        add(new JScrollPane(archivePanel), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArchiveView();
            }
        });
    }
}
