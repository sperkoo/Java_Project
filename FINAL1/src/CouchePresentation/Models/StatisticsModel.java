package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Seance;
import CoucheMetier.POJO.Document;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsModel {
    private GestionProjet gestionProjet;

    public StatisticsModel() {
        this.gestionProjet = new GestionProjet();
    }

    public List<Projet> getAllProjects() {
        return gestionProjet.getAllProjets();
    }

    public int getTotalHoursForProject(String projectId) {
        List<Seance> seances = gestionProjet.getSeancesByProject(projectId);
        return seances.stream()
                .mapToInt(seance -> seance.getHeure_fin().getHour() - seance.getHeure_Debut().getHour())
                .sum();
    }

    public int getDocumentCountForProject(String projectId) {
        List<Document> documents = gestionProjet.getDocumentsByProject(projectId);
        return documents.size();
    }

    public Map<String, Integer> getHoursByTimeFrame(String projectId, String timeframe) {
        List<Seance> seances = gestionProjet.getSeancesByProject(projectId);
        return seances.stream()
                .collect(Collectors.groupingBy(seance -> {
                    LocalDate date = seance.getDate_seance();
                    switch (timeframe) {
                        case "week":
                            return date.getYear() + "-W" + date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                        case "month":
                            return date.getYear() + "-" + date.getMonthValue();
                        case "year":
                            return String.valueOf(date.getYear());
                        default:
                            throw new IllegalArgumentException("Invalid timeframe: " + timeframe);
                    }
                }, Collectors.summingInt(seance -> seance.getHeure_fin().getHour() - seance.getHeure_Debut().getHour())));
    }

    public Map<String, Double> getCategoryPercentage(String timeframe) {
        List<Projet> projects = gestionProjet.getAllProjets();
        int totalHours = projects.stream()
                .mapToInt(proj -> getTotalHoursForProject(proj.getId_Projet()))
                .sum();
        return projects.stream()
                .collect(Collectors.groupingBy(Projet::getCategorie,
                        Collectors.summingDouble(proj -> (double) getTotalHoursForProject(proj.getId_Projet()) / totalHours * 100)));
    }
}
