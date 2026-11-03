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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Counter");
        frame.setContentPane(new NumberCounter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}