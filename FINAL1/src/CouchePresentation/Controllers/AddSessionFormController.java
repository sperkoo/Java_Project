package CouchePresentation.Controllers;

import CoucheMetier.POJO.Seance;
import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.SessionModel;
import CouchePresentation.Views.NewSessionForm;
import CouchePresentation.Views.ProjectPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddSessionFormController {
    private NewSessionForm form;
    private SessionModel model;
    private String IdProjet;


    public AddSessionFormController(NewSessionForm form, SessionModel model, String IdProjet) {
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
                String Date = form.getDateField().getText();
                LocalDate startDate = LocalDate.parse(Date);
                String startTimeField = form.getStartTimeField().getText();
                String endTimeField = form.getEndTimeField().getText();
                // Convertir les String représentant les heures en objets LocalTime
                LocalTime StartTime = LocalTime.parse(startTimeField);
                LocalTime endTime = LocalTime.parse(endTimeField);

                Seance seance = new Seance(IdProjet, form.getDescriptionArea().getText(), startDate, StartTime, endTime, form.getNoteArea().getText());
                model.addSession(seance);

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(form, "Données de la séance sauvegardées avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données de la séance sauvegardées avec succès!");

                form.dispose();
                new ProjectPage();
            }
        });


    }
}
