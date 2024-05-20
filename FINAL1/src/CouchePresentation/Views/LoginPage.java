package CouchePresentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginPage extends JFrame {
    private JTextField emailField;
    private JButton loginButton;
    private LoginListener loginListener;

    public LoginPage() {
        setTitle("Login Page");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage backgroundImage;
        try {
            backgroundImage = ImageIO.read(new File("src/CouchePresentation/Views/Images/mybackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null;
        }

        // Create a custom JPanel to paint the background image
        BufferedImage finalBackgroundImage = backgroundImage;
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    // Calculate the scale factors
                    double scaleX = (double) getWidth() / finalBackgroundImage.getWidth();
                    double scaleY = (double) getHeight() / finalBackgroundImage.getHeight();
                    // Scale and draw the image
                    int width = (int) (finalBackgroundImage.getWidth() * scaleX);
                    int height = (int) (finalBackgroundImage.getHeight() * scaleY);
                    g.drawImage(finalBackgroundImage, 0, 0, width, height, this);
                }
            }
        };

        // Set the custom panel as the content pane of the JFrame
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        // Panel for center components
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Make panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center horizontally and vertically
        backgroundPanel.add(centerPanel, gbc);

        initialiser();
        dessiner(centerPanel, gbc);
        action();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initialiser() {
        emailField = new JTextField("Email", 30);
        emailField.setForeground(Color.GRAY);
        loginButton = new JButton("Login");
    }

    private void dessiner(JPanel centerPanel, GridBagConstraints gbc) {
        // Add components to the center panel
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // Add vertical padding
        centerPanel.add(emailField, gbc);

        gbc.gridy = 1;
        centerPanel.add(loginButton, gbc);
    }

    private void action() {
        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                if (loginListener != null) {
                    loginListener.onLogin(email); // Notify the listener
                }
            }
        });
    }

    public void setLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    // Define a LoginListener interface
    public interface LoginListener {
        void onLogin(String email);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPage loginPage = new LoginPage();
                loginPage.setLoginListener(new LoginListener() {
                    @Override
                    public void onLogin(String email) {
                        // For testing purposes, just print the email
                        System.out.println("Logged in with email: " + email);
                    }
                });
            }
        });
    }
}
