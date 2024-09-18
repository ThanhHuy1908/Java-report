
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ConvertFrame extends JFrame {
    private JPanel fromJPanel;
    private JPanel toJPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPanel buttonPanel;
    private JTextField inputCurrencyText;
    private JTextField resultCurrencyText;
    private ButtonGroup fromButtonGroup;
    private ButtonGroup toButtonGroup;
    private JRadioButton usdFromJRadioButton;
    private JRadioButton mxnFromJRadioButton;
    private JRadioButton euroFromJRadioButton;
    private JRadioButton usdToJRadioButton;
    private JRadioButton mxnToJRadioButton;
    private JRadioButton euroToJRadioButton;
    private JButton convertButton;
    private JButton clearButton;
    private JButton exitJButton;


    // constructor sets up GUI
    public ConvertFrame() {
        super("Currency Conversion");

        //Create labels
        label1 = new JLabel("Convert from:");
        label2 = new JLabel("Convert to:");
        label3 = new JLabel("Enter Currency:");
        label4 = new JLabel("Comparable Currency is:");

        // Create images for currency icons
        ImageIcon usdIcon = new ImageIcon(getClass().getResource("dollar.jpg"));
        usdIcon = new ImageIcon(usdIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

        ImageIcon mxnIcon = new ImageIcon(getClass().getResource("peso.jpg"));
        mxnIcon = new ImageIcon(mxnIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

        ImageIcon euroIcon = new ImageIcon(getClass().getResource("euro.jpg"));
        euroIcon = new ImageIcon(euroIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));

        // Create JLabels with currency icons for "Convert from" panel
        JLabel usdFromIconLabel = new JLabel(usdIcon);
        JLabel mxnFromIconLabel = new JLabel(mxnIcon);
        JLabel euroFromIconLabel = new JLabel(euroIcon);

        // Create JLabels with currency icons for "Convert to" panel
        JLabel usdToIconLabel = new JLabel(usdIcon);
        JLabel mxnToIconLabel = new JLabel(mxnIcon);
        JLabel euroToIconLabel = new JLabel(euroIcon);

        // Modify the layout as needed to position the JLabels above each section



        // Menu starts here
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        // About menu item
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Currency Conversion Program\nusing menus and buttons" +
                        "\nSources: https://www.oanda.com/currency-converter");
            }
        });
        fileMenu.add(aboutMenuItem);

        // Convert menu item
        JMenuItem convertMenuItem = new JMenuItem("Convert");
        convertMenuItem.setMnemonic('C');
        convertMenuItem.addActionListener(new ConvertEventHandler());
        fileMenu.add(convertMenuItem);

        // Clear menu item
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.setMnemonic('l');
        clearMenuItem.addActionListener(new ClearEventHandler());
        fileMenu.add(clearMenuItem);

        // Exit menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('x');
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        // Menu ends here


        // Create ButtonGroup for from currency JRadioButtons
        usdFromJRadioButton = new JRadioButton("US Dollar", true);
        mxnFromJRadioButton = new JRadioButton("Mexican Peso", false);
        euroFromJRadioButton = new JRadioButton("Euro", false);
        fromButtonGroup = new ButtonGroup();
        fromButtonGroup.add(usdFromJRadioButton);
        fromButtonGroup.add(mxnFromJRadioButton);
        fromButtonGroup.add(euroFromJRadioButton);

        // Create ButtonGroup for to currency JRadioButtons
        usdToJRadioButton = new JRadioButton("US Dollar", false);
        mxnToJRadioButton = new JRadioButton("Mexican Peso", true);
        euroToJRadioButton = new JRadioButton("Euro", false);
        toButtonGroup = new ButtonGroup();
        toButtonGroup.add(usdToJRadioButton);
        toButtonGroup.add(mxnToJRadioButton);
        toButtonGroup.add(euroToJRadioButton);

        // Create from JPanel
        fromJPanel = new JPanel();
        fromJPanel.setLayout(new GridLayout(2, 1)); // Change the layout to accommodate the labels and icons
        JPanel iconFromPanel = new JPanel(new GridLayout(1, 3)); // Create a panel for icons
        iconFromPanel.add(usdFromIconLabel);
        iconFromPanel.add(mxnFromIconLabel);
        iconFromPanel.add(euroFromIconLabel);
        fromJPanel.add(iconFromPanel);
        JPanel fromRadioPanel = new JPanel(new GridLayout(1, 3)); // Create a panel for radio buttons
        fromRadioPanel.add(usdFromJRadioButton);
        fromRadioPanel.add(mxnFromJRadioButton);
        fromRadioPanel.add(euroFromJRadioButton);
        fromJPanel.add(fromRadioPanel);

        // Create to JPanel
        toJPanel = new JPanel();
        toJPanel.setLayout(new GridLayout(2, 1)); // Change the layout to accommodate the labels and icons
        JPanel iconToPanel = new JPanel(new GridLayout(1, 3)); // Create a panel for icons
        iconToPanel.add(usdToIconLabel);
        iconToPanel.add(mxnToIconLabel);
        iconToPanel.add(euroToIconLabel);
        toJPanel.add(iconToPanel);
        JPanel toRadioPanel = new JPanel(new GridLayout(1, 3)); // Create a panel for radio buttons
        toRadioPanel.add(usdToJRadioButton);
        toRadioPanel.add(mxnToJRadioButton);
        toRadioPanel.add(euroToJRadioButton);
        toJPanel.add(toRadioPanel);

        // Create JTextField for entering currency to be converted
        inputCurrencyText = new JTextField(10);
        inputCurrencyText.setText("0");

        // JTextField to display converted currency
        resultCurrencyText = new JTextField(10);
        resultCurrencyText.setEditable(false);

        // JPanel for buttons
        buttonPanel = new JPanel();
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertEventHandler());
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearEventHandler());
        exitJButton = new JButton("Exit");
        exitJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(ConvertFrame.this, "Are you sure?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitJButton);

        // Add components to GUI
        setLayout(new GridLayout(11, 1));
        add(label1);
        add(fromJPanel);
        add(label2);
        add(inputCurrencyText);
        add(label3);
        add(toJPanel);
        add(label4);
        add(resultCurrencyText);
        add(buttonPanel);

        pack(); // Automatically set the size of the frame
    }

    // Inner class for Convert button and menu item event handling

    private class ConvertEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Get the input currency value from the inputCurrencyText
            double inputCurrency = Double.parseDouble(inputCurrencyText.getText());

            // Determine the conversion rates based on the selected "from" and "to" currencies
            double conversionRate;
            String fromCurrency = "";
            String toCurrency = "";
            if (usdFromJRadioButton.isSelected() && mxnToJRadioButton.isSelected()) {
                conversionRate = 16.34;
                fromCurrency = "US Dollar";
                toCurrency = "Mexican Peso";
            } else if (usdFromJRadioButton.isSelected() && euroToJRadioButton.isSelected()) {
                conversionRate = 0.92;
                fromCurrency = "US Dollar";
                toCurrency = "Euro";
            } else if (mxnFromJRadioButton.isSelected() && usdToJRadioButton.isSelected()) {
                conversionRate = 0.06119;
                fromCurrency = "Mexican Peso";
                toCurrency = "US Dollar";
            } else if (mxnFromJRadioButton.isSelected() && euroToJRadioButton.isSelected()) {
                conversionRate = 0.05634;
                fromCurrency = "Mexican Peso";
                toCurrency = "Euro";
            } else if (euroFromJRadioButton.isSelected() && usdToJRadioButton.isSelected()) {
                conversionRate = 1.08592;
                fromCurrency = "Euro";
                toCurrency = "US Dollar";
            } else {
                conversionRate = 17.741;
                fromCurrency = "Euro";
                toCurrency = "Mexican Peso";
            }

            // Perform the conversion calculation
            double result = inputCurrency * conversionRate;

            // Display the result in the resultCurrencyTextField
            resultCurrencyText.setText(String.format("%.2f", result));

            // Show the result in a pop-up dialog
            String message = String.format("%s to %s %.2f is equivalent to %.2f", fromCurrency, toCurrency, inputCurrency, result);
            JOptionPane.showMessageDialog(ConvertFrame.this, message, "Conversion Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Inner class for Clear button and menu item event handling
    private class ClearEventHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            inputCurrencyText.setText("0");
            resultCurrencyText.setText("");
        }
    }


    private class ButtonHandler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(ConvertFrame.this, String.format(
                    "You pressed: %s", event.getActionCommand()));
        }
    }

}