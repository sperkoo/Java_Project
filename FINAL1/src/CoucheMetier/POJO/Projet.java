package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    private String Nom;
    private String Id_Projet;
    private String Categorie;
    private String Type;
    private LocalDate Date_debut, Date_fin;
    private String Description;
    private List<Seance> sessions;

    private boolean archived;

    public Projet(String categorie, String type, LocalDate date_debut, LocalDate date_fin, String description) {
        this.Id_Projet = IDGenerator.generateID();
        this.Categorie = categorie;
        this.Type = type;
        this.Date_debut = date_debut;
        this.Date_fin = date_fin;
        this.Description = description;
        this.sessions = new ArrayList<>();
    }

    public Projet(String Nom, String categorie, String type, LocalDate date_debut, LocalDate date_fin, String description) {
        this.Nom = Nom;
        this.Id_Projet = IDGenerator.generateID();
        this.Categorie = categorie;
        this.Type = type;
        this.Date_debut = date_debut;
        this.Date_fin = date_fin;
        this.Description = description;
    }

    public Projet(Projet source) {
        this.Id_Projet = null;  // Set to null or generate a new ID as per your ID generation strategy
        this.Date_debut = source.Date_debut;
        this.Date_fin = source.Date_fin;
        this.Description = source.Description;
        this.Categorie = source.Categorie;
        this.Type = source.Type;
        this.sessions = new ArrayList<>(source.sessions);
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public static Projet fromJSON(JSONObject jsonObject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String id = (String) jsonObject.get("Id_Projet");
        String categorie = (String) jsonObject.get("Categorie");
        String type = (String) jsonObject.get("Type");
        LocalDate date_debut = LocalDate.parse((String) jsonObject.get("Date_debut"), formatter);
        LocalDate date_fin = LocalDate.parse((String) jsonObject.get("Date_fin"), formatter);
        String description = (String) jsonObject.get("Description");
        Projet projet = new Projet(categorie, type, date_debut, date_fin, description);
        projet.setId_Projet(id);

        // Deserialize sessions
        JSONArray sessionsArray = (JSONArray) jsonObject.get("Sessions");
        if (sessionsArray != null) {
            for (Object obj : sessionsArray) {
                JSONObject sessionObj = (JSONObject) obj;
                Seance session = Seance.fromJSON(sessionObj);
                projet.addSession(session);
            }
        }

        return projet;
    }

    public static List<Projet> fromJSONArray(JSONArray jsonArray) {
        List<Projet> projets = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
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

    public List<Seance> getSessions() {
        return sessions;
    }

    public void addSession(Seance session) {
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        sessions.add(session);
    }

    @Override
    public String toString() {
        return "Projet{" +
                "Id_Projet=" + Id_Projet +
                ", Categorie='" + Categorie + '\'' +
                ", Date_debut=" + Date_debut +
                ", Date_fin=" + Date_fin +
                ", Description='" + Description + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_Projet", Id_Projet);
        jsonObject.put("Categorie", Categorie);
        jsonObject.put("Type", Type);
        jsonObject.put("Date_debut", Date_debut.toString());
        jsonObject.put("Date_fin", Date_fin.toString());
        jsonObject.put("Description", Description);

        // Serialize sessions
        JSONArray sessionsArray = new JSONArray();
        if (sessions != null) {
            for (Seance session : sessions) {
                sessionsArray.add(session.toJSON());
            }
        }
        jsonObject.put("Sessions", sessionsArray);

        return jsonObject;
    }

    public String getNom() {
        return Nom;
    }
}
