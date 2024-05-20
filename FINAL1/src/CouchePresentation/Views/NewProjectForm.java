package CouchePresentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewProjectForm extends JFrame {
    JLabel nomLabel = new JLabel("Nom Projet:");
    JTextField nomField = new JTextField();
    JLabel categoryLabel = new JLabel("Catégorie:");
    JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Enseignement", "Encadrement"});
    JLabel typeLabel = new JLabel("Type:");
    JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Thèse", "PFE", "PFA", "Cours", "Examen"});
    JLabel startDateLabel = new JLabel("Date de début:");
    JTextField startDateField = new JTextField("aaaa-mm-jj");
    JLabel endDateLabel = new JLabel("Date de fin:");
    JTextField endDateField = new JTextField("aaaa-mm-jj");
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionTextArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
    JButton saveButton = new JButton("Enregistrer");
    JButton returnButton = new JButton("Retour");

    public NewProjectForm() {

        this.setTitle("Ajouter un projet");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JLabel title = new JLabel("Veuillez entrer les données du nouveau projet");
        title.setForeground(Color.WHITE);

        panel6.setBackground(new Color(40,180,99));
        panel5.setLayout(new BorderLayout());

        panel1.setPreferredSize(new Dimension(100,50));
        panel2.setPreferredSize(new Dimension(50,100));
        panel3.setPreferredSize(new Dimension(50,100));
        panel4.setPreferredSize(new Dimension(100,50));
        panel5.setPreferredSize(new Dimension(500,450));
        panel6.setPreferredSize(new Dimension(500,30));

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.WEST);
        this.add(panel3,BorderLayout.EAST);
        this.add(panel4,BorderLayout.SOUTH);
        this.add(panel5,BorderLayout.CENTER);
        panel5.add(panel6,BorderLayout.NORTH);
        panel6.add(title);

        Border bordureColoree = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(40,180,99));
        panel5.setBorder(bordureColoree);


        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(10, 10, 10, 10);

        addToLayout(formPanel, nomLabel, 0, 0, constraints);
        addToLayout(formPanel, nomField, 1, 0, constraints);

        addToLayout(formPanel, categoryLabel, 0, 1, constraints);
        addToLayout(formPanel, categoryComboBox, 1, 1, constraints);

        addToLayout(formPanel, typeLabel, 0, 2, constraints);
        addToLayout(formPanel, typeComboBox, 1, 2, constraints);

        addToLayout(formPanel, startDateLabel, 0, 3, constraints);
        addToLayout(formPanel, startDateField, 1, 3, constraints);

        addToLayout(formPanel, endDateLabel, 0, 4, constraints);
        addToLayout(formPanel, endDateField, 1, 4, constraints);

        addToLayout(formPanel, descriptionLabel, 0, 5, constraints);
        constraints.gridwidth = 2;  // Span two columns for the description field
        addToLayout(formPanel, descriptionScrollPane, 1, 5, constraints);

        // Ajout des boutons Enregistrer et Retour avec les contraintes appropriées
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(returnButton);

        addToLayout(formPanel, buttonPanel, 0, 6, constraints);
        constraints.gridwidth = 2;

        // Ajout du formulaire au centre du panel5
        panel5.add(formPanel, BorderLayout.CENTER);

    }

    private GridBagConstraints createConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = anchor;
        constraints.insets = new Insets(top, left, bottom, right);
        return constraints;
    }

    private void addToLayout(Container container, Component component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        container.add(component, constraints);
    }

    public void retour(ActionListener listener){
        returnButton.addActionListener(listener);
    }

    public void enregistrer(ActionListener listener){
        saveButton.addActionListener(listener);
    }
    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public JTextField getNomField() {
        return nomField;
    }

    public void setNomField(JTextField nomField) {
        this.nomField = nomField;
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public void setCategoryComboBox(JComboBox<String> categoryComboBox) {
        this.categoryComboBox = categoryComboBox;
    }

    public JComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox<String> typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public void setStartDateField(JTextField startDateField) {
        this.startDateField = startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }

    public void setEndDateField(JTextField endDateField) {
        this.endDateField = endDateField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public static void main(String[] args) {
        new NewProjectForm().setVisible(true);
    }

}