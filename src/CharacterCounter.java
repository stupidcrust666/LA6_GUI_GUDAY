import javax.swing.*;

public class CharacterCounter {
    private JPanel mainPanel;
    private JTextArea messageTextArea;
    private JTextField characterTextField;
    private JButton countButton;
    private JLabel countLabel;

    public CharacterCounter() {
        messageTextArea.setName("messageTextArea");
        characterTextField.setName("characterTextField");
        countButton.setName("countButton");
        countLabel.setName("countLabel");

        messageTextArea.setEditable(true);
        messageTextArea.setEnabled(true);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setRows(10);
        messageTextArea.setColumns(30);
        messageTextArea.setForeground(java.awt.Color.BLACK);
        messageTextArea.setBackground(java.awt.Color.WHITE);
        messageTextArea.setCaretColor(java.awt.Color.BLACK);
        messageTextArea.getCaret().setBlinkRate(500);

        characterTextField.setForeground(java.awt.Color.BLACK);
        characterTextField.setBackground(java.awt.Color.WHITE);
        characterTextField.setCaretColor(java.awt.Color.BLACK);

        countButton.addActionListener(e -> {
            String message = messageTextArea.getText();
            String input = characterTextField.getText();

            if (input.isEmpty()) {
                countLabel.setText("0");
                return;
            }

            char target = input.charAt(0);
            int count = 0;

            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == target) {
                    count++;
                }
            }

            countLabel.setText(String.valueOf(count));
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
        // keep the input boxes white
        messageTextArea.setBackground(java.awt.Color.WHITE);
        characterTextField.setBackground(java.awt.Color.WHITE);
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

    public static void main(String[] args) {
        setGlobalFont();
        JFrame frame = new JFrame("Character Counter");
        frame.setContentPane(new CharacterCounter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}