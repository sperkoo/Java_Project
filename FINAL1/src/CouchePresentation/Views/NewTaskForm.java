package CouchePresentation.Views;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewTaskForm extends JFrame {
    JButton saveButton = new JButton("Enregistrer");
    JButton backButton = new JButton("Retour");
    JLabel categoryLabel = new JLabel("Catégorie:");
    JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Enseignement", "Encadrement"});
    JLabel startDateLabel = new JLabel("Date de début:");
    JTextField startDateField = new JTextField("aaaa-mm-jj",30);
    JLabel endDateLabel = new JLabel("Date de fin:");
    JTextField endDateField = new JTextField("aaaa-mm-jj",30);
    JLabel descriptionLabel = new JLabel("Description:");
    JTextArea descriptionArea = new JTextArea(5, 30);


    public NewTaskForm() {
        setTitle("Ajouter une tâche");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JLabel title = new JLabel("Veuillez entrer les données de la nouvelle tâche");
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

        addToLayout(contentPane, startDateLabel, 0, 1, constraints);
        addToLayout(contentPane, startDateField, 1, 1, constraints);

        addToLayout(contentPane, endDateLabel, 0, 2, constraints);
        addToLayout(contentPane, endDateField, 1, 2, constraints);

        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 3, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionArea), 0, 4, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
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

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

    private void addToLayout(Container container, Component component, int x, int y, GridBagConstraints constraints) {
        constraints.gridx = x;
        constraints.gridy = y;
        container.add(component, constraints);
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

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    //    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new NewTaskForm().setVisible(true);
//        });
//    }
}

