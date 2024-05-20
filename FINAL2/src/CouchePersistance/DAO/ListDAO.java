package CouchePersistance.DAO;

import CouchePersistance.DAO.DAOInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import CoucheMetier.POJO.Liste;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ListDAO implements DAOInterface {
    private String filename = "listes.json";

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
        JSONArray listes = readAll();
        for (Object o : listes) {
            JSONObject liste = (JSONObject) o;
            if (id.equals(liste.get("Id"))) {
                return liste;
            }
        }
        return null;
    }

    public void create(JSONObject newListe) {
        JSONArray listes = readAll();
        listes.add(newListe);
        try (FileWriter file = new FileWriter(filename)) {
            file.write(listes.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, JSONObject liste) {
        JSONArray listes = readAll();
        for (int i = 0; i < listes.size(); i++) {
            JSONObject l = (JSONObject) listes.get(i);
            if (id.equals(l.get("Id"))) {
                listes.set(i, liste);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(listes.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        JSONArray listes = readAll();
        for (Object o : listes) {
            JSONObject liste = (JSONObject) o;
            if (id.equals(liste.get("Id"))) {
            	listes.remove(liste);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(listes.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Liste> searchListes(String keyword) {
        List<Liste> allListes = readAll();
        return allListes.stream()
                        .filter(liste -> liste.getNom().contains(keyword) || liste.getDescription().contains(keyword))
                        .collect(Collectors.toList());
    }
}
