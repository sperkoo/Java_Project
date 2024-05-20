package CoucheMetier.Gestion;

import CouchePersistance.DAO.ListDAO;
import CoucheMetier.POJO.Liste;
import java.util.List;

public class GestionListe {
    private ListDAO listeDAO;

    public GestionListe() {
        this.listeDAO = new ListDAO();
    }

    public void addListe(Liste liste) {
        listeDAO.create(liste.toJSON());
    }

    public void updateListe(Liste liste) {
        listeDAO.update(liste.getId_liste(), liste.toJSON());
    }

    public void deleteListe(String id) {
        listeDAO.delete(id);
    }

    public Liste getListeById(String id) {
        return Liste.fromJSON(listeDAO.read(id));
    }

    public List<Liste> getAllListes() {
        return Liste.fromJSONArray(listeDAO.readAll());
    }
}