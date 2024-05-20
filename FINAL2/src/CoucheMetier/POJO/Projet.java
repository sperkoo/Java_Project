package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Projet {
    private String Id_Projet;
    private String Nom;
    private String Categorie;
    private String Type;
    private LocalDate Date_debut, Date_fin;
    private String Description;


    public Projet(String Nom, String categorie, String type, LocalDate date_debut, LocalDate date_fin, String description) {
        this.Nom = Nom;
        this.Id_Projet = IDGenerator.generateID();
        this.Categorie = categorie;
        this.Type = type;
        this.Date_debut = date_debut;
        this.Date_fin = date_fin;
        this.Description = description;
    }

    public static Projet fromJSON(JSONObject jsonObject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id = (String) jsonObject.get("Id_Projet");
        String nom = (String) jsonObject.get("Nom");
        String categorie = (String) jsonObject.get("Categorie");
        String type = (String) jsonObject.get("Type");
        LocalDate date_debut = LocalDate.parse((String) jsonObject.get("Date_debut"), formatter);
        LocalDate date_fin = LocalDate.parse((String) jsonObject.get("Date_fin"), formatter);
        String description = (String) jsonObject.get("Description");
        Projet projet = new Projet(nom ,categorie, type, date_debut, date_fin, description);
        projet.setId_Projet(id);
        return projet;
    }


    public static List<Projet> fromJSONArray(JSONArray jsonArray) {
        List<Projet> projets = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            projets.add(fromJSON(jsonObject));
        }
        return projets;
    }

    public String getId_Projet() {
        return Id_Projet;
    }

    public void setId_Projet(String id_Projet) {
        Id_Projet = id_Projet;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public LocalDate getDate_debut() {
        return Date_debut;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDate_debut(LocalDate date_debut) {
        Date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        Date_fin = date_fin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_Projet", Id_Projet);
        jsonObject.put("Nom", Nom);
        jsonObject.put("Categorie", Categorie);
        jsonObject.put("Type", Type);
        jsonObject.put("Date_debut", Date_debut.toString());
        jsonObject.put("Date_fin", Date_fin.toString());
        jsonObject.put("Description", Description);
        return jsonObject;
    }

    @Override
    public String toString() {
        return Nom;
    }

}