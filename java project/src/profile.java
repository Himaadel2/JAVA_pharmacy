import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class profile extends JFrame {
    private String username;

    public profile(String username) {
        this.username = username;
        setTitle("Profile");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        panel.add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        panel.add(separator);

        JLabel profileIconLabel = new JLabel(new ImageIcon("D:\\progamming\\java project\\src\\icons\\profile.png"));
        profileIconLabel.setBounds(50, 80, 250, 270);
        panel.add(profileIconLabel);
        JLabel profileNameLabel = new JLabel("name");
        profileNameLabel.setBounds(160, 365, 100, 20);
        panel.add(profileNameLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(440, 80, 100, 30);
        panel.add(nameLabel);
        final JTextField nameField = new JTextField(15);
        nameField.setBounds(440, 110, 300, 20);
        panel.add(nameField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(440, 150, 100, 30);
        panel.add(phoneNumberLabel);
        final JTextField phoneNumberField = new JTextField(15);
        phoneNumberField.setBounds(440, 180, 300, 20);
        panel.add(phoneNumberField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(440, 220, 100, 30);
        panel.add(emailLabel);
        final JTextField emailField = new JTextField(15);
        emailField.setBounds(440, 250, 300, 20);
        panel.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(440, 290, 100, 30);
        panel.add(addressLabel);
        final JTextField addressField = new JTextField(15);
        addressField.setBounds(440, 320, 300, 20);
        panel.add(addressField);

        JButton submitButton = new JButton("Save");
        submitButton.setBounds(300, 450, 200, 30);
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "name is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else if (phoneNumber.equals("")) {
                    JOptionPane.showMessageDialog(null, "phone number is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else if (email.equals("")){
                    JOptionPane.showMessageDialog(null, "email is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else if (address.equals("")) {
                    JOptionPane.showMessageDialog(null, "address is required.", "Required", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                        Statement st = con.createStatement();
                        String sql = "UPDATE appuser SET name = '" + name + "', phone_number = '" + phoneNumber + "', email = '" + email + "', address = '" + address + "' WHERE username = '" + username + "';";
                        st.executeUpdate(sql);
                        con.close();
                        JOptionPane.showMessageDialog(null,"Profile updated successfully!");
                        setVisible(false);
                        new profile(username).setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex);
                    }
                }
            }
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM appuser WHERE username = '" + username + "'";
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                profileNameLabel.setText(rs.getString("name"));
                nameField.setText(rs.getString("name"));
                phoneNumberField.setText(rs.getString("phone_number"));
                emailField.setText(rs.getString("email"));
                addressField.setText(rs.getString("address"));
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }

        add(panel);
        setVisible(true);
    }
}
