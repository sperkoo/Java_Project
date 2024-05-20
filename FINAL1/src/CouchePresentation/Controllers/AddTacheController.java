package CouchePresentation.Controllers;

import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.TacheModel;
import CouchePresentation.Views.AddTacheView;
import CouchePresentation.Views.Dashboard;
import CouchePresentation.Views.ListeView;
import CouchePresentation.Views.ProjectPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTacheController {
    private AddTacheView form;
    private TacheModel model;

    public AddTacheController(AddTacheView form, TacheModel model, String idProjet, String idListe){
        this.form = form;
        this.model = model;
        
        this.form.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
				if (idProjet == null) {
					new ListeView();
				} else {
					new Dashboard();
				}
            }
        });

        this.form.enregistrer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Convertir les éléments sélectionnés des JComboBox en String
                String selectedCategory = (String) form.getCategoryComboBox().getSelectedItem();
                // Convertir les valeurs des JTextField en String
                String startDateText = form.getStartDateField().getText();
                String endDateText = form.getEndDateField().getText();
                
                LocalDate startDate;
                LocalDate endDate;
                
                // Convertir les String représentant les dates en objets LocalDate
                try {
                    startDate = LocalDate.parse(startDateText);
                    endDate = LocalDate.parse(endDateText);
                } catch (DateTimeParseException ex) {
                    form.displayErrorMessage("Veuillez entrer une date valide au format aaaa-mm-jj.");
                    return;
                }
                
                // Vérifier si tous les champs sont remplis
                if (selectedCategory.isEmpty()) {
                    form.displayErrorMessage("Veuillez remplir le champ 'Catégorie'!");
                    return;
                }
                
                String IdProjet = form.getProjetsComboBox().getId_Projet();
                String IdListe = form.getListesComboBox().getId_liste();
                
				if (idProjet == null) {
					Tache t = new Tache(IdProjet, idListe, selectedCategory, form.getDescriptionArea().getText(), startDate, endDate);
	                model.addTask(t);
				}
				if (idListe == null) {
					Tache t = new Tache(idProjet, IdListe, selectedCategory,
							form.getDescriptionArea().getText(), startDate, endDate);
					model.addTask(t);
				}
                
                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données de la tâche sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données de la tâche sauvegardées avec succès!");

                form.dispose();
                if (idProjet == null) {
					new ListeView();
				} else {
					new ProjectPage();
				}
            }
        });
    }
}
