package CouchePresentation.Controllers;

import CouchePresentation.Views.AddProjetView;
import CouchePresentation.Views.ProjetView;
import javax.swing.*;

public class ProjetController {
    private ProjetView view;

    public ProjetController(ProjetView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        view.getBackButton().addActionListener(e -> onBackButtonClick());
        view.getNewProjectButton().addActionListener(e -> onNewProjectButtonClick());
    }

    // Action lorsque le bouton "Retour" est cliqué
    private void onBackButtonClick() {
        JOptionPane.showMessageDialog(view, "Retour à la page précédente");
    }

    // Action lorsque le bouton "+ Nouveau projet" est cliqué
    private void onNewProjectButtonClick() {
    	AddProjetView newProjectForm = new AddProjetView();
        newProjectForm.setVisible(true);
    }

    // Action lorsque le bouton "Appliquer le filtre" est cliqué
    private void onApplyFilterButtonClick() {
        JOptionPane.showMessageDialog(view, "Appliquer le filtre sur les projets");
    }
}
