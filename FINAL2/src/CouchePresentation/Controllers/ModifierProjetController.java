package CouchePresentation.Controllers;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Models.ProjetModel;
import CouchePresentation.Views.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifierProjetController {
   private ModifierProjetView updateView;
   private ProjetModel projectModel;
   private String IdProjet;


    public ModifierProjetController(ModifierProjetView updateView, ProjetModel projectModel, String idProjet) {
        this.updateView = updateView;
        this.projectModel = projectModel;
        this.IdProjet = idProjet;
        Projet projet = projectModel.readProject(IdProjet);

        updateView.initialiser(projet);

        this.updateView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateView.dispose();
                new ProjetView();
            }
        });

        this.updateView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectName = updateView.getNameField().getText();
                // Convertir les éléments sélectionnés des JComboBox en String
                String selectedCategory = (String) updateView.getCategoryComboBox().getSelectedItem();
                String selectedType = (String) updateView.getTypeComboBox().getSelectedItem();
                // Convertir les valeurs des JTextField en String
                String startDateText = updateView.getStartDateField().getText();
                String endDateText = updateView.getEndDateField().getText();
                // Convertir les String représentant les dates en objets LocalDate
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                Projet prj = new Projet(projectName, selectedCategory, selectedType, startDate, endDate, updateView.getDescriptionTextArea().getText());
                prj.setId_Projet(IdProjet);
                projectModel.updateProject(prj);

                JOptionPane.showMessageDialog(updateView, "Données misent à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données misent à jour avec succès!");

                updateView.dispose();
                new ProjetView();
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
