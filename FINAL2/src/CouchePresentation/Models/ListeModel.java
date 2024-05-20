package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionListe;
import CoucheMetier.POJO.Liste;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListeModel extends AbstractTableModel {
    private GestionListe gestionListe;
	private String[] columns = {"Nom", "Description"};
    private List<Liste> listes;

    public ListeModel(List<Liste> listes) {
        this.listes = listes;
    }
    
    public ListeModel() {
    	gestionListe = new GestionListe();
    }
    
    public void setListes(List<Liste> listes) {
        this.listes = listes;
        fireTableDataChanged();
    }
    
    public void addListe(Liste liste){
    	gestionListe.addListe(liste);
    }

    public void updateListe(Liste liste){
        gestionListe.updateListe(liste);
    }

    public Liste readListe(String id){
        return gestionListe.getListeById(id);
    }
    
    @Override
    public int getRowCount() {
        return listes.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Liste liste = listes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return liste.getNom();
            case 1:
                return liste.getDescription();
            case 2:
                return liste.getId();
            default:
                return null;
        }
    }
    
}
