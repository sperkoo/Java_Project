package CouchePresentation.Models;

import CoucheMetier.Gestion.GestionDocument;
import CoucheMetier.POJO.Document;
import CoucheMetier.POJO.Seance;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DocModel extends AbstractTableModel {
    private GestionDocument gestionDocument;
    private String[] columnNames = {"Nom", "Date d'ajout","Description"};
    private List<Document> documents;

    public DocModel() {
        gestionDocument = new GestionDocument();
    }

    public DocModel(List<Document> documents) {
        this.documents = documents;
    }

    public void addDoc(Document doc){
        gestionDocument.addDocument(doc);
    }

    @Override
    public int getRowCount() {
        return documents.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Document document = documents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return document.getNom();
            case 1:
                return document.getDate_ajout();
            case 2:
                return document.getDescription();
            case 3:
                return document.getChemin();
            default:
                return null;
        }
    }
}
