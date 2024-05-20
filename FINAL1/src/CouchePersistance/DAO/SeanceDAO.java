package CouchePersistance.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SeanceDAO implements DAOInterface {

    private String filename = "seances.json";

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
        JSONArray seances = readAll();
        for (Object obj : seances) {
            JSONObject seance = (JSONObject) obj;
            if (id.equals(seance.get("ID_Seance"))) {
                return seance;
            }
        }
        return null;
    }

    public void create(JSONObject newSeance) {
        JSONArray seances = readAll();
        seances.add(newSeance);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(seances.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, JSONObject seance) {
        JSONArray seances = readAll();
        for (int i = 0; i < seances.size(); i++) {
            JSONObject s = (JSONObject) seances.get(i);
            if (id.equals(s.get("ID_Seance"))) {
                seances.set(i, seance);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(seances.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        JSONArray seances = readAll();
        for (Object o : seances) {
            JSONObject seance = (JSONObject) o;
            if (id.equals(seance.get("ID_Seance"))) {
                seances.remove(seance);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(seances.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray readAllByProject(String projectId) {
        JSONArray seances = readAll();
        JSONArray result = new JSONArray();
        for (Object o : seances) {
            JSONObject seance = (JSONObject) o;
            if (projectId.equals(seance.get("ID_Projet"))) {
                result.add(seance);
            }
        }
        return result;
    }
}
