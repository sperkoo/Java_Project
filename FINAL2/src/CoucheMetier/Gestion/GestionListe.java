package CoucheMetier.Gestion;

import CouchePersistance.DAO.ListDAO;
import CoucheMetier.POJO.Liste;
import java.util.List;
import java.util.stream.Collectors;

public class GestionListe {
    private ListDAO listeDAO;

    public GestionListe() {
        this.listeDAO = new ListDAO();
    }

    public void addListe(Liste liste) {
        listeDAO.create(liste.toJSON());
    }

    public void updateListe(Liste liste) {
        listeDAO.update(liste.getId(), liste.toJSON());
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

    public List<Liste> searchListesByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return getAllListes();
        }
        return getAllListes().stream()
                .filter(liste -> liste.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}