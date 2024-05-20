package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Views.StatistiqueView;
import java.util.List;

public class StatistiqueController {
    private List<Projet> projects;
    private StatistiqueView view;

    public StatistiqueController(List<Projet> projects, StatistiqueView view) {
        this.projects = projects;
        this.view = view;
    }

    // Calculate and update document statistics
    private void updateDocumentStatistics() {
        long totalDocuments = projects.stream().count();
        view.updateTotalDocuments(totalDocuments);
    }
}
