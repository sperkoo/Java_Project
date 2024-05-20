package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionSeance;
import CoucheMetier.POJO.Seance;
import CoucheMetier.POJO.Tache;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SessionModel extends AbstractTableModel {
    private GestionSeance gestionSeance;
    private String[] columnNames = {"Description", "Date", "Heure de début","Heure de fin","Note"};
    private List<Seance> seances;

    public SessionModel(){
        gestionSeance = new GestionSeance();
    }

    public SessionModel(List<Seance> seances) {
        this.seances = seances;
    }

    public void addSession(Seance seance){
        gestionSeance.addSeance(seance);
    }

    public void updateSession(Seance seance){
        gestionSeance.updateSeance(seance);
    }

    public Seance readSession(String id){
        return gestionSeance.getSeanceById(id);
    }

    public void deleteSession(String id){
        gestionSeance.deleteSeance(id);
    }

    @Override
    public int getRowCount() {
        return seances.size();
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
        Seance seance = seances.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return seance.getDescription();
            case 1:
                return seance.getDate_seance();
            case 2:
                return seance.getHeure_Debut();
            case 3:
                return seance.getHeure_fin();
            case 4:
                return seance.getNote();
            case 5:
                return seance.getId_seance();
            default:
                return null;
        }
    }
}
