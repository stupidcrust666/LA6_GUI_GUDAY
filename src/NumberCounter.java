import javax.swing.*;

public class NumberCounter {
    private JPanel mainPanel;
    private JButton decreaseButton;
    private JButton increaseButton;
    private JLabel countLabel;

    private int count = 0;

    public NumberCounter() {
        countLabel.setName("countLabel");
        increaseButton.setName("increaseButton");
        decreaseButton.setName("decreaseButton");

        increaseButton.addActionListener(e -> {
            count++;
            countLabel.setText(String.valueOf(count));
        });

        decreaseButton.addActionListener(e -> {
            count--;
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
        JFrame frame = new JFrame("Number Counter");
        frame.setContentPane(new NumberCounter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}