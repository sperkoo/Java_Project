package CoucheMetier.Gestion;

import CoucheMetier.POJO.Seance;
import CouchePersistance.DAO.SeanceDAO;

import java.util.List;

public class GestionSeance {
    private SeanceDAO seanceDAO;

    public GestionSeance() {
        this.seanceDAO = new SeanceDAO();
    }

    public void addSeance(Seance seance) {
        seanceDAO.create(seance.toJSON());
    }

    public void updateSeance(Seance seance) {
        seanceDAO.update(seance.getId_seance(), seance.toJSON());
    }

    public void deleteSeance(String id) {
        seanceDAO.delete(id);
    }

    public Seance getSeanceById(String id) {
        return Seance.fromJSON(seanceDAO.read(id));
    }

    public List<Seance> getAllSeances()  {
        return Seance.fromJSONArray(seanceDAO.readAll());
    }
}
