package CoucheMetier.Gestion;

import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Seance;
import CoucheMetier.POJO.Document;
import CoucheMetier.POJO.Tache;
import CouchePersistance.DAO.ProjetDAO;
import CouchePersistance.DAO.SeanceDAO;
import CouchePersistance.DAO.DocumentDAO;
import CouchePersistance.DAO.TacheDAO;

import java.util.List;

public class GestionProjet {
    private ProjetDAO projetDAO;
    private SeanceDAO seanceDAO;
    private DocumentDAO documentDAO;
    private TacheDAO tacheDAO;

    public GestionProjet() {
        this.projetDAO = new ProjetDAO();
        this.seanceDAO = new SeanceDAO();
        this.documentDAO = new DocumentDAO();
        this.tacheDAO = new TacheDAO();
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

    public Projet cloneProject(Projet selectedProject) {
        Projet clonedProject = new Projet(selectedProject);
        clonedProject.setId_Projet(String.valueOf(0)); // Ensure the cloned project has a new ID
        addProjet(clonedProject);
        return clonedProject;
    }

    public List<Seance> getSeancesByProject(String projectId) {
        return Seance.fromJSONArray(seanceDAO.readAllByProject(projectId));
    }

    public List<Document> getDocumentsByProject(String projectId) {
        return Document.fromJSONArray(documentDAO.readAllByProject(projectId));
    }

    public List<Tache> getTachesByProject(String projectId) {
        return Tache.fromJSONArray(tacheDAO.readAllByProject(projectId));
    }

    public void addSeance(Seance seance) {
        seanceDAO.create(seance.toJSON());
    }

    public void addDocument(Document document) {
        documentDAO.create(document.toJSON());
    }

    public void removeProject(Projet project) {
        projetDAO.delete(project.getId_Projet()); // Remove the project using DAO
    }

    public void addTache(Tache tache) {
        tacheDAO.create(tache.toJSON());
    }

    public int getDocumentCountByProject(String idProjet) {
        List<Document> documents = getDocumentsByProject(idProjet);
        return documents.size();
    }
}
