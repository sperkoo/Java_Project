package CouchePresentation.Controllers;

import CoucheMetier.POJO.Liste;
import CouchePresentation.Models.ListeModel;
import CouchePresentation.Views.AddTacheView;
import CouchePresentation.Views.AddListeView;
import CouchePresentation.Views.ListeView;
import CouchePresentation.Views.ProjetView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddListeController {
    private AddListeView addListeView;
    private ListeModel listeModel;

    public AddListeController(AddListeView addListeView, ListeModel listeModel) {
        this.addListeView = addListeView;
        this.listeModel = listeModel;

        this.addListeView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addListeView.dispose();
                new ListeView();
            }
        });

        this.addListeView.enregistrer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = addListeView.getNomTextArea().getText();
                String description = addListeView.getDescriptionTextArea().getText();

                if (nom.isEmpty() || description.isEmpty()) {
                	addListeView.displayErrorMessage("Veuillez remplir tous les champs.");
                    return;
                }

                Liste newListe = new Liste(nom, description);
                listeModel.addListe(newListe);

                JOptionPane.showMessageDialog(addListeView, "Liste sauvegardée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                addListeView.dispose();
                new ListeView();
            }
        });
    }
}
