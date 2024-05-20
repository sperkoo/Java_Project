package CoucheMetier.Gestion;


import CoucheMetier.POJO.Tache;
import CouchePersistance.DAO.TacheDAO;

import java.util.List;

public class GestionTache {
    private TacheDAO tacheDAO;

    public GestionTache() {
        this.tacheDAO = new TacheDAO();
    }

    public void addTache(Tache tache) {
        tacheDAO.create(tache.toJSON());
    }

    public void updateTache(Tache tache) {
        tacheDAO.update(tache.getId_tache(), tache.toJSON());
    }

    public void deleteTache(String id) {
        tacheDAO.delete(id);
    }

    public Tache getTacheById(String id) {
        return Tache.fromJSON(tacheDAO.read(id));
    }

    public List<Tache> getAllTaches() {
        return Tache.fromJSONArray(tacheDAO.readAll());
    }
}