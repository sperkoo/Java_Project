package CouchePresentation.Views;

import CoucheMetier.POJO.Projet;
import CoucheMetier.POJO.Tache;
import CouchePresentation.Models.TacheModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TaskListView extends JFrame {
    public TaskListView(Projet project) {
        setTitle("Liste des Tâches");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Print project ID to debug
        System.out.println("Afficher les taches du projet: " + project.getId_Projet());

        List<Tache> tasks = TacheModel.getTasksForProject(project.getId_Projet());

        String[] columnNames = {"Id Tâche", "Catégorie", "Description", "Date de Début", "Date de Fin"};
        String[][] data = new String[tasks.size()][5];

        for (int i = 0; i < tasks.size(); i++) {
            Tache task = tasks.get(i);
            data[i][0] = task.getId_tache();
            data[i][1] = task.getCategorie();
            data[i][2] = task.getDescription();
            data[i][3] = task.getDate_debut().toString();
            data[i][4] = task.getDate_fin().toString();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);
    }
}
