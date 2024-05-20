package CouchePersistance.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class ProjetDAO implements DAOInterface{

    private String filename = "projets.json";

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
        JSONArray projets = readAll();
        for (Object o : projets) {
            JSONObject projet = (JSONObject) o;
            if (id.equals(projet.get("Id_Projet"))) {
                return projet;
            }
        }
        return null;
    }


    public void create(JSONObject newProjet) {
        JSONArray projets = readAll();
        projets.add(newProjet);
        try (FileWriter file = new FileWriter(filename)) {
            file.write(projets.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, JSONObject projet) {
        JSONArray projets = readAll();
        for (int i = 0; i < projets.size(); i++) {
            JSONObject p = (JSONObject) projets.get(i);
            if (id.equals(p.get("Id_Projet"))) {
                projets.set(i, projet);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(projets.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        JSONArray projets = readAll();
        for (Object o : projets) {
            JSONObject projet = (JSONObject) o;
            if (id.equals(projet.get("Id_Projet"))) {
                projets.remove(projet);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(projets.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
