package CouchePresentation.Models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import CoucheMetier.Gestion.GestionTache;
import CoucheMetier.POJO.Tache;

public class TacheModel extends AbstractTableModel{
	private GestionTache gestionTache;
    private String[] columnNames = {"Catégorie", "Date début", "Date fin", "Description"};
    private List<Tache> taches;

    public TacheModel() {
        gestionTache = new GestionTache();
    }

    public TacheModel(List<Tache> taches) {
        this.taches = taches;
    }

    public void addTask(Tache tache){
        gestionTache.addTache(tache);
    }

    public void updateTask(Tache tache){
        gestionTache.updateTache(tache);
    }

    public Tache readTask(String id){
        return gestionTache.getTacheById(id);
    }


    @Override
    public int getRowCount() {
        return taches.size();
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
}
