package CoucheMetier.POJO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Seance {
    private String Id_projet;
    private String Id_seance;
    private String Description;
    private LocalDate Date_seance;
    private LocalTime Heure_Debut, Heure_fin;
    private String Note;

    public Seance() {
    }

    public Seance(String IdProjet, String description, LocalDate date_seance, LocalTime heure_Debut, LocalTime heure_fin, String note) {
        Id_projet = IdProjet;
        Id_seance = IDGenerator.generateID();
        Description = description;
        Date_seance = date_seance;
        Heure_Debut = heure_Debut;
        Heure_fin = heure_fin;
        Note = note;
    }

    public String getId_projet() {
        return Id_projet;
    }

    public String getId_seance() {
        return Id_seance;
    }

    public void setId_seance(String id_seance) {
        Id_seance = id_seance;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getDate_seance() {
        return Date_seance;
    }

    public void setDate_seance(LocalDate date_seance) {
        Date_seance = date_seance;
    }

    public LocalTime getHeure_Debut() {
        return Heure_Debut;
    }

    public void setHeure_Debut(LocalTime heure_Debut) {
        Heure_Debut = heure_Debut;
    }

    public LocalTime getHeure_fin() {
        return Heure_fin;
    }

    public void setHeure_fin(LocalTime heure_fin) {
        Heure_fin = heure_fin;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "Id_seance=" + Id_seance +
                ", Description='" + Description + '\'' +
                ", Date_seance=" + Date_seance +
                ", Heure_Debut=" + Heure_Debut +
                ", Heure_fin=" + Heure_fin +
                ", Note='" + Note + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id_projet", Id_projet);
        jsonObject.put("Id_seance", Id_seance);
        jsonObject.put("Description", Description);
        jsonObject.put("Date_seance", Date_seance.toString());
        jsonObject.put("Heure_Debut", Heure_Debut.toString());
        jsonObject.put("Heure_fin", Heure_fin.toString());
        jsonObject.put("Note", Note);
        return jsonObject;
    }

    public static Seance fromJSON(JSONObject jsonObject) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String id_projet = (String) jsonObject.get("Id_projet");
        String id_seance = (String) jsonObject.get("Id_seance");  // Ensure the key is correct
        String Description = (String) jsonObject.get("Description");
        LocalDate Date_seance = LocalDate.parse((String) jsonObject.get("Date_seance"), formatter);
        LocalTime Heure_Debut = LocalTime.parse((String) jsonObject.get("Heure_Debut"), timeFormatter);
        LocalTime Heure_fin = LocalTime.parse((String) jsonObject.get("Heure_fin"), timeFormatter);
        String Note = (String) jsonObject.get("Note");
        Seance seance = new Seance(id_projet, Description, Date_seance, Heure_Debut, Heure_fin, Note);
        seance.setId_seance(id_seance);  // Set the session ID
        return seance;
    }


    public static List<Seance> fromJSONArray(JSONArray jsonArray) {
        List<Seance> seances = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            seances.add(fromJSON(jsonObject));
        }
        return seances;
    }
}
