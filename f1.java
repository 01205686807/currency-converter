package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Main2 extends JFrame {
    private JLabel titleLabel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel fromLabel;
    private JComboBox<String> fromComboBox;
    private JLabel toLabel;
    private JComboBox<String> toComboBox;
    private JButton convertButton;
    private JLabel resultLabel;
    private JLabel noteLabel;

    private static final String[] currencies = {"USD", "EUR", "GBP", "JPY", "CAD", "INR"};
    private static final double[][] conversionRates = {
            {1.0, 0.84, 0.84, 110.94, 1.26, 74.0},
            {1.19, 1.0, 0.88, 131.84, 1.5, 88.29},
            {1.35, 1.13, 1.0, 149.96, 1.71, 100.53},
            {0.0090, 0.0076, 0.0067, 1.0, 0.011, 0.65},
            {0.79, 0.67, 0.59, 88.97, 1.0, 58.64},
            {0.0135, 0.0113, 0.0099, 1.52, 0.017, 1.0}
    };

    public Main2() {
        setTitle("Currency Converter");

        titleLabel = new JLabel("Currency Converter");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        amountLabel = new JLabel("Enter Amount:");
        amountLabel.setForeground(Color.BLACK);
        amountTextField = new JTextField(10); // حجم النص
        amountTextField.setForeground(Color.BLACK);

        fromLabel = new JLabel("From Currency:");
        fromLabel.setForeground(Color.BLACK);
        fromComboBox = new JComboBox<>(currencies);
        fromComboBox.setBackground(Color.WHITE);
        fromComboBox.setForeground(Color.BLACK);

        toLabel = new JLabel("To Currency:");
        toLabel.setForeground(Color.BLACK);
        toComboBox = new JComboBox<>(currencies);
        toComboBox.setBackground(Color.WHITE);
        toComboBox.setForeground(Color.BLACK);

        convertButton = new JButton("Convert");
        convertButton.setForeground(Color.WHITE);
        convertButton.setBackground(Color.BLACK);
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        resultLabel = new JLabel("Result:");
        resultLabel.setForeground(Color.BLACK);

        noteLabel = new JLabel("Note: The currency values used in this demo are for illustration purposes only.");
        noteLabel.setForeground(Color.BLACK);

        setLayout(new GridLayout(8, 2));
        add(titleLabel);
        add(new JLabel()); // Empty cell for layout
        add(amountLabel);
        add(amountTextField);
        add(fromLabel);
        add(fromComboBox);
        add(toLabel);
        add(toComboBox);
        add(convertButton);
        add(resultLabel);
        add(noteLabel);

        amountTextField.setText("100");
        fromComboBox.setSelectedItem("USD");
        toComboBox.setSelectedItem("EUR");

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            String fromCurrency = (String) fromComboBox.getSelectedItem();
            String toCurrency = (String) toComboBox.getSelectedItem();

            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < currencies.length; i++) {
                if (currencies[i].equals(fromCurrency)) {
                    fromIndex = i;
                }
                if (currencies[i].equals(toCurrency)) {
                    toIndex = i;
                }
            }

            if (fromIndex != -1 && toIndex != -1) {
                double conversionRate = conversionRates[fromIndex][toIndex];
                double convertedAmount = amount * conversionRate;
                DecimalFormat df = new DecimalFormat("#.##");
                resultLabel.setText("Result: " + df.format(amount) + " " + fromCurrency + " = " + df.format(convertedAmount) + " " + toCurrency);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid currency selection. Please select valid currencies.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main2();
            }
        });
    }
}
