
package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Models.ProjectModel;
import CouchePresentation.Views.NewProjectForm;
import CouchePresentation.Views.ProjectPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;


public class NewProjectFormController {
    private NewProjectForm form;
    private ProjectModel model;

    public NewProjectFormController(NewProjectForm form, ProjectModel model){
        this.form = form;
        this.model = model;

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
                String nom = form.getNomField().getText();
                // Convertir les éléments sélectionnés des JComboBox en String
                String selectedCategory = (String) form.getCategoryComboBox().getSelectedItem();
                String selectedType = (String) form.getTypeComboBox().getSelectedItem();
                // Convertir les valeurs des JTextField en String
                String startDateText = form.getStartDateField().getText();
                String endDateText = form.getEndDateField().getText();
                // Convertir les String représentant les dates en objets LocalDate
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                // Vérifier si tous les champs sont remplis
                if (selectedCategory.isEmpty() || selectedType.isEmpty()) {
                    form.displayErrorMessage("Veuillez remplir tous les champs!");
                    return;
                }

                Projet prj = new Projet(selectedCategory, selectedType, startDate, endDate, form.getDescriptionTextArea().getText());
                model.addProject(prj);

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données du projet sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données du projet sauvegardées avec succès!");

                form.dispose();

                new ProjectPage();

            }
        });
    }
    public static void main(String[] args){
        ProjectModel projectModel = new ProjectModel();
        NewProjectForm projectForm = new NewProjectForm();
        NewProjectFormController controller = new NewProjectFormController(projectForm,projectModel);
        projectForm.setVisible(true);
    }
}
