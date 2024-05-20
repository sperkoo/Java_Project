package CouchePresentation.Controllers;

import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.TacheModel;
import CouchePresentation.Views.ProjetTacheView;
import CouchePresentation.Views.UpdateTaskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UpdateTaskController {
    private UpdateTaskView updateTaskView;
    private TacheModel tacheModel;
    private String IdTache;
    private String IdProjet;

    public UpdateTaskController(UpdateTaskView taskView, TacheModel tacheModel, String idProjet, String idTache) {
        this.updateTaskView = taskView;
        this.tacheModel = tacheModel;
        this.IdProjet = idProjet;
        this.IdTache = idTache;
        Tache tache = tacheModel.readTask(IdTache);

        taskView.initialiser(tache);

        this.updateTaskView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTaskView.dispose();
                new ProjetTacheView(idProjet);
            }
        });

        this.updateTaskView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) updateTaskView.getCategoryComboBox().getSelectedItem();
                String startDateText = updateTaskView.getStartDateField().getText();
                String endDateText = updateTaskView.getEndDateField().getText();
                LocalDate startDate = LocalDate.parse(startDateText);
                LocalDate endDate = LocalDate.parse(endDateText);

                Tache t = new Tache(IdProjet, selectedCategory, updateTaskView.getDescriptionTextArea().getText(), startDate, endDate);
                t.setId_tache(IdTache);
                tacheModel.updateTask(t);

                JOptionPane.showMessageDialog(updateTaskView, "Données mises à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données mises à jour avec succès!");

                updateTaskView.dispose();
                new ProjetTacheView(idProjet);
            }
        });
    }
}
