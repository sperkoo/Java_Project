package CouchePresentation.Controllers;

import javax.swing.*;

import CoucheMetier.POJO.Liste;
import CouchePresentation.Models.ListeModel;
import CouchePresentation.Views.ListeView;
import CouchePresentation.Views.ModifierListeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifierListeController {
    ModifierListeView modifierView;
    ListeModel listeModel;
    ListeView listeView = new ListeView();
    String IdListe = listeView.getSelectedListeIdFromTable();

    public ModifierListeController(ModifierListeView modifierView, ListeModel listeModel, String IdListe) {
        this.modifierView = modifierView;
        this.listeModel = listeModel;
        this.IdListe = IdListe;
        Liste liste = listeModel.readListe(IdListe);

        modifierView.initialiser(liste);

        this.modifierView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	modifierView.dispose();
                new ListeView();
            }
        });

        this.modifierView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Convertir les valeurs des JTextField en String
                String nom = modifierView.getNomField().getText();
                String description = modifierView.getDescriptionTextArea().getText();
                // Vérifier si tous les champs sont remplis
                if (nom.isEmpty() || description.isEmpty()) {
                	modifierView.displayErrorMessage("Veuillez remplir tous les champs!");
                    return;
                }

                Liste liste = new Liste(nom, description);
                liste.setId(IdListe);
                listeModel.updateListe(liste);

                JOptionPane.showMessageDialog(modifierView, "Données misent à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données misent à jour avec succès!");

                modifierView.dispose();
                new ListeView();
            }
        });
    }

}
