
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MatricGUI extends JFrame {

    private JTextField quantityTextField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;

    public MatricGUI() {
        setTitle("Enemies Of Syntax Unit Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        placeComponents(panel);
        add(panel);

        // Set background color to ice blue (RGB: 173, 216, 230)
        panel.setBackground(new Color(173, 216, 230));

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Convert from:"));

        fromComboBox = new JComboBox<>(new String[]{"Feet", "Pounds", "Fahrenheit"});
        panel.add(fromComboBox);

        panel.add(new JLabel("Convert to:"));

        toComboBox = new JComboBox<>(new String[]{"Meters", "Kilograms", "Celsius"});
        panel.add(toComboBox);

        panel.add(new JLabel("Enter quantity:"));

        quantityTextField = new JTextField(10);
        panel.add(quantityTextField);

        JButton convertButton = new JButton("Convert");
        panel.add(convertButton);

        JLabel resultLabel = new JLabel("Result: ");
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertButtonClicked(resultLabel);
            }
        });
    }

    private void convertButtonClicked(JLabel resultLabel) {
        try {
            char choiceFrom = getCharValue((String) Objects.requireNonNull(fromComboBox.getSelectedItem()).toString().toLowerCase());
            char choiceTo = getCharValue((String) Objects.requireNonNull(toComboBox.getSelectedItem()).toString().toLowerCase());
            double quantity = Double.parseDouble(quantityTextField.getText());
            double result = convert(choiceFrom, choiceTo, quantity);

            // Display the result in the label
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    private char getCharValue(String unit) {
        switch (unit) {
            case "feet":
                return 'a';
            case "pounds":
                return 'b';
            case "fahrenheit":
                return 'c';
            case "meters":
                return 'a';
            case "kilograms":
                return 'b';
            case "celsius":
                return 'c';
            default:
                throw new IllegalArgumentException("Invalid unit: " + unit);
        }
    }

    private double convert(char from, char to, double quantity) {
        switch (from) {
            case 'a':
                if (to == 'a') {
                    return quantity * 0.3048;  // Feet to Feet (no conversion)
                }
                break;
            case 'b':
                if (to == 'b') {
                    return quantity * 0.453592;  // Pounds to Pounds (no conversion)
                }
                break;
            case 'c':
                if (to == 'c') {
                    return (quantity - 32) / 1.8;  // Fahrenheit to Fahrenheit (no conversion)
                } 
                break;
        }
        throw new IllegalArgumentException("Invalid conversion");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatricGUI();
            }
        });
    }
}