package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Models.ProjetModel;
import CouchePresentation.Views.AddProjetView;
import CouchePresentation.Views.ProjetView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class AddProjetController {
    private AddProjetView form;
    private ProjetModel model;

    public AddProjetController(AddProjetView form, ProjetModel model){
        this.form = form;
        this.model = model;

        this.form.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
                new ProjetView();
            }
        });

        this.form.enregistrer(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = form.getNomField().getText();
                String selectedCategory = (String) form.getCategoryComboBox().getSelectedItem();
                String selectedType = (String) form.getTypeComboBox().getSelectedItem();
                String startDateText = form.getStartDateField().getText();
                String endDateText = form.getEndDateField().getText();
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                // Vérifier si tous les champs sont remplis
                if (selectedCategory.isEmpty() || selectedType.isEmpty()) {
                    form.displayErrorMessage("Veuillez remplir tous les champs!");
                    return;
                }

                Projet prj = new Projet(nom, selectedCategory, selectedType, startDate, endDate, form.getDescriptionTextArea().getText());
                model.addProject(prj);

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données du projet sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données du projet sauvegardées avec succès!");

                form.dispose();
                new ProjetView();
            }
        });
    }
    public static void main(String[] args){
        ProjetModel projectModel = new ProjetModel();
        AddProjetView projectForm = new AddProjetView();
        AddProjetController controller = new AddProjetController(projectForm,projectModel);
        projectForm.setVisible(true);
    }
}
