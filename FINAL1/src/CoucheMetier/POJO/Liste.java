package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Liste {
    private String Id_liste;
    private String Nom;
    private String Description;

    public Liste() {
    }

    public Liste(String nom, String description) {
        this.Id_liste = IDGenerator.generateID();
        this.Nom = nom;
        this.Description = description;
    }

    public String getId_liste() {
        return Id_liste;
    }

    public void setId_liste(String id_liste) {
        Id_liste = id_liste;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    @Override
    public String toString() {
        return "Liste{" +
                "Id_liste='" + Id_liste + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_liste", Id_liste);
        jsonObject.put("Nom", Nom);
        jsonObject.put("Description", Description);
        return jsonObject;
    }

    public static Liste fromJSON(JSONObject jsonObject) {
        Liste liste = new Liste();
        if (jsonObject != null) {
            liste.setId_liste((String) jsonObject.get("Id_liste"));
            liste.setNom((String) jsonObject.get("Nom"));
            liste.setDescription((String) jsonObject.get("Description"));
        } else {
            System.err.println("JSON object is null");
        }
        return liste;
    }

    public static List<Liste> fromJSONArray(JSONArray jsonArray) {
        List<Liste> listes = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            listes.add(fromJSON(jsonObject));
        }
        return listes;
    }
}
