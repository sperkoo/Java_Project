package CouchePresentation.Controllers;

import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.TacheModel;
import CouchePresentation.Views.ProjetTacheView;
import CouchePresentation.Views.ModifierTacheView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifierTacheController {
    private ModifierTacheView modifierTacheView;
    private TacheModel tacheModel;
    private String IdTache;
    private String IdProjet;

    public ModifierTacheController(ModifierTacheView modifierTacheView, TacheModel tacheModel, String idProjet, String idTache) {
        this.modifierTacheView = modifierTacheView;
        this.tacheModel = tacheModel;
        this.IdProjet = idProjet;
        this.IdTache = idTache;
        Tache tache = tacheModel.readTask(IdTache);

        modifierTacheView.initialiser(tache);

        this.modifierTacheView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	modifierTacheView.dispose();
                new ProjetTacheView(idProjet);
            }
        });

        this.modifierTacheView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Convertir les éléments sélectionnés des JComboBox en String
                String selectedCategory = (String) modifierTacheView.getCategoryComboBox().getSelectedItem();
                // Convertir les valeurs des JTextField en String
                String startDateText = modifierTacheView.getStartDateField().getText();
                String endDateText = modifierTacheView.getEndDateField().getText();
                // Convertir les String représentant les dates en objets LocalDate
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                Tache t = new Tache(IdProjet, null, selectedCategory, modifierTacheView.getDescriptionTextArea().getText(), startDate, endDate);
                t.setId_tache(IdTache);
                tacheModel.updateTask(t);

                JOptionPane.showMessageDialog(modifierTacheView, "Données misent à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données misent à jour avec succès!");

                modifierTacheView.dispose();
                new ProjetTacheView(idProjet);
            }
        });

    }
}
