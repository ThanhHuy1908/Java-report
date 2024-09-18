
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class CellPhoneInventory extends JFrame {
    JLabel labelModel, labelManufacturer, labelRetailPrice, labelBanner;
    JTextField jTextFieldModel, jTextFieldManufacturer, jTextFieldRetailPrice;
    JButton jButtonAdd, jButtonSave, jButtonNext,jButtonShow;
    JPanel newPanel,buttonPanel;
    ArrayList<CellPhone> phoneArrayList = new ArrayList<CellPhone>();

    CellPhoneInventory() {
        setTitle("Cellphone Inventory");
        setLayout(new BorderLayout());
        labelBanner = new JLabel("Cellphone Inventory Management");
        labelBanner.setFont(new Font("Serif",Font.BOLD,20));
        labelBanner.setForeground(Color.BLUE);
        labelModel = new JLabel("Model");
        labelManufacturer = new JLabel("Manufacturer");
        labelRetailPrice = new JLabel("Retail Price");
        jTextFieldModel = new JTextField(15);
        jTextFieldManufacturer = new JTextField(15);
        jTextFieldRetailPrice = new JTextField(15);
        jButtonAdd = new JButton("Add");
        jButtonSave = new JButton("Save");
        jButtonNext = new JButton("Next");
        jButtonShow = new JButton("Show Inventory");
        newPanel = new JPanel(new GridLayout(3, 2));
        newPanel.add(labelModel);
        newPanel.add(jTextFieldModel);
        newPanel.add(labelManufacturer);
        newPanel.add(jTextFieldManufacturer);
        newPanel.add(labelRetailPrice);
        newPanel.add(jTextFieldRetailPrice);
        buttonPanel = new JPanel(new GridLayout(2,2));
        buttonPanel.add(jButtonAdd);
        buttonPanel.add(jButtonNext);
        buttonPanel.add(jButtonSave);
        buttonPanel.add(jButtonShow);
        add(labelBanner, BorderLayout.NORTH);
        add(newPanel, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);

        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String model = jTextFieldModel.getText();
                String manufacturer = jTextFieldManufacturer.getText();
                String priceText = jTextFieldRetailPrice.getText();

                try {
                    if (model == null || model.trim().isEmpty()) {
                        throw new InvalidModelException();
                    }
                    if (manufacturer == null || manufacturer.trim().isEmpty()) {
                        throw new InvalidManufacturerException();
                    }
                    if (priceText.isEmpty()) {
                        throw new InvalidRetailPriceException();
                    }

                    double price = Double.parseDouble(priceText);
                    if (price <= 0 || price > 1500) {
                        throw new InvalidRetailPriceException();
                    }

                    phoneArrayList.add(new CellPhone(model, manufacturer, price));
                    String inventoryDisplay = "";
                    for (CellPhone p : phoneArrayList) {
                        System.out.println(p);
                        inventoryDisplay += p;
                    }
                    JOptionPane.showMessageDialog(null, inventoryDisplay);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid model: Model cannot be empty.");
                } catch (InvalidModelException | InvalidManufacturerException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid manufacturer: Manufacturer cannot be empty.");
                } catch (InvalidRetailPriceException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid retail price: Retail price must be a positive number less than or equal to 1500.");
                }
            }
        });



        jButtonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextFieldModel.setText(null);
                jTextFieldManufacturer.setText(null);
                jTextFieldRetailPrice.setText(null);
            }
        });
        jButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Formatter outCellPhoneList = null;
                try {

                    // file stream for output file
                    outCellPhoneList = new Formatter("cellPhones.txt");


                    for (int i = 0; i < phoneArrayList.size(); i++) {
                        outCellPhoneList.format("%s %s %.2f\n", phoneArrayList.get(i).getModel(),
                                phoneArrayList.get(i).getManufacturer(),
                                phoneArrayList.get(i).getRetailPrice());
                    }
                    System.out.println("cellphone list stored in txt file.");
                } catch (SecurityException securityException) {
                    System.err.println(
                            "You do not have write access to this file.");
                    System.exit(1);
                } catch (FileNotFoundException fileNotFoundException) {
                    System.err.println(
                            "Error creating file.");
                    System.exit(1);
                } catch (FormatterClosedException closedException) {
                    System.err.println(
                            "Error writing to file - file has been closed.");
                    System.exit(1);
                } catch (IllegalFormatException formatException) {
                    System.err.println("Error with output.");
                    System.exit(1);
                } finally {
                    if (outCellPhoneList != null) {
                        outCellPhoneList.close();
                    }}
            }
        });
        jButtonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String msg = String.format("%-20s%-20s%10s%n","Model", "Manufacturer",
                        "retail price");
                // open cellPhoneList.txt, read its contents and close the file
                try(Scanner input = new Scanner(Paths.get("cellPhones.txt"))) {

                    // read record from file
                    while (input.hasNext()) { // while there is more to read
                        // display record contents

                        msg += String.format("%-20s%-20s%10.2f%n",input.next(),
                                input.next(),  input.nextDouble());
                    }
                }
                catch ( NoSuchElementException | IllegalStateException | IOException exception) {
                    //e.printStackTrace();
                    System.out.println("Error: " + exception.getMessage());
                }
                JOptionPane.showMessageDialog(null,msg);

            }
        });

    }
}