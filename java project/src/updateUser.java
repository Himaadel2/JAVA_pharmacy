//import javax.swing.*;
//import java.awt.*;
//
//public class updateUser extends JFrame{
//    public updateUser(){
//        setTitle("Update User");
//        setSize(800, 600);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//
//        JLabel titleLabel = new JLabel("Update User", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
//        titleLabel.setBounds(200, 10, 400, 30);
//        panel.add(titleLabel);
//
//        JSeparator separator = new JSeparator();
//        separator.setBounds(0, 50, 790, 2);
//        panel.add(separator);
//
//        add(panel);
//        setVisible(true);
//    }
//    public static void main(String[] args) {
//        new updateUser();
//    }
//}


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

public class updateUser extends JFrame {
    private String username;

    public updateUser() {
        setTitle("Update User");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLayout(null);

        JLabel titleLabel = new JLabel("Update User", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        add(separator);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(170, 70, 100, 30);
        add(usernameLabel);
        final JTextField usernameField = new JTextField(15);
        usernameField.setBounds(240, 77, 300, 20);
        add(usernameField);
        ImageIcon searchButtonIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\search.png");
        JButton searchButton = new JButton("search",searchButtonIcon);
        searchButton.setBounds(560, 77, 100, 25);
        add(searchButton);

        JLabel userRoleLabel = new JLabel("User Role:");
        userRoleLabel.setBounds(440, 150, 100, 30);
        add(userRoleLabel);

        String[] role = {"Admin", "Pharmacist"};
        JComboBox cb = new JComboBox(role);
        cb.setBounds(440, 180, 300, 20);
        add(cb);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 150, 100, 30);
        add(nameLabel);
        final JTextField nameField = new JTextField(15);
        nameField.setBounds(50, 180, 300, 20);
        add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 220, 100, 30);
        add(dobLabel);
        JDateChooser dobChooser = new JDateChooser();
        dobChooser.setDateFormatString("yyyy-MM-dd");
        dobChooser.setBounds(50, 250, 300, 20);
        add(dobChooser);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setBounds(50, 280, 100, 30);
        add(phoneNumberLabel);
        final JTextField phoneNumberField = new JTextField(15);
        phoneNumberField.setBounds(50, 310, 300, 20);
        add(phoneNumberField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 220, 100, 30);
        add(emailLabel);
        final JTextField emailField = new JTextField(15);
        emailField.setBounds(440, 250, 300, 20);
        add(emailField);



        JLabel iconCheckLabel = new JLabel();

        iconCheckLabel.setBounds(745, 177, 300, 20);
        add(iconCheckLabel);


        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(440, 280, 100, 30);
        add(addressLabel);
        final JTextField addressField = new JTextField(15);
        addressField.setBounds(440, 310, 300, 20);
        add(addressField);

        ImageIcon updateButtonIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\save.png");
        JButton updateButton = new JButton("Update",updateButtonIcon);
        updateButton.setBounds(460, 360, 150, 25);
        add(updateButton);
        setVisible(true);
//
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                    Statement stmt = con.createStatement();
                    username = usernameField.getText();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM appuser WHERE username = '" + username + "'");

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null,"username exist");
                    } else {
                        JOptionPane.showMessageDialog(null,"username not exist");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String phoneNumber = phoneNumberField.getText();
                    String email = emailField.getText();
                    String address = addressField.getText();
                    String userRole = (String) cb.getSelectedItem();
                    String name = nameField.getText();
                    Date date = dobChooser.getDate();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                    Statement st = con.createStatement();
                    String sql = "UPDATE appuser SET name = '" + name + "', user_role = '" + userRole + "', phone_number = '" + phoneNumber + "', dob = '" + date + "', email = '" + email + "', address = '" + address + "' WHERE username = '" + username + "';";
                    st.executeUpdate(sql);
                    con.close();
                    JOptionPane.showMessageDialog(null,"updated successfully!");
                    setVisible(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }


            }
        }
        );
    }
    public static void main(String[] args) {
        new updateUser();
    }
}