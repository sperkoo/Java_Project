package CouchePresentation.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewSessionForm extends JFrame {
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionArea = new JTextArea(2, 20);
    JLabel dateLabel = new JLabel("Date:");
    JTextField dateField = new JTextField("aaaa-mm-jj");
    JLabel startTimeLabel = new JLabel("Heure début:");
    JTextField startTimeField = new JTextField("HH:mm");
    JLabel endTimeLabel = new JLabel("Heure de fin:");
    JTextField endTimeField = new JTextField("HH:mm");
    JLabel noteLabel = new JLabel("Note:");
    JTextArea noteArea = new JTextArea(3, 20);
    JButton saveButton = new JButton("Enregistrer");
    JButton backButton = new JButton("Retour");


    public NewSessionForm() {
        setTitle("Ajouter une séance");
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


        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 0, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionArea), 0, 1, constraints);

        addToLayout(contentPane, dateLabel, 0, 2, constraints);
        addToLayout(contentPane, dateField, 1, 2, constraints);

        addToLayout(contentPane, startTimeLabel, 0, 3, constraints);
        addToLayout(contentPane, startTimeField, 1, 3, constraints);

        addToLayout(contentPane, endTimeLabel, 0, 4, constraints);
        addToLayout(contentPane, endTimeField, 1, 4, constraints);

        noteArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, noteLabel, 0, 5, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(noteArea), 0, 6, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        constraints.gridwidth = 2;
        addToLayout(contentPane, buttonPanel, 0, 7, constraints);
    }

    private void addToLayout(Container container, Component component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        container.add(component, constraints);
    }

    public void retour(ActionListener listener){
        backButton.addActionListener(listener);
    }
    public void enregistrer(ActionListener listener){
        saveButton.addActionListener(listener);
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JTextField getStartTimeField() {
        return startTimeField;
    }

    public JTextField getEndTimeField() {
        return endTimeField;
    }

    public JTextArea getNoteArea() {
        return noteArea;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NewSessionForm().setVisible(true);
        });
    }
}
