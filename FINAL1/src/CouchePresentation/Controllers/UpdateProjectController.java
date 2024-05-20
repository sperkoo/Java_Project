package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.ProjectModel;
import CouchePresentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UpdateProjectController {
    UpdateProjectView updateView;
    ProjectModel projectModel;
    ProjectPage projectPage = new ProjectPage();
    String IdProjet = projectPage.getSelectedProjectIdFromTable();

    public UpdateProjectController(UpdateProjectView updateView, ProjectModel projectModel, String idProjet) {
        this.updateView = updateView;
        this.projectModel = projectModel;
        IdProjet = idProjet;
        Projet projet = projectModel.readProject(IdProjet);

        updateView.initialiser(projet);

        this.updateView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateView.dispose();
                new ProjectPage();
            }
        });

        this.updateView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Convertir les éléments sélectionnés des JComboBox en String
                String selectedCategory = (String) updateView.getCategoryComboBox().getSelectedItem();
                String selectedType = (String) updateView.getTypeComboBox().getSelectedItem();
                // Convertir les valeurs des JTextField en String
                String startDateText = updateView.getStartDateField().getText();
                String endDateText = updateView.getEndDateField().getText();
                // Convertir les String représentant les dates en objets LocalDate
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                // Vérifier si tous les champs sont remplis
                if (selectedCategory.isEmpty() || selectedType.isEmpty()) {
                    updateView.displayErrorMessage("Veuillez remplir tous les champs!");
                    return;
                }

                Projet prj = new Projet(selectedCategory, selectedType, startDate, endDate, updateView.getDescriptionTextArea().getText());
                prj.setId_Projet(IdProjet);
                projectModel.updateProject(prj);

                JOptionPane.showMessageDialog(updateView, "Données misent à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données misent à jour avec succès!");

                updateView.dispose();
                new ProjectPage();
            }
        });
        this.updateView.voirTaches(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjetTacheView view =new ProjetTacheView(IdProjet);
                updateView.dispose();
                view.setVisible(true);
            }
        });

        this.updateView.voirSeances(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjetSeanceView view = new ProjetSeanceView(IdProjet);
                updateView.dispose();
                view.setVisible(true);
            }
        });

        this.updateView.voirDocs(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjetDocView view = new ProjetDocView(IdProjet);
                updateView.dispose();
                view.setVisible(true);
            }
        });

    }

}
