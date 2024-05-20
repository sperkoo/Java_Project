package CouchePersistance.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TacheDAO implements DAOInterface {

    private String filename = "taches.json";

    public JSONArray readAll() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            return (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public JSONObject read(String id) {
        JSONArray taches = readAll();
        for (Object o : taches) {
            JSONObject tache = (JSONObject) o;
            if (id.equals(tache.get("ID_Tache"))) {
                return tache;
            }
        }
        return null;
    }

    public void create(JSONObject newTache) {
        JSONArray taches = readAll();
        taches.add(newTache);
        try (FileWriter file = new FileWriter(filename)) {
            file.write(taches.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, JSONObject tache) {
        JSONArray taches = readAll();
        for (int i = 0; i < taches.size(); i++) {
            JSONObject p = (JSONObject) taches.get(i);
            if (id.equals(p.get("ID_Tache"))) {
                taches.set(i, tache);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(taches.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        JSONArray taches = readAll();
        for (int i = 0; i < taches.size(); i++) {
            JSONObject tache = (JSONObject) taches.get(i);
            if (id.equals(tache.get("ID_Tache"))) {
                taches.remove(i);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(taches.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray readAllByProject(String projectId) {
        JSONArray taches = readAll();
        JSONArray projectTaches = new JSONArray();
        for (Object o : taches) {
            JSONObject tache = (JSONObject) o;
            if (projectId.equals(tache.get("Id_projet"))) {
                projectTaches.add(tache);
            }
        }
        return projectTaches;
    }
}
