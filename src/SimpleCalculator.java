import javax.swing.*;

public class SimpleCalculator {
    private JTextField tfNumber1;
    private JPanel mainPanel;
    private JTextField tfNumber2;
    private JComboBox cbOperations;
    private JButton btnCompute;
    private JLabel lblResult;

    public SimpleCalculator() {
        tfNumber1.setName("tfNumber1");
        tfNumber2.setName("tfNumber2");
        cbOperations.setName("cbOperations");
        btnCompute.setName("btnCompute");
        lblResult.setName("lblResult");

        lblResult.setBorder(BorderFactory.createLineBorder(java.awt.Color.GRAY));
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setPreferredSize(new java.awt.Dimension(100, 30));

        cbOperations.setModel(new DefaultComboBoxModel<>(new String[]{"+", "-", "*", "/"}));

        btnCompute.addActionListener(e -> {
            try {
                double a = Double.parseDouble(tfNumber1.getText().trim());
                double b = Double.parseDouble(tfNumber2.getText().trim());
                String op = (String) cbOperations.getSelectedItem();
                double result;

                switch (op) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    default:
                        if (b == 0) {
                            lblResult.setText("Cannot divide by 0");
                            return;
                        }
                        result = a / b;
                }

                // show 6 instead of 6.0 when the answer is a whole number
                if (result == Math.floor(result)) {
                    lblResult.setText(String.valueOf((long) result));
                } else {
                    lblResult.setText(String.valueOf(result));
                }
            } catch (NumberFormatException ex) {
                lblResult.setText("Invalid input");
            }
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
        JFrame frame = new JFrame("Simple Calculator");
        frame.setContentPane(new SimpleCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
