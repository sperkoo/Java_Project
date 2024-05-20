package CouchePresentation.Controllers;



import CouchePresentation.Views.NewProjectForm;
import CouchePresentation.Views.ProjectPage;

import javax.swing.*;

public class ProjectPageController {
    private ProjectPage view;

    public ProjectPageController(ProjectPage view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        // Ajouter des écouteurs aux boutons de la vue
        view.getBackButton().addActionListener(e -> onBackButtonClick());
        view.getNewProjectButton().addActionListener(e -> onNewProjectButtonClick());
//        view.getOptionsButton().addActionListener(e -> onOptionsButtonClick());
        view.getApplyFilterButton().addActionListener(e -> onApplyFilterButtonClick());
    }

    // Action lorsque le bouton "Retour" est cliqué
    private void onBackButtonClick() {
        // Ajoutez ici la logique pour revenir à la page précédente
        JOptionPane.showMessageDialog(view, "Retour à la page précédente");
    }

    // Action lorsque le bouton "+ Nouveau projet" est cliqué
    private void onNewProjectButtonClick() {
        // Ouvrir le formulaire de création de nouveau projet
        NewProjectForm newProjectForm = new NewProjectForm();
        newProjectForm.setVisible(true);
    }

    // Action lorsque le bouton "Options" est cliqué
    private void onOptionsButtonClick() {
        // Ajoutez ici la logique pour gérer les options du projet sélectionné
        JOptionPane.showMessageDialog(view, "Gérer les options du projet sélectionné");
    }

    // Action lorsque le bouton "Appliquer le filtre" est cliqué
    private void onApplyFilterButtonClick() {
        // Ajoutez ici la logique pour appliquer le filtre sur les projets
        JOptionPane.showMessageDialog(view, "Appliquer le filtre sur les projets");
    }
}
