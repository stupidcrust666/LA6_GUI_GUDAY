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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Character Counter");
        frame.setContentPane(new CharacterCounter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}