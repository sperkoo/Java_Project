package CouchePresentation.Models;

import CoucheMetier.POJO.Projet;
import java.util.ArrayList;
import java.util.List;

public class ArchiveModel {
    private static List<Projet> archivedProjects = new ArrayList<>();

    public static void addProjectToArchive(Projet project) {
        archivedProjects.add(project);
    }

    public static List<Projet> getArchivedProjects() {
        return archivedProjects;
    }
}
