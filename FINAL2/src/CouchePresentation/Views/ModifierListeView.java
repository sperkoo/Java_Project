package CouchePresentation.Views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import CoucheMetier.POJO.Liste;

public class ModifierListeView extends JFrame{
	JLabel nomLabel = new JLabel("Nom Liste:");
    JTextField nomField = new JTextField();
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionTextArea = new JTextArea(5, 20);
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
    JButton updateButton = new JButton("Modifier");
    JButton returnButton = new JButton("Retour");

	public ModifierListeView() {
        this.setTitle("Modifier la liste");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setLayout(new GridBagLayout());
        this.setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        addToLayout(contentPane, nomLabel, 0, 0, constraints);
        addToLayout(contentPane, nomField, 1, 0, constraints);

        addToLayout(contentPane, descriptionLabel, 0, 4, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, descriptionScrollPane, 0, 5, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(returnButton);
        constraints.gridwidth = 2;
        addToLayout(contentPane, buttonPanel, 0, 6, constraints);
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
    
    public void initialiser(Liste liste) {
        nomField.setText(liste.getNom().toString());
        descriptionTextArea.setText(liste.getDescription());
    }
    
    public JTextField getNomField() {
        return nomField;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }
    
    public static void main(String[] args) {
        new ModifierListeView().setVisible(true);
    }
}
