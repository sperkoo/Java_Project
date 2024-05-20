
import CouchePresentation.Controllers.LoginController;

import javax.swing.*;

public class Main {
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
