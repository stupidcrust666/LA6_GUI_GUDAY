import javax.swing.*;
import java.awt.*;
import java.io.*;

public class LoginForm {
    private JPanel mainPanel;
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JLabel usernameLbl;
    private JLabel passwordLbl;

    public LoginForm() {
        userNameTextField.setName("userNameTextField");
        passwordTextField.setName("passwordTextField");
        passwordTextField.setEchoChar('*');
        loginButton.setName("loginButton");

        createDefaultUsersFile();

        loginButton.addActionListener(e -> {
            String user = userNameTextField.getText();
            String pass = new String(passwordTextField.getPassword());
            boolean success = checkCredentials(user, pass);
            showStatus(success ? "Login Successful!" : "Login Failed!");
        });
        applyTheme(mainPanel);
    }

    private void applyTheme(java.awt.Container root) {
        java.awt.Color lavender = new java.awt.Color(230, 230, 250);
        root.setBackground(lavender);
        for (java.awt.Component c : root.getComponents()) {
            if (c instanceof JCheckBox || c instanceof JRadioButton) {
                ((AbstractButton) c).setOpaque(false);
            }
            if (c instanceof java.awt.Container) {
                applyTheme((java.awt.Container) c);
            }
        }
    }

    private static void setGlobalFont() {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                java.awt.Font f = (java.awt.Font) value;
                UIManager.put(key, new javax.swing.plaf.FontUIResource(
                        "Comic Sans MS", f.getStyle(), Math.max(14, f.getSize())));
            }
        }
    }

    private void createDefaultUsersFile() {
        File file = new File("users.txt");
        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("admin,admin");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean checkCredentials(String user, String pass) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2
                        && parts[0].trim().equals(user)
                        && parts[1].trim().equals(pass)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void showStatus(String message) {
        Window owner = SwingUtilities.getWindowAncestor(mainPanel);
        JDialog statusDialog = new JDialog(owner, "Status", Dialog.ModalityType.APPLICATION_MODAL);
        statusDialog.setName("statusDialog");

        JLabel messageLabel = new JLabel(message);
        messageLabel.setName("messageLabel");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        statusDialog.add(messageLabel);
        statusDialog.pack();
        statusDialog.setLocationRelativeTo(mainPanel);
        statusDialog.setVisible(true);
    }

    public static void main(String[] args) {
        setGlobalFont();
        JFrame frame = new JFrame("Login Form");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}