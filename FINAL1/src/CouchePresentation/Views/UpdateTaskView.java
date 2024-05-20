package CouchePresentation.Views;

import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Tache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpdateTaskView extends JFrame {
    JLabel categoryLabel = new JLabel("Catégorie:");
    JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Enseignement", "Encadrement"});
    JLabel startDateLabel = new JLabel("Date de début:");
    JTextField startDateField = new JTextField();
    JLabel endDateLabel = new JLabel("Date de fin:");
    JTextField endDateField = new JTextField();
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionTextArea = new JTextArea(5, 30);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
    JButton updateButton = new JButton("Modifier");
    JButton returnButton = new JButton("Retour");

    public UpdateTaskView() {
        this.setTitle("Modifier la tâche");
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

        addToLayout(contentPane, categoryLabel, 0, 0, constraints);
        addToLayout(contentPane, categoryComboBox, 1, 0, constraints);

        addToLayout(contentPane, startDateLabel, 0, 2, constraints);
        addToLayout(contentPane, startDateField, 1, 2, constraints);

        addToLayout(contentPane, endDateLabel, 0, 3, constraints);
        addToLayout(contentPane, endDateField, 1, 3, constraints);

        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 4, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionTextArea), 0, 5, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);
        constraints.gridwidth = 2;
        addToLayout(contentPane, buttonPanel, 0, 6, constraints);

        JPanel additionalButtonPanel = new JPanel();
        addToLayout(contentPane, additionalButtonPanel, 0, 7, constraints);

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

    public void initialiser(Tache tache) {
        categoryComboBox.setSelectedItem(tache.getCategorie());
        startDateField.setText(tache.getDate_debut().toString());
        endDateField.setText(tache.getDate_fin().toString());
        descriptionTextArea.setText(tache.getDescription());
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
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
