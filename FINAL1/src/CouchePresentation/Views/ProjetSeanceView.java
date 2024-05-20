package CouchePresentation.Views;

import CoucheMetier.Gestion.GestionSeance;
import CoucheMetier.POJO.Seance;
import CouchePresentation.Controllers.UpdateSessionController;
import CouchePresentation.Models.SessionModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProjetSeanceView extends JFrame {
    private JTable sessionsTable;
    private SessionModel tableModel;
    private GestionSeance gestionSeance;
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel searchPanel = new JPanel(new FlowLayout());
    private JButton backButton = new JButton("Retour");
    private JPopupMenu popupMenu;
    private String selectedSessionId;



    public ProjetSeanceView(String IdProjet) {
        setTitle("Mes Séances");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel logoLabel = new JLabel(new ImageIcon("src/CouchePresentation/Views/Images/logooo.png"));


        searchPanel.add(logoLabel);
        searchPanel.add(backButton);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        String[] columns = {"Description", "Date", "Heure de début","Heure de fin","Note"};
        gestionSeance = new GestionSeance();
        List<Seance> data = gestionSeance.getAllSeances();
        List<Seance> filteredData = new ArrayList<>();
        for (Seance seance : data) {
            System.out.println("Seance IdProjet: " + seance.getId_projet());
            System.out.println("IdProjet passé en paramètre: " + IdProjet);
            if (seance.getId_projet() != null && seance.getId_projet().equals(IdProjet)) {
                filteredData.add(seance);
            }
        }

        tableModel = new SessionModel(filteredData) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        popupMenu = new JPopupMenu();
        JMenuItem modifier = new JMenuItem("Modifier");
        JMenuItem supprimer = new JMenuItem("Supprimer");
        popupMenu.add(modifier);
        popupMenu.addSeparator();
        popupMenu.add(supprimer);

        sessionsTable = new JTable(tableModel);
        sessionsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int clickedRow = sessionsTable.rowAtPoint(e.getPoint());
                    if (clickedRow != -1) {
                        popupMenu.show(sessionsTable, e.getX(), e.getY());
                        selectedSessionId = (String) tableModel.getValueAt(clickedRow, 5);
                        System.out.println("ID de la séance double-cliqué : " + selectedSessionId);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sessionsTable);

        centerPanel.add(filtersPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProjectPage();
            }
        });

        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openModifierSeance(IdProjet);
            }
        });

        setVisible(true);
    }

    public void openModifierSeance(String idProjet){
        dispose();
        UpdateSessionView view = new UpdateSessionView();
        SessionModel model = new SessionModel();
        UpdateSessionController controller = new UpdateSessionController(view, model, idProjet, selectedSessionId);
        view.setVisible(true);
    }

    public void retour(ActionListener listener){
        backButton.addActionListener(listener);
    }


}
