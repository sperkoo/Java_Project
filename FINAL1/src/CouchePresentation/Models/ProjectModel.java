package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Seance;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProjectModel extends AbstractTableModel {
    private GestionProjet gestionProjet;
    private String[] columnNames = {"Catégorie", "Type", "Date début", "Date fin", "Description"};
    private List<Projet> projets;
    private List<Seance> seances; // List to hold sessions

    public ProjectModel(List<Projet> projets) {
        this.projets = projets;
        this.seances = new ArrayList<>(); // Initialize the seances list
    }

    public ProjectModel() {
        gestionProjet = new GestionProjet();
        this.projets = new ArrayList<>(); // Initialize the projets list
        this.seances = new ArrayList<>(); // Initialize the seances list
    }

    public void addProject(Projet prj) {
        gestionProjet.addProjet(prj);
        this.projets.add(prj); // Add project to the list
        fireTableDataChanged(); // Notify the table model that data has changed
    }

    public void updateProject(Projet prj) {
        gestionProjet.updateProjet(prj);
        int index = getProjectIndexById(prj.getId_Projet());
        if (index >= 0) {
            this.projets.set(index, prj); // Update project in the list
            fireTableDataChanged(); // Notify the table model that data has changed
        }
    }

    public Projet readProject(String id) {
        return gestionProjet.getProjetById(id);
    }

    public void removeProject(Projet project) {
        gestionProjet.deleteProjet(project.getId_Projet());
        this.projets.remove(project);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return projets.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projet project = projets.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return project.getCategorie();
            case 1:
                return project.getType();
            case 2:
                return project.getDate_debut();
            case 3:
                return project.getDate_fin();
            case 4:
                return project.getDescription();
            case 5:
                return project.getId_Projet();
            default:
                return null;
        }
    }

    public Projet getProjectAt(int selectedRow) {
        return projets.get(selectedRow);
    }

    public void updateData(List<Projet> data) {
        this.projets = data;
        fireTableDataChanged();
    }

    public void addSession(Seance seance) {
        this.seances.add(seance); // Add the session to the list
        fireTableDataChanged(); // Notify the table model that data has changed
    }

    private int getProjectIndexById(String id) {
        for (int i = 0; i < projets.size(); i++) {
            if (projets.get(i).getId_Projet().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
