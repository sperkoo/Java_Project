package CouchePersistance.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class DocumentDAO implements DAOInterface {

    private String filename = "documents.json";

    public JSONArray readAll() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            return (JSONArray) parser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
        return new JSONArray();
    }

    public JSONObject read(String id) {
        JSONArray documents = readAll();
        for (Object o : documents) {
            JSONObject document = (JSONObject) o;
            if (id.equals(document.get("ID_Document"))) {
                return document;
            }
        }
        return null;
    }

    public void create(JSONObject newDocument) {
        JSONArray documents = readAll();
        documents.add(newDocument);
        try (FileWriter file = new FileWriter(filename)) {
            file.write(documents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, JSONObject document) {
        JSONArray documents = readAll();
        for (int i = 0; i < documents.size(); i++) {
            JSONObject d = (JSONObject) documents.get(i);
            if (id.equals(d.get("ID_Document"))) {
                documents.set(i, document);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(documents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        JSONArray documents = readAll();
        for (Object o : documents) {
            JSONObject document = (JSONObject) o;
            if (id.equals(document.get("ID_Document"))) {
                documents.remove(document);
                break;
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(documents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray readAllByProject(String projectId) {
        JSONArray allDocuments = readAll();
        JSONArray projectDocuments = new JSONArray();
        for (Object obj : allDocuments) {
            JSONObject document = (JSONObject) obj;
            if (projectId.equals(document.get("ID_Projet"))) {
                projectDocuments.add(document);
            }
        }
        return projectDocuments;
    }
}
