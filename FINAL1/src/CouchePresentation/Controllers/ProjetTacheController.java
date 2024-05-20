package CouchePresentation.Controllers;

import CouchePresentation.Models.TacheModel;
import CouchePresentation.Views.ProjetTacheView;
import CouchePresentation.Views.UpdateProjectView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjetTacheController {
    private TacheModel model;
    private ProjetTacheView view;
    private UpdateProjectView projectView;
    private String IdProjet;

    public ProjetTacheController(TacheModel model, UpdateProjectView projectView, String idProjet) {
        this.model = model;
        this.projectView = projectView;
        this.IdProjet = idProjet;

        this.view = new ProjetTacheView(IdProjet);

        this.view.retour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                new UpdateProjectView();
            }
        });



    }
}
