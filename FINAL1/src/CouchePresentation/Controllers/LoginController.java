package CouchePresentation.Controllers;

import CouchePresentation.Views.Dashboard;
import CouchePresentation.Views.LoginPage;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {
    private LoginPage loginPage;
    private Dashboard dashboardPage;
    private static final String API_KEY = "7280fb66db33ada3b5c825045bd3fb0c80fedf26";

    public LoginController() {
        super();
    }

    public void startApplication() {
        loginPage = new LoginPage();
        loginPage.setLoginListener(new LoginPage.LoginListener() {
            @Override
            public void onLogin(String email) {
                if (email.endsWith("@gmail.com")) {
                    if (verifyEmail(email)) {
                        showDashboardPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email address", "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please use a Gmail address", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean verifyEmail(String email) {
        try {
            String urlString = "https://api.hunter.io/v2/email-verifier?email=" + email + "&api_key=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                String status = jsonResponse.getJSONObject("data").getString("status");
                return status.equals("valid");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to verify email", "Login Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while verifying email", "Login Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void showDashboardPage() {
        loginPage.dispose();
        dashboardPage = new Dashboard();
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
