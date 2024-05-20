package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Document {
    private String nom;
    private String Id_Projet;
    private String Id_doc;
    private String Description;
    private LocalDate Date_ajout;
    private String chemin;

    public Document() {
    }

    public Document(String IdProjet,String nomdoc, String description, LocalDate date_ajout, String path) {
        Id_Projet = IdProjet;
        nom = nomdoc;
        Id_doc = IDGenerator.generateID();
        Description = description;
        Date_ajout = date_ajout;
        chemin = path;
    }

    public Document(String chemin) {
        this.chemin = chemin;
    }

    public String getNom() {
        return nom;
    }

    public String getId_Projet() {
        return Id_Projet;
    }

    public String getId_doc() {
        return Id_doc;
    }

    public void setId_doc(String id_doc) {
        Id_doc = id_doc;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public LocalDate getDate_ajout() {
        return Date_ajout;
    }

    public void setDate_ajout(LocalDate date_ajout) {
        Date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Document{" +
                "Id_doc=" + Id_doc +
                ", Description='" + Description + '\'' +
                ", Date_ajout=" + Date_ajout +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_projet", Id_Projet);
        jsonObject.put("Nom", nom);
        jsonObject.put("Id_doc", Id_doc);
        jsonObject.put("Description", Description);
        jsonObject.put("Date_ajout", Date_ajout.toString());
        jsonObject.put("Chemin", chemin.toString());
        return jsonObject;
    }

    public static Document fromJSON(JSONObject jsonObject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Id_projet = (String) jsonObject.get("Id_projet");
        String nomDoc = (String) jsonObject.get("Nom");
        //Long id = ((Long) jsonObject.get("Id_doc"));
        String description = (String) jsonObject.get("Description");
        LocalDate dateAjout = LocalDate.parse((String) jsonObject.get("Date_ajout"), formatter);
        String Chemin = (String) jsonObject.get("Chemin");
        return new Document(Id_projet, nomDoc, description, dateAjout, Chemin);
    }

    public static List<Document> fromJSONArray(JSONArray jsonArray) {
        List<Document> documents = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObj = (JSONObject) obj;
            documents.add(fromJSON(jsonObj));
        }
        return documents;
    }
}
