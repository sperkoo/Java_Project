package CouchePresentation.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class ToDoListForm extends JFrame {

    private BufferedImage backgroundImage;

    public ToDoListForm(ListeView toDoListView) {

        setTitle("To-Do List");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            backgroundImage = ImageIO.read(new File("src/CouchePresentation/Views/Images/ToDoList.png")); // Chargez votre image d'arrière-plan ici
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Dessine l'image d'arrière-plan mise à l'échelle pour s'adapter au panneau
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Nom Label and Text Field
        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomField = new JTextField();
        addToLayout(contentPane, nomLabel, 0, 0, constraints);
        addToLayout(contentPane, nomField, 1, 0, constraints);

        // Description Label and Text Area
        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(3, 20);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        addToLayout(contentPane, descriptionLabel, 0, 1, constraints);
        constraints.gridwidth = 2;
        addToLayout(contentPane, new JScrollPane(descriptionArea), 0, 2, constraints);

        // Button to add task
        JButton addTask = new JButton("+ Task");
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("<-Back");

        //BACK
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermez la fenêtre ToDoListForm
                dispose();

                // Rendez la fenêtre parente (ToDoListView) visible
                toDoListView.setVisible(true);
            }
        });

        //+TASK
        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTaskForm newTaskForm = new NewTaskForm();
                newTaskForm.setVisible(true);
            }
        });
        //BACK
//        addDocumentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Fermez la fenêtre ToDoListForm
//                dispose();
//
//                // Rendez la fenêtre parente (ToDoListView) visible
//                NewTaskForm.setVisible(true);
//            }
//        });

        //SAVE
        // Ajoutez un ActionListener au bouton "Save"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérez les données saisies
                String nom = nomField.getText();
                String description = descriptionArea.getText();

                // Vérifiez si les champs sont vides
                if (nom.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                // Ajoutez la nouvelle liste à ToDoListView
//                ToDoListView parent = (ToDoListView) getParentFrame();
//                parent.addNewList(nom, description);

                // Fermez la fenêtre ToDoListForm
                dispose();
            }
        });


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTask);
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

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new ToDoListForm(ToDoListView.this).setVisible(true);
//        });
//    }
}
