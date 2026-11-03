import javax.swing.*;

public class FoodOrderingSystem {
    private JPanel mainPanel;
    private JCheckBox checkPizza;
    private JCheckBox checkBurger;
    private JCheckBox checkFries;
    private JCheckBox checkSoftdrinks;
    private JCheckBox checkTea;
    private JCheckBox checkSundae;
    private JRadioButton noneRadioButton;
    private JRadioButton rb5;
    private JRadioButton rb10;
    private JRadioButton rb15;
    private JButton btnOrder;
    private JLabel foodsLabel;
    private JLabel discountsLabel;

    public FoodOrderingSystem() {
        // component names required by the assignment
        checkPizza.setName("cPizza");
        checkBurger.setName("cBurger");
        checkFries.setName("cFries");
        checkSoftdrinks.setName("cSoftDrinks");
        checkTea.setName("cTea");
        checkSundae.setName("cSundae");
        noneRadioButton.setName("rbNone");
        rb5.setName("rb5");
        rb10.setName("rb10");
        rb15.setName("rb15");
        btnOrder.setName("btnOrder");

        // only one discount can be selected at a time
        ButtonGroup discountGroup = new ButtonGroup();
        discountGroup.add(noneRadioButton);
        discountGroup.add(rb5);
        discountGroup.add(rb10);
        discountGroup.add(rb15);
        noneRadioButton.setSelected(true);

        btnOrder.addActionListener(e -> {
            double total = 0;

            if (checkPizza.isSelected()) total += 100;
            if (checkBurger.isSelected()) total += 80;
            if (checkFries.isSelected()) total += 65;
            if (checkSoftdrinks.isSelected()) total += 55;
            if (checkTea.isSelected()) total += 50;
            if (checkSundae.isSelected()) total += 40;

            if (rb5.isSelected()) {
                total *= 0.95;
            } else if (rb10.isSelected()) {
                total *= 0.90;
            } else if (rb15.isSelected()) {
                total *= 0.85;
            }

            JOptionPane.showMessageDialog(null,
                    "The total price is Php " + String.format("%.2f", total));
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Food Ordering System");
        frame.setContentPane(new FoodOrderingSystem().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}