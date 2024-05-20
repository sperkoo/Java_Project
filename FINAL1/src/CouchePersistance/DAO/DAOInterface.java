package CouchePersistance.DAO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface DAOInterface {
    JSONArray readAll();
    JSONObject read(String id);
    void create(JSONObject newProjet);
    void update(String id, JSONObject projet);
    void delete(String id);
}
