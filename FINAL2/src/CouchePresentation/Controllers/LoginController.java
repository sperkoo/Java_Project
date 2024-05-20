package CouchePresentation.Controllers;

import CouchePresentation.Views.HomeView;
import CouchePresentation.Views.LoginView;

import javax.swing.*;

public class LoginController {
    private LoginView loginPage;
    private HomeView dashboardPage;

    public LoginController() {
        super();
    }

    public void startApplication() {
        loginPage = new LoginView();
        loginPage.setLoginListener(new LoginView.LoginListener() {
            @Override
            public void onLogin(String email) {
                if (email.endsWith("@gmail.com")) {
                    showDashboardPage();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email address", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void showDashboardPage() {
        loginPage.dispose();
        dashboardPage = new HomeView();
        dashboardPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginController loginController = new LoginController();
                loginController.startApplication();
            }
        });
    }
}
