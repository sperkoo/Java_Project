package CouchePresentation.Views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;

public class StatisticsView extends JFrame {
    private JPanel chartPanel;
    private GestionProjet gestionProjet;

    public StatisticsView() {
        gestionProjet = new GestionProjet();

        setTitle("Statistiques du Projet");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setLocationRelativeTo(null);

        chartPanel = new JPanel(new GridLayout(2, 2));
        add(chartPanel, BorderLayout.CENTER);

        addDynamicCharts();

        JButton retourButton = new JButton("Retourner");
        retourButton.setBackground(new Color(8, 148, 81));
        retourButton.setForeground(Color.WHITE);
        retourButton.setFocusPainted(false);
        retourButton.setOpaque(true);
        retourButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(retourButton, BorderLayout.SOUTH);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Dashboard();
            }
        });

        setVisible(true);
    }

    private void addDynamicCharts() {
        List<Projet> projects = gestionProjet.getAllProjets();

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (Projet project : projects) {
            if (project.getCategorie() != null) {
                pieDataset.setValue(project.getCategorie(), Math.random() * 100);
            }
        }

        JFreeChart pieChart = createPieChart("Work Hour Distribution", pieDataset);
        addChart(pieChart);

        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        for (Projet project : projects) {
            if (project.getCategorie() != null && project.getDate_debut() != null) {
                barDataset.addValue(Math.random() * 100, project.getCategorie(), project.getDate_debut().toString());
            }
        }

        JFreeChart barChart = createBarChart("Work Hours per Project", "Project", "Hours", barDataset);
        addChart(barChart);

        DefaultCategoryDataset documentsDataset = new DefaultCategoryDataset();
        for (Projet project : projects) {
            if (project.getNom() != null) {
                int documentCount = gestionProjet.getDocumentCountByProject(project.getId_Projet());
                documentsDataset.addValue(documentCount, project.getNom(), "Documents");
            }
        }

        JFreeChart documentsBarChart = createBarChart("Nombre de documents par projet", "Project", "Number of Documents", documentsDataset);
        addChart(documentsBarChart);

        XYSeriesCollection timeSeriesCollection = new XYSeriesCollection();

        XYSeries weeklySeries = new XYSeries("Weekly");
        XYSeries monthlySeries = new XYSeries("Monthly");
        XYSeries yearlySeries = new XYSeries("Yearly");

        for (Projet project : projects) {
            LocalDate startDate = project.getDate_debut();
            LocalDate endDate = project.getDate_fin();
            if (startDate != null && endDate != null) {
                long weeks = ChronoUnit.WEEKS.between(startDate, endDate);
                long months = ChronoUnit.MONTHS.between(startDate, endDate);
                long years = ChronoUnit.YEARS.between(startDate, endDate);

                weeklySeries.add(weeks, Math.random() * 100);
                monthlySeries.add(months, Math.random() * 100);
                yearlySeries.add(years, Math.random() * 100);
            }
        }

        timeSeriesCollection.addSeries(weeklySeries);
        timeSeriesCollection.addSeries(monthlySeries);
        timeSeriesCollection.addSeries(yearlySeries);

        JFreeChart timeSeriesChart = createTimeSeriesChart("Nombre d’heures de travail par semaine, mois et année", "Time", "Hours", timeSeriesCollection);
        addChart(timeSeriesChart);
    }

    public void addChart(JFreeChart chart) {
        ChartPanel panel = new ChartPanel(chart);
        chartPanel.add(panel);
    }

    public JFreeChart createBarChart(String title, String categoryAxisLabel, String valueAxisLabel, DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    public JFreeChart createPieChart(String title, DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Enseignement", new Color(200, 100, 100));
        plot.setSectionPaint("Encadrement", new Color(100, 200, 100));
        return chart;
    }

    public JFreeChart createTimeSeriesChart(String title, String xAxisLabel, String yAxisLabel, XYSeriesCollection dataset) {
        return ChartFactory.createXYLineChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    public static void main(String[] args) {
        new StatisticsView();
    }
}
