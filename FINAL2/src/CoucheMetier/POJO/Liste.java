package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Liste {
    private String Id;
    private String Nom;
    private String Description;

    public Liste(String nom, String description) {
    	Id = IDGenerator.generateID();
    	Nom = nom;
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
    	Id = id;
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
        return Nom;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", Id);
        jsonObject.put("Nom", Nom);
        jsonObject.put("Description", Description);
        return jsonObject;
    }

    public static Liste fromJSON(JSONObject jsonObject) {
        String id = (String) jsonObject.get("Id");
        String nom = (String) jsonObject.get("Nom");
        String description = (String) jsonObject.get("Description");
        Liste liste = new Liste(nom, description);
        liste.setId(id);
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
