package CouchePresentation.Controllers;

import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.TacheModel;
import CouchePresentation.Views.NewTaskForm;
import CouchePresentation.Views.ProjectPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AddTaskFormController {
    private NewTaskForm form;
    private TacheModel model;
    private String IdProjet;

    public AddTaskFormController(NewTaskForm form, TacheModel model, String IdProjet){
        this.form = form;
        this.model = model;
        this.IdProjet = IdProjet;

        this.form.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
                new ProjectPage();
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
                // Convertir les String représentant les dates en objets LocalDate
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);
                // Vérifier si tous les champs sont remplis
                if (selectedCategory.isEmpty()) {
                    form.displayErrorMessage("Veuillez remplir le champs 'Catégorie'!");
                    return;
                }

                Tache t = new Tache(IdProjet, selectedCategory, form.getDescriptionArea().getText(), startDate, endDate);
                model.addTask(t);

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données de la tâche sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données de la tâche sauvegardées avec succès!");

                form.dispose();

                new ProjectPage();
            }
        });


    }
}
