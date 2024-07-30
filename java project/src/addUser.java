import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addUser extends JFrame {
    int CheckUserName;

    public addUser() {
        setTitle("Add User");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Add USER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        panel.add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        panel.add(separator);

        JLabel userRoleLabel = new JLabel("User Role:");
        userRoleLabel.setBounds(50, 80, 100, 30);
        panel.add(userRoleLabel);

        String[] role = {"Admin", "Pharmacist"};
        JComboBox cb = new JComboBox(role);
        cb.setBounds(50, 110, 300, 20);
        panel.add(cb);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 150, 100, 30);
        panel.add(nameLabel);
        final JTextField nameField = new JTextField(15);
        nameField.setBounds(50, 180, 300, 20);
        panel.add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 220, 100, 30);
        panel.add(dobLabel);
        JDateChooser dobChooser = new JDateChooser();
        dobChooser.setDateFormatString("yyyy-MM-dd");
        dobChooser.setBounds(50, 250, 300, 20);
        panel.add(dobChooser);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(50, 280, 100, 30);
        panel.add(phoneNumberLabel);
        final JTextField phoneNumberField = new JTextField(15);
        phoneNumberField.setBounds(50, 310, 300, 20);
        panel.add(phoneNumberField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 80, 100, 30);
        panel.add(emailLabel);
        final JTextField emailField = new JTextField(15);
        emailField.setBounds(440, 110, 300, 20);
        panel.add(emailField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(440, 150, 100, 30);
        panel.add(usernameLabel);
        final JTextField usernameField = new JTextField(15);
        usernameField.setBounds(440, 180, 300, 20);
        panel.add(usernameField);


        JLabel iconCheckLabel = new JLabel();
        usernameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String username = usernameField.getText();
                if (!username.equals("")) {
                    iconCheckLabel.setVisible(true);
                    iconCheckLabel.setIcon(new ImageIcon("D:\\\\Omar_study\\\\second second semester\\\\programming-2\\\\JAVA projects\\\\testing_database_connection\\\\src\\\\icons\\\\yes.png"));
                    iconCheckLabel.setText("");

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "1234");
                        Statement st = con.createStatement();
                        String sql = "SELECT * FROM pharma.appuser WHERE username = '" + username + "'";
                        ResultSet rs = st.executeQuery(sql);
                        CheckUserName = 0;
                        if (rs.next()) {
                            CheckUserName = 1;
                            iconCheckLabel.setIcon(new ImageIcon("D:\\\\Omar_study\\\\second second semester\\\\programming-2\\\\JAVA projects\\\\testing_database_connection\\\\src\\\\icons\\\\no.png"));
                            iconCheckLabel.setText("");
                        }
                        con.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    iconCheckLabel.setVisible(false);
                }
            }
        });
        iconCheckLabel.setBounds(745, 177, 300, 20);
        panel.add(iconCheckLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(440, 220, 100, 30);
        panel.add(passwordLabel);
        final JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(440, 250, 300, 20);
        panel.add(passwordField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(440, 280, 100, 30);
        panel.add(addressLabel);
        final JTextField addressField = new JTextField(15);
        addressField.setBounds(440, 310, 300, 20);
        panel.add(addressField);

        JButton submitButton = new JButton("Save");
        submitButton.setBounds(300, 380, 200, 30);
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userRole = (String) cb.getSelectedItem();
                String name = nameField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
                Date date = dobChooser.getDate();
                String dob;
                if (date != null) {
                    dob = dateFormat.format(dobChooser.getDate());
                }
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String address = addressField.getText();
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "name is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else if (username.equals("")) {
                    JOptionPane.showMessageDialog(null, "username is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else if (CheckUserName == 1) {
                    JOptionPane.showMessageDialog(null, "username already exists.", "duplicate username", JOptionPane.ERROR_MESSAGE);
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(null, "password is required.", "Required", JOptionPane.ERROR_MESSAGE);
                }else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                        Statement st = con.createStatement();
                        String sql = "INSERT INTO appuser (user_role, name, dob, phone_number, email, username, password, address) VALUES ('" + userRole + "', '" + name + "', '" + date + "', '" + phoneNumber + "', '" + email + "', '" + username + "', '" + password + "', '" + address + "');";
                        st.executeUpdate(sql);
                        con.close();
                        JOptionPane.showMessageDialog(null,"User added successfully!");
                        setVisible(false);
                        new addUser().setVisible(true);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });

        add(panel);
        setVisible(true);
    }

}