import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, result;
    private int operator;

    public CalculatorGUI() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        textField = new JTextField(10);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "C":
                textField.setText("");
                break;
            case "=":
                num2 = Double.parseDouble(textField.getText());
                calculate();
                textField.setText(String.valueOf(result));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                num1 = Double.parseDouble(textField.getText());
                operator = getOperator(command);
                textField.setText("");
                break;
            default:
                textField.setText(textField.getText() + command);
        }
    }

    private void calculate() {
        switch (operator) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = 0; // Handle division by zero
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    private int getOperator(String op) {
        switch (op) {
            case "+":
                return 1;
            case "-":
                return 2;
            case "*":
                return 3;
            case "/":
                return 4;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}
