package CouchePresentation.Models;
// ccc
import CoucheMetier.Gestion.GestionProjet;
import CoucheMetier.POJO.Projet;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProjetModel extends AbstractTableModel {
    private GestionProjet gestionProjet;
    private String[] columnNames = {"Nom", "Catégorie", "Type", "Date début", "Date fin", "Description"};
    private List<Projet> projets;

    public ProjetModel(List<Projet> projets) {
        this.projets = projets;
    }

    public ProjetModel() {
        gestionProjet = new GestionProjet();
    }

    public void addProject(Projet prj){
        gestionProjet.addProjet(prj);
    }
    
    public void updateProject(Projet prj) {
        gestionProjet.updateProjet(prj);
    }

    public Projet readProject(String id) {
        return gestionProjet.getProjetById(id);
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

    public  Object getValueAt(int rowIndex, int columnIndex) {
        Projet project = projets.get(rowIndex);
        switch (columnIndex) {
        	case 0:
        		return project.getNom();
            case 1:
                return project.getCategorie();
            case 2:
                return project.getType();
            case 3:
                return project.getDate_debut();
            case 4:
                return project.getDate_fin();
            case 5:
                return project.getDescription();
            case 6:
                return project.getId_Projet();
            default:
                return null;
        }
    }
}
