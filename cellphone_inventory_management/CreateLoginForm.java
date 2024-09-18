import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click
class CreateLoginForm extends JFrame implements ActionListener {
    //initialize button, panel, label, and text field
    JButton jButtonSubmit, jButtonExit, jButtonCreateAccount;
    JPanel newPanel;
    JLabel userLabel, passLabel, infoLabel;
    final JTextField textField1, textField2;

    //calling constructor
    CreateLoginForm() {
        setTitle("Lab 5 Login Window");
        setLayout(new BorderLayout());
        Icon logo = new ImageIcon(getClass().getResource("user-login-305.png"));
        infoLabel = new JLabel(logo);
        infoLabel.setSize(new Dimension(20, 20));
        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        jButtonSubmit = new JButton("SUBMIT"); //set label to button
        jButtonExit = new JButton("EXIT");
        jButtonCreateAccount = new JButton("Create an Account");

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(4, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(jButtonSubmit);
        newPanel.add(jButtonExit);

        //add panel to frame
        add(infoLabel, BorderLayout.NORTH);
        add(newPanel, BorderLayout.CENTER);
        add(jButtonCreateAccount, BorderLayout.SOUTH);

        //perform action on button click
        jButtonSubmit.addActionListener(this);     //add action listener to button

        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Are you sure?", "Confirmation window", JOptionPane.YES_NO_OPTION);
                if (r == 0)
                    System.exit(0);
            }
        });

        jButtonCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccount createAccountForm = new CreateAccount();
                createAccountForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                createAccountForm.setSize(300, 220);  //set size of the frame
                createAccountForm.setVisible(true);  //make form visible to the user
            }
        });

    }

    //define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText().trim(); // get user entered username from textField1
        String passValue = textField2.getText().trim(); // get user entered password from textField2
        boolean isSuccess = false;

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String user = parts[0].trim();
                    String pw = parts[1].trim();
                    if (userValue.equals(user) && passValue.equals(pw)) {
                        // Successful authentication
                        JOptionPane.showMessageDialog(null, "Login successful");
                        CellPhoneInventory page = new CellPhoneInventory();
                        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        page.setSize(350, 250);
                        page.setVisible(true);
                        CreateLoginForm.this.dispose();
                        isSuccess = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading user database");
        }

        if (!isSuccess) {
            // Show error message
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }
    }
}
    //create the main class
class LoginFormDemo
{
    //main() method start
    public static void main(String arg[])
    {
        //create instance of the CreateLoginForm
        //Login window for Lab 5
        CreateLoginForm form = new CreateLoginForm();
        form.setSize(320,240);  //set size of the frame
        form.setVisible(true);  //make form visible to the user

    }
}
