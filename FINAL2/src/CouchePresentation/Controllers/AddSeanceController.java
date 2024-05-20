package CouchePresentation.Controllers;

import CoucheMetier.POJO.Seance;
import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.SeanceModel;
import CouchePresentation.Views.AddSeanceView;
import CouchePresentation.Views.ProjetView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddSeanceController {
    private AddSeanceView form;
    private SeanceModel model;
    private String IdProjet;


    public AddSeanceController(AddSeanceView form, SeanceModel model, String IdProjet) {
        this.form = form;
        this.model = model;
        this.IdProjet = IdProjet;

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
                new ProjetView();
            }
        });

    }
}
