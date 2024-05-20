package CouchePresentation.Controllers;

import CoucheMetier.POJO.Document;
import CoucheMetier.POJO.Seance;
import CouchePresentation.Models.DocModel;
import CouchePresentation.Views.AddDocumentView;
import CouchePresentation.Views.ProjetView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddDocumentController {
    AddDocumentView form;
    DocModel model;
    String IdProjet;
    String chemin;
    Document document;

    public AddDocumentController(AddDocumentView form, DocModel model, String idProjet) {
        this.form = form;
        this.model = model;
        IdProjet = idProjet;

        this.form.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
                new ProjetView();
            }
        });

        this.form.importer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    chemin = selectedFile.getAbsolutePath();
                    // Stocker le document (ici, juste affichage du chemin d'accès)
                    JOptionPane.showMessageDialog(null, "Document importé : " +chemin);
                }
            }
        });

        this.form.enregistrer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Date = form.getAddDateField().getText();
                LocalDate addDate = LocalDate.parse(Date);
                if (chemin == null){
                    form.displayErrorMessage("Veuillez importer un document!");
                    return;
                }
                Document doc = new Document(IdProjet, form.getDocNameField().getText(), form.getDescriptionArea().getText(), addDate, chemin);
                model.addDoc(doc);

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données du document sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données du document sauvegardées avec succès!");

                form.dispose();
                new ProjetView();
            }
        });
    }
}
