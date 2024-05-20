package CouchePresentation.Controllers;

import CouchePresentation.Views.AddListeView;
import CouchePresentation.Views.ListeView;
import javax.swing.*;

import CoucheMetier.Gestion.GestionListe;

public class ListeController {
    private ListeView view;
    private GestionListe gestionListe = new GestionListe();
    
    public ListeController(ListeView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        view.getBackButton().addActionListener(e -> onBackButtonClick());
        view.getNewListeButton().addActionListener(e -> onAddListeButtonClick());
    }

    private void onBackButtonClick() {
        // Ajoutez ici la logique pour revenir à la page précédente
        JOptionPane.showMessageDialog(view, "Retour à la page précédente");
    }

    private void onAddListeButtonClick() {
        AddListeView addListeView = new AddListeView();
        addListeView.setVisible(true);
    }

    public void onSupprimerClick(String listId) {
        try {
            gestionListe.deleteListe(listId);
            JOptionPane.showMessageDialog(view, "Liste supprimée avec succès");
            // Actualisez la vue ou le modèle pour refléter la suppression
            view.refreshTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de la suppression de la liste", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSearchAction(String keyword) {
        System.out.println("Recherche avec mot-clé : " + keyword);
        // Ajoutez ici la logique pour rechercher des listes avec le mot-clé
    }
}
