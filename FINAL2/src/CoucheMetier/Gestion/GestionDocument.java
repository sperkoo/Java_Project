package CoucheMetier.Gestion;
//fefe
import CoucheMetier.POJO.Document;
import CouchePersistance.DAO.DocumentDAO;

import java.util.List;

public class GestionDocument {

    private DocumentDAO documentDAO;

    public GestionDocument() {
        this.documentDAO = new DocumentDAO();
    }

    public void addDocument(Document document) {
        documentDAO.create(document.toJSON());
    }

//    public void updateDocument(Document document) {
//        documentDAO.update(document.getId_doc(), document.toJSON());
//    }

    public void deleteDocument(String id) {
        documentDAO.delete(id);
    }

    public Document getDocumentById(String id) {
        return Document.fromJSON(documentDAO.read(id));
    }

    public List<Document> getAllDocuments() {
        return Document.fromJSONArray(documentDAO.readAll());
    }
}