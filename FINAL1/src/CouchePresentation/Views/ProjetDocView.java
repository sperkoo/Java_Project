package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionDocument;
import CoucheMetier.POJO.Document;
import CouchePresentation.Models.DocModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjetDocView extends JFrame {
    private JTable docsTable;
    private DocModel tableModel;
    private GestionDocument gestionDocument;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel searchPanel = new JPanel(new FlowLayout());
    JTextField searchField = new JTextField(40);
    JButton backButton = new JButton("Retour");


    public ProjetDocView(String IdProjet) {
        setTitle("Mes Documents");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));


        searchPanel.add(logoLabel);
        searchPanel.add(searchField);
        searchPanel.add(backButton);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String[] columns = {"Nom", "Date d'ajout","Description"};
        gestionDocument = new GestionDocument();
        List<Document> data = gestionDocument.getAllDocuments();

        List<Document> filteredData = new ArrayList<>();
        for (Document document : data) {
            System.out.println("Document IdProjet: " + document.getId_Projet());
            System.out.println("IdProjet passé en paramètre: " + IdProjet);
            if (document.getId_Projet() != null && document.getId_Projet().equals(IdProjet)) {
                filteredData.add(document);
            }
        }

        tableModel = new DocModel(filteredData) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        docsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(docsTable);

        centerPanel.add(filtersPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        docsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int clickedRow = docsTable.rowAtPoint(e.getPoint());
                    if (clickedRow != -1) {
                        String chemin = (String) tableModel.getValueAt(clickedRow, 3);
                        System.out.println("chemin du document selectionné" +chemin);
                        openDocument(chemin);
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProjectPage();
            }
        });

        setVisible(true);
    }

    public void retour(ActionListener listener){
        backButton.addActionListener(listener);
    }
    private void openDocument(String chemin) {
            try {
                File file = new File(chemin);
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "Le fichier n'existe pas : " + chemin, "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Impossible d'ouvrir le fichier : " + chemin, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    }

}
