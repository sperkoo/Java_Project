package CouchePresentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewDocForm extends JFrame {
    JLabel DocNameLabel = new JLabel("Nom");
    JTextField DocNameField = new JTextField();
    JButton saveButton = new JButton("Enregistrer");
    JButton importButton = new JButton("Importer");
    JButton backButton = new JButton("Retour");
    JLabel addDateLabel = new JLabel("Date d'ajout:");
    JTextField addDateField = new JTextField("aaaa-mm-jj",30);
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionArea = new JTextArea(5, 30);

    public NewDocForm(){
        setTitle("Ajouter un document");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        JPanel contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        addToLayout(contentPane, DocNameLabel, 0, 0, constraints);
        addToLayout(contentPane, DocNameField, 1, 0, constraints);

        addToLayout(contentPane, addDateLabel, 0, 1, constraints);
        addToLayout(contentPane, addDateField, 1, 1, constraints);

        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 3, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionArea), 0, 4, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(importButton);
        buttonPanel.add(backButton);
        constraints.gridwidth = 2;
        addToLayout(contentPane, buttonPanel, 0, 5, constraints);
    }


    public void retour(ActionListener listener){
        backButton.addActionListener(listener);
    }
    public void enregistrer(ActionListener listener){
        saveButton.addActionListener(listener);
    }
    public void importer(ActionListener listener){
        importButton.addActionListener(listener);
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    private void addToLayout(Container container, Component component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        container.add(component, constraints);
    }

    public JTextField getDocNameField() {
        return DocNameField;
    }

    public JTextField getAddDateField() {
        return addDateField;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    //        public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new NewDocForm().setVisible(true);
//        });
//    }


}


