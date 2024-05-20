package CouchePresentation.Controllers;

import CoucheMetier.POJO.Liste;
import CouchePresentation.Models.ListeModel;
import CouchePresentation.Views.ListeView;
import CouchePresentation.Views.ModifierListeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierListeController {
    ModifierListeView modifierView;
    ListeModel listeModel;
    String idListe;

    public ModifierListeController(ModifierListeView modifierView, ListeModel listeModel, String idListe) {
        this.modifierView = modifierView;
        this.listeModel = listeModel;
        this.idListe = idListe;

        if (idListe == null || idListe.isEmpty()) {
            modifierView.displayErrorMessage("ID de la liste est invalide.");
            return;
        }

        Liste liste = listeModel.readListe(idListe);

        if (liste == null) {
            modifierView.displayErrorMessage("Erreur de chargement de la liste.");
            return;
        }

        modifierView.initialiser(liste);

        modifierView.retour(e -> {
            modifierView.dispose();
            new ListeView();
        });

        modifierView.modifier(e -> {
            String nom = modifierView.getNomField().getText();
            String description = modifierView.getDescriptionTextArea().getText();

            if (nom.isEmpty() || description.isEmpty()) {
                modifierView.displayErrorMessage("Veuillez remplir tous les champs!");
                return;
            }

            Liste updatedListe = new Liste(nom, description);
            updatedListe.setId_liste(idListe);
            listeModel.updateListe(updatedListe);

            JOptionPane.showMessageDialog(modifierView, "Données mises à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

            modifierView.dispose();
            new ListeView();
        });
    }
}
