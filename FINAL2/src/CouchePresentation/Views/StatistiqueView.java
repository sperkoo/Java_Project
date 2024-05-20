package CouchePresentation.Views;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StatistiqueView extends JFrame {
    private ChartPanel hoursChartPanel;
    private ChartPanel documentsChartPanel;
    private ChartPanel categoryPieChartPanel;

    public StatistiqueView() {
        setTitle("Project Statistics Dashboard");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Add header
        JPanel headerPanel = createHeader();
        add(headerPanel, BorderLayout.NORTH);

        // Initialize statistics panels
        initStatisticsPanels();

        setVisible(true);
    }

    private void initStatisticsPanels() {
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        hoursChartPanel = createBarChartPanel("Work Hours per Project", "Project", "Hours");
        documentsChartPanel = createBarChartPanel("Documents per Project", "Project", "Documents");
        topPanel.add(hoursChartPanel);
        topPanel.add(documentsChartPanel);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        categoryPieChartPanel = createPieChartPanel();
        bottomPanel.add(categoryPieChartPanel);
        bottomPanel.add(createCategoryBarChartPanel());

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Updates the total work hours chart with new data
    public void updateTotalWorkHours(double hours) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(hours, "Total Hours", "Current");
        JFreeChart chart = ChartFactory.createBarChart(
                "Total Work Hours", "Category", "Hours", dataset);
        hoursChartPanel.setChart(chart);
    }

    // Updates the total documents chart with new data
    public void updateTotalDocuments(long count) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(count, "Total Documents", "Current");
        JFreeChart chart = ChartFactory.createBarChart(
                "Total Documents", "Category", "Documents", dataset);
        documentsChartPanel.setChart(chart);
    }

    // Updates the work hours by category pie chart with new data
    public void updateWorkHoursByCategory(Map<String, Long> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Long> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Work Hour Percentage by Category", dataset, true, true, false);
        categoryPieChartPanel.setChart(chart);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel logoPanel = new JPanel(); // Panel to hold logo image
        JPanel searchPanel = new JPanel(); // Panel to hold search bar and return button

        // Logo
        ImageIcon logoIcon = new ImageIcon("src/CouchePresentation/Views/Images/logooo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.add(logoLabel);

        // Search bar
        JTextField searchField = new JTextField(20);
        JButton returnButton = new JButton("Retour");
        searchPanel.add(searchField);
        searchPanel.add(returnButton);

        // Action listener for return button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the statistics dashboard frame
                new HomeView(); // Open the dashboard page
            }
        });

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private ChartPanel createBarChartPanel(String title, String categoryAxisLabel, String valueAxisLabel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLabel, dataset);
        return new ChartPanel(barChart);
    }

    private ChartPanel createPieChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        JFreeChart pieChart = ChartFactory.createPieChart("Work Hour Percentage by Category", dataset, true, true, false);
        return new ChartPanel(pieChart);
    }

    private ChartPanel createCategoryBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart barChart = ChartFactory.createBarChart("Work Hours by Timeframe", "Category", "Hours", dataset);
        return new ChartPanel(barChart);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StatistiqueView());
    }

    public JPanel getStatisticsPanel() {
        JPanel statisticsPanel = new JPanel(new BorderLayout());

        // Create a JPanel to hold the charts
        JPanel chartsPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // Adjust the layout as needed
        chartsPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add the chart panels to the chartsPanel
        chartsPanel.add(hoursChartPanel);
        chartsPanel.add(documentsChartPanel);
        chartsPanel.add(categoryPieChartPanel);

        // Add the chartsPanel to the statisticsPanel
        statisticsPanel.add(chartsPanel, BorderLayout.CENTER);

        return statisticsPanel;
    }
}
