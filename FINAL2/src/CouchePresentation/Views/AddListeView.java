package CouchePresentation.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddListeView extends JFrame {

    JLabel nomLabel = new JLabel("Nom Liste:");
    JTextField nomField = new JTextField();
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionTextArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
    JButton saveButton = new JButton("Enregistrer");
    JButton returnButton = new JButton("Retour");

    public AddListeView() {
        this.setTitle("To-Do List");
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
        JLabel title = new JLabel("Veuillez entrer les données du nouvelle liste");
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

        addToLayout(formPanel, descriptionLabel, 0, 4, constraints);
        constraints.gridwidth = 2;
        addToLayout(formPanel, descriptionScrollPane, 0, 5, constraints);

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
    
    public JTextField getNomTextArea() {
        return nomField;
    }

    public void setNomTextArea(JTextField nomField) {
        this.nomField = nomField;
    }
    
    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public static void main(String[] args) {
        new AddListeView().setVisible(true);
    }
}
