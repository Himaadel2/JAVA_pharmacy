import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class updatemedicine extends JFrame {
    private String username;

    public updatemedicine() {
        setTitle("Update Medicine");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLayout(null);

        JLabel titleLabel = new JLabel("Update Medicine", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        add(separator);

        JLabel usernameLabel = new JLabel("Medicine ID:");
        usernameLabel.setBounds(160, 70, 100, 30);
        add(usernameLabel);
        final JTextField usernameField = new JTextField(15);
        usernameField.setBounds(240, 77, 300, 20);
        add(usernameField);

        ImageIcon searchButtonIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\search.png");
        JButton searchButton = new JButton("search", searchButtonIcon);
        searchButton.setBounds(560, 77, 100, 25);
        add(searchButton);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 150, 100, 30);
        add(nameLabel);
        final JTextField nameField = new JTextField(15);
        nameField.setBounds(50, 180, 300, 20);
        add(nameField);

        JLabel dobLabel = new JLabel("Company Name:");
        dobLabel.setBounds(50, 220, 100, 30);
        add(dobLabel);
        final JTextField companyField = new JTextField(15);
        companyField.setBounds(50, 250, 300, 20);
        add(companyField);

        JLabel quantityLabel = new JLabel("Quantity: ");
        quantityLabel.setBounds(50, 280, 100, 30);
        add(quantityLabel);
        final JTextField quantityField = new JTextField(15);
        quantityField.setBounds(50, 310, 300, 20);
        add(quantityField);

        JLabel priceLabel = new JLabel("Price per unit:");
        priceLabel.setBounds(440, 150, 100, 30);
        add(priceLabel);
        final JTextField priceField = new JTextField(15);
        priceField.setBounds(440, 180, 300, 20);
        add(priceField);


        JLabel iconCheckLabel = new JLabel();

        iconCheckLabel.setBounds(745, 177, 300, 20);
        add(iconCheckLabel);


        JLabel addLabel = new JLabel("Add Quantity:");
        addLabel.setBounds(200, 340, 100, 30);
        add(addLabel);
        final JTextField addField = new JTextField(15);
        addField.setBounds(200, 370, 150, 20);
        add(addField);

        ImageIcon updateButtonIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\save.png");
        JButton updateButton = new JButton("Update", updateButtonIcon);
        updateButton.setBounds(440, 220, 150, 20);
        add(updateButton);
        setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    username = usernameField.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM medicine WHERE id_medicine = '" + username + "'");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Medicine ID exist");
                    } else {
                        JOptionPane.showMessageDialog(null, "Medicine ID not exist");
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
              String quantity = quantityField.getText();
              String price = priceField.getText();
              String add = addField.getText();
              String name = nameField.getText();
              String company = companyField.getText();
              int sum = Integer.parseInt(quantity) + Integer.parseInt(add);
              Class.forName("com.mysql.cj.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
              Statement st = con.createStatement();
              String sql = "UPDATE medicine SET name = '" + name + "', company = '" + company + "', quantity = '" + sum + "', price_unit = '" + price + "' WHERE id_medicine = '" + username + "';";
              st.executeUpdate(sql);
              con.close();
              JOptionPane.showMessageDialog(null, "updated successfully!");
              setVisible(false);
              } catch (Exception ex) {
              JOptionPane.showMessageDialog(null, ex);
              }
              }
        }
        );
    }
}