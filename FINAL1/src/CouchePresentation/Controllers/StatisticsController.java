package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Models.StatisticsModel;
import CouchePresentation.Views.StatisticsView;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;

public class StatisticsController {
    private StatisticsModel model;
    private StatisticsView view;

    public StatisticsController(StatisticsModel model, StatisticsView view) {
        this.model = model;
        this.view = view;
        updateView();
    }

    private void updateView() {
        DefaultCategoryDataset projectHoursDataset = new DefaultCategoryDataset();
        for (Projet project : model.getAllProjects()) {
            if (project.getId_Projet() != null && project.getNom() != null) {
                projectHoursDataset.addValue(model.getTotalHoursForProject(project.getId_Projet()), project.getNom(), "Heures de travail");
            }
        }
        view.addChart(view.createBarChart("Nombre d'heures de travail par projet", "Projets", "Heures", projectHoursDataset));

        DefaultCategoryDataset projectDocumentsDataset = new DefaultCategoryDataset();
        for (Projet project : model.getAllProjects()) {
            if (project.getId_Projet() != null && project.getType() != null) {
                projectDocumentsDataset.addValue(model.getDocumentCountForProject(project.getId_Projet()), project.getType(), "Documents");
            }
        }
        view.addChart(view.createBarChart("Nombre de documents par projet", "Projets", "Documents", projectDocumentsDataset));

        DefaultCategoryDataset weeklyHoursDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset monthlyHoursDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset yearlyHoursDataset = new DefaultCategoryDataset();
        for (Projet project : model.getAllProjects()) {
            if (project.getId_Projet() != null && project.getNom() != null) {
                Map<String, Integer> weeklyHours = model.getHoursByTimeFrame(project.getId_Projet(), "week");
                weeklyHours.forEach((week, hours) -> weeklyHoursDataset.addValue(hours, project.getNom(), week));
                Map<String, Integer> monthlyHours = model.getHoursByTimeFrame(project.getId_Projet(), "month");
                monthlyHours.forEach((month, hours) -> monthlyHoursDataset.addValue(hours, project.getNom(), month));
                Map<String, Integer> yearlyHours = model.getHoursByTimeFrame(project.getId_Projet(), "year");
                yearlyHours.forEach((year, hours) -> yearlyHoursDataset.addValue(hours, project.getNom(), year));
            }
        }
        view.addChart(view.createBarChart("Nombre d'heures de travail par semaine", "Semaine", "Heures", weeklyHoursDataset));
        view.addChart(view.createBarChart("Nombre d'heures de travail par mois", "Mois", "Heures", monthlyHoursDataset));
        view.addChart(view.createBarChart("Nombre d'heures de travail par année", "Année", "Heures", yearlyHoursDataset));

        DefaultPieDataset categoryPercentageDataset = new DefaultPieDataset();
        Map<String, Double> categoryPercentage = model.getCategoryPercentage("year");
        categoryPercentage.forEach((category, percentage) -> {
            if (category != null) {
                categoryPercentageDataset.setValue(category, percentage);
            }
        });
        view.addChart(view.createPieChart("Pourcentage d'heures de travail par catégorie", categoryPercentageDataset));
    }

    public static void main(String[] args) {
        StatisticsModel model = new StatisticsModel();
        StatisticsView view = new StatisticsView();
        new StatisticsController(model, view);
        view.setVisible(true);
    }
}
