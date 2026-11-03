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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setContentPane(new SimpleCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
