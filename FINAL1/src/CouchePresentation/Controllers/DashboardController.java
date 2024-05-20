//package CouchePresentation.Controllers;
//
//import CouchePresentation.Views.Dashboard;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//
//
//public class DashboardController {
//    private Dashboard dashboardView;
//
//    public DashboardController() {
//        dashboardView = new Dashboard();
//        dashboardView.setQuitButtonListener(new QuitButtonListener());
//    }
//
//    // Action listener for the quit button
//    private class QuitButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Close the dashboard and open the login page
//            dashboardView.dispose();
//            new LoginController();
//        }
//    }
//
//    // Action listener for the "Mes projets" button
//    private class MesProjetsButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Close the dashboard and open the project management page
//            dashboardView.dispose();
//            new ProjectPageController();
//        }
//    }
//
//    // Action listener for the "Mes listes" button
//    private class MesListesButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Close the dashboard and open the to-do list page
//            dashboardView.dispose();
//            new ToDoListController();
//        }
//    }
//
//    // Action listener for the "Statistiques" button
//    private class StatistiquesButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Close the dashboard and open the statistics page
//            dashboardView.dispose();
//            new StatisticsController();
//        }
//    }
//
//    // Action listener for the "Archive" button
//    private class ArchiveButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Close the dashboard and open the archive page
//            dashboardView.dispose();
//            new ArchiveController();
//        }
//    }
//
//    public static void main(String[] args) {
//        // Run the GUI form
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new DashboardController();
//            }
//        });
//    }
//}
