package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionTache;
import CoucheMetier.POJO.Tache;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TacheModel extends AbstractTableModel {
    private static List<Tache> taches = new ArrayList<>();
    private GestionTache gestionTache;
    private String[] columnNames = {"Catégorie", "Date début", "Date fin", "Description"};

    public TacheModel() {
        gestionTache = new GestionTache();
    }

    public TacheModel(List<Tache> taches) {
        this.taches = taches;
    }

//    public void addTask(Tache tache){
//        gestionTache.addTache(tache);
//    }

    public static void addTask(Tache task) {
        taches.add(task);
    }

    public void updateTask(Tache tache){
        gestionTache.updateTache(tache);
    }

    public Tache readTask(String id){
        return gestionTache.getTacheById(id);
    }

    public int getRowCount() {
        return taches.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Tache tache = taches.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tache.getCategorie();
            case 1:
                return tache.getDate_debut();
            case 2:
                return tache.getDate_fin();
            case 3:
                return tache.getDescription();
            case 4:
                return tache.getId_tache();
            default:
                return null;
        }
    }

    public static List<Tache> getTasksForProject(String projectId) {
        return taches.stream()
                .filter(task -> task.getIdProjet().equals(projectId))
                .collect(Collectors.toList());
    }
}
