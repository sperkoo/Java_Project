package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tache {
    private String IdProjet;
    private String IdListe;

    private String Id_tache;
    private String Categorie;
    private String Description;
    private LocalDate date_debut, date_fin;

    public Tache() {
    }

    public Tache(String IdProjet, String categorie, String description, LocalDate date_debut, LocalDate date_fin) {
        this.IdProjet = IdProjet;
        this.Id_tache = IDGenerator.generateID();
        this.Categorie = categorie;
        this.Description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Tache(String IdProjet, String IdListe, String categorie, String description, LocalDate date_debut, LocalDate date_fin) {
        this.IdProjet = IdProjet;
        this.IdListe = IdListe;
        this.Id_tache = IDGenerator.generateID();
        this.Categorie = categorie;
        this.Description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public static Tache fromJSON(JSONObject jsonObject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id_tache = (String) jsonObject.get("Id_tache");
        String id_projet = (String) jsonObject.get("Id_projet");
        String id_liste = (String) jsonObject.get("Id_Liste");
        String categorie = (String) jsonObject.get("Categorie");
        String description = (String) jsonObject.get("Description");
        LocalDate date_debut = LocalDate.parse((String) jsonObject.get("Date_debut"), formatter);
        LocalDate date_fin = LocalDate.parse((String) jsonObject.get("Date_fin"), formatter);
        Tache tache = new Tache(id_projet, id_liste, categorie, description, date_debut, date_fin);
        tache.setId_tache(id_tache);
        return tache;
    }

    public static List<Tache> fromJSONArray(JSONArray jsonArray) {
        List<Tache> taches = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            taches.add(fromJSON(jsonObject));
        }
        return taches;
    }

    public String getIdProjet() {
        return IdProjet;
    }

    public void setIdProjet(String idProjet) {
        this.IdProjet = idProjet;
    }

    public String getId_tache() {
        return Id_tache;
    }

    public void setId_tache(String id_tache) {
        Id_tache = id_tache;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "Id_tache=" + Id_tache +
                ", Categorie='" + Categorie + '\'' +
                ", Description='" + Description + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_projet", IdProjet);
        jsonObject.put("Id_liste", IdListe);
        jsonObject.put("Id_tache", Id_tache);
        jsonObject.put("Categorie", Categorie);
        jsonObject.put("Description", Description);
        jsonObject.put("Date_debut", date_debut.toString());
        jsonObject.put("Date_fin", date_fin.toString());
        return jsonObject;
    }
}
