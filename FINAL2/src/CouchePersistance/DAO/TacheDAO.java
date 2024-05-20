package CouchePersistance.DAO;

import CouchePersistance.DAO.DAOInterface;
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
            if (id.equals(tache.get("Id_tache"))) {
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
            if (id.equals(p.get("Id_tache"))) {
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
        for (Object o : taches) {
            JSONObject tache = (JSONObject) o;
            if (id.equals(tache.get("Id_tache"))) {
                taches.remove(tache);
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
}
