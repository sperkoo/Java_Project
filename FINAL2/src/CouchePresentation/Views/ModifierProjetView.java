package CouchePresentation.Views;

import CoucheMetier.POJO.Projet;
import CouchePresentation.Models.TacheModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProjetView extends JFrame{
	JLabel nameLabel = new JLabel("Nom:");
	JTextField nameField = new JTextField();
    JLabel categoryLabel = new JLabel("Catégorie:");
    JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Enseignement", "Encadrement"});
    JLabel typeLabel = new JLabel("Type:");
    JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Thèse", "PFE", "PFA", "Cours", "Examen"});
    JLabel startDateLabel = new JLabel("Date de début:");
    JTextField startDateField = new JTextField();
    JLabel endDateLabel = new JLabel("Date de fin:");
    JTextField endDateField = new JTextField();
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionTextArea = new JTextArea(5, 30);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
    JButton updateButton = new JButton("Modifier");
    JButton returnButton = new JButton("Retour");
    JButton voirTaches = new JButton("Voir tâches");
    JButton voirSeances = new JButton("Voir séances");
    JButton voirDocs = new JButton("Voir documents");


    public ModifierProjetView() {
        this.setTitle("Modifier le projet");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setLocationRelativeTo(null);

        setLayout(new GridBagLayout());

        JPanel contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        addToLayout(contentPane, nameLabel, 0, 0, constraints);
        addToLayout(contentPane, nameField, 1, 0, constraints);
        
        addToLayout(contentPane, categoryLabel, 0, 1, constraints);
        addToLayout(contentPane, categoryComboBox, 1, 1, constraints);

        addToLayout(contentPane, typeLabel, 0, 2, constraints);
        addToLayout(contentPane, typeComboBox, 1, 2, constraints);

        addToLayout(contentPane, startDateLabel, 0, 3, constraints);
        addToLayout(contentPane, startDateField, 1, 3, constraints);

        addToLayout(contentPane, endDateLabel, 0, 4, constraints);
        addToLayout(contentPane, endDateField, 1, 4, constraints);

        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 5, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionTextArea), 0, 6, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);
        constraints.gridwidth = 2;
        addToLayout(contentPane, buttonPanel, 0, 7, constraints);

        JPanel additionalButtonPanel = new JPanel();
        additionalButtonPanel.add(voirTaches);
        additionalButtonPanel.add(voirSeances);
        additionalButtonPanel.add(voirDocs);
        addToLayout(contentPane, additionalButtonPanel, 0, 8, constraints);


    }

    private void addToLayout(Container container, Component component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        container.add(component, constraints);
    }

    public void retour(ActionListener listener){
        returnButton.addActionListener(listener);
    }

    public void modifier(ActionListener listener){
        updateButton.addActionListener(listener);
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public void voirTaches(ActionListener listener){
        voirTaches.addActionListener(listener);
    }

    public void voirSeances(ActionListener listener){
        voirSeances.addActionListener(listener);
    }

    public void voirDocs(ActionListener listener){
        voirDocs.addActionListener(listener);
    }

    public void initialiser(Projet projet) {
        nameField.setText(projet.getNom());
        categoryComboBox.setSelectedItem(projet.getCategorie());
        typeComboBox.setSelectedItem(projet.getType());
        startDateField.setText(projet.getDate_debut().toString());
        endDateField.setText(projet.getDate_fin().toString());
        descriptionTextArea.setText(projet.getDescription());
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }


    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }


}
