package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;

public class Dashboard extends JFrame {
    private JLabel backgroundLabel;
    private JTextField searchField;
    private JButton quitButton;

    public Dashboard() {
        setTitle("Dashboard");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // We will use absolute positioning
        setLocationRelativeTo(null);

        // Background image
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); // Fill the entire frame
        add(backgroundLabel);

        // Logo
        ImageIcon logoImage = new ImageIcon("src/CouchePresentation/Views/Images/logooo.png");
        JLabel logoLabel = new JLabel(logoImage);
        logoLabel.setBounds(10, 10, logoImage.getIconWidth(), logoImage.getIconHeight());
        backgroundLabel.add(logoLabel);

        // Search bar
        searchField = new JTextField("Search");
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
        backgroundLabel.add(searchField);

        // Quit button
        quitButton = new JButton("Quit");
        quitButton.setBounds(getWidth() - 100, 10, 80, 30); // Positioned in top-right corner
        quitButton.setBackground(new Color(8, 148, 81)); // Set background color
        quitButton.setForeground(Color.BLACK); // Set text color
        quitButton.setFocusPainted(false); // Remove the border when focused
        quitButton.setOpaque(true); // Make the button opaque
        quitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        backgroundLabel.add(quitButton);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dashboard frame
                new LoginPage(); // Open the login page
            }
        });

        // Four buttons
        String[] buttonLabels = {"Mes projets", "Mes listes", "Statistiques", "Archive"};
        JButton[] buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setForeground(Color.WHITE); // Set text color
            buttons[i].setFont(new Font("Arial", Font.BOLD, 14)); // Set font
            buttons[i].setFocusPainted(false); // Remove the border when focused
            buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
            buttons[i].setOpaque(true); // Make the button opaque
            buttons[i].setBackground(new Color(8, 148, 81)); // Set background color
            backgroundLabel.add(buttons[i]);
        }

        // Action listener for "Mes projets" button
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dashboard frame
                new ProjectPage(); // Open the project management page
            }
        });

        // Action listener for "Mes listes" button
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dashboard frame
                new ListeView(); // Open the to-do list page
            }
        });

        // Action listener for "Statistiques" button
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dashboard frame
                new StatisticsView(); // Open the statistics page
            }
        });

        // Action listener for "Archive" button
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dashboard frame
                new ArchivePage(); // Open the archive page
            }
        });

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // Resize and reposition the background label
                backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

                // Resize and reposition the search bar
                int searchBarWidth = 150;
                int searchBarX = (getWidth() - searchBarWidth - 100) / 2;
                searchField.setBounds(searchBarX, 10, searchBarWidth, 30);

                // Resize and reposition the quit button
                quitButton.setBounds(getWidth() - 100, 10, 80, 30);

                // Resize and reposition the buttons
                int buttonWidth = getWidth() / buttonLabels.length;
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setBounds(i * buttonWidth, 120, buttonWidth, 40);
                }
            }
        });

        // GestionProjet instance
        GestionProjet gestionProjet = new GestionProjet();
        ArrayList<Projet> projects = (ArrayList<Projet>) gestionProjet.getAllProjets();

        // My Daily Tasks Panel
        JPanel taskPanel = new JPanel();
        taskPanel.setBounds(10, 170, getWidth() - 20, getHeight() / 2 - 180);
        taskPanel.setBorder(new TitledBorder("Les dernier 5 projets"));
        taskPanel.setLayout(new BorderLayout());
        JTextArea taskArea = new JTextArea();
        loadTasks(taskArea, projects);
        taskPanel.add(new JScrollPane(taskArea), BorderLayout.CENTER);
        backgroundLabel.add(taskPanel);

        // Statistics Panel
        JPanel statisticsPanel = new JPanel();
        statisticsPanel.setBounds(10, taskPanel.getBounds().y + taskPanel.getBounds().height + 10, getWidth() - 20, getHeight() / 2 - 180);
        statisticsPanel.setBorder(new TitledBorder("Work Hour Percentage by Category"));
        statisticsPanel.setLayout(new BorderLayout());
        ChartPanel chartPanel = createStatisticsChart();
        statisticsPanel.add(chartPanel, BorderLayout.CENTER);
        backgroundLabel.add(statisticsPanel);

        // Layout adjustments...
        setVisible(true);
    }

    private void loadTasks(JTextArea taskArea, ArrayList<Projet> projects) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Projet project : projects) {
            if (count++ == 5) break; // Limit to the first 5 projects
            sb.append("Categorie: ").append(project.getCategorie())
                    .append(", Date fin: ").append(project.getDate_fin()).append("\n");
        }
        taskArea.setText(sb.toString());
    }

    private ChartPanel createStatisticsChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Enseignement", 40);
        dataset.setValue("Encadrement", 30);
        dataset.setValue("Autre", 30);

        JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(getWidth() - 40, 200)); // Adjust size to make chart smaller
        return chartPanel;
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
