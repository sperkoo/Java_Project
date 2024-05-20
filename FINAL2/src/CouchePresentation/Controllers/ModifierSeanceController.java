package CouchePresentation.Controllers;

import CoucheMetier.POJO.Seance;
import CouchePresentation.Models.SeanceModel;
import CouchePresentation.Views.ProjetSeanceView;
import CouchePresentation.Views.ModifierSeanceView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class ModifierSeanceController {
    private ModifierSeanceView sessionView;
    private SeanceModel sessionModel;
    private String IdSeance;
    private String IdProjet;

    public ModifierSeanceController(ModifierSeanceView view, SeanceModel model, String idProjet, String idSeance) {
        this.sessionView = view;
        this.sessionModel = model;
        IdSeance = idSeance;
        IdProjet = idProjet;

        Seance seance = sessionModel.readSession(IdSeance);
        sessionView.initialiser(seance);

        this.sessionView.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionView.dispose();
                new ProjetSeanceView(IdProjet);
            }
        });

        this.sessionView.modifier(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Date = sessionView.getDateField().getText();
                LocalDate startDate = LocalDate.parse(Date);
                String startTimeField = sessionView.getStartTimeField().getText();
                String endTimeField = sessionView.getEndTimeField().getText();
                // Convertir les String représentant les heures en objets LocalTime
                LocalTime StartTime = LocalTime.parse(startTimeField);
                LocalTime endTime = LocalTime.parse(endTimeField);

                Seance s = new Seance(IdProjet, sessionView.getDescriptionArea().getText(), startDate, StartTime, endTime, sessionView.getNoteArea().getText());
                s.setId_seance(IdSeance);
                sessionModel.updateSession(s);

                JOptionPane.showMessageDialog(sessionView, "Données misent à jour avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Données misent à jour avec succès!");

                sessionView.dispose();
                new ProjetSeanceView(IdProjet);
            }
        });
    }
}
