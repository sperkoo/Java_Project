package CoucheMetier.Gestion;

import CoucheMetier.POJO.Projet;
import CouchePersistance.DAO.ProjetDAO;

import java.util.List;
import java.util.stream.Collectors;

public class GestionProjet {
    private ProjetDAO projetDAO;

    public GestionProjet() {
        this.projetDAO = new ProjetDAO();
    }

    public void addProjet(Projet projet) {
        projetDAO.create(projet.toJSON());
    }

    public void updateProjet(Projet projet) {
        projetDAO.update(projet.getId_Projet(), projet.toJSON());
    }

    public void deleteProjet(String id) {
        projetDAO.delete(id);
    }

    public Projet getProjetById(String id) {
        return Projet.fromJSON(projetDAO.read(id));
    }

    public List<Projet> getAllProjets() {
        return Projet.fromJSONArray(projetDAO.readAll());
    }

    public List<Projet> searchProjetsByKeyword(String keyword) {
        return getAllProjets().stream()
                .filter(projet -> projet.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }
}