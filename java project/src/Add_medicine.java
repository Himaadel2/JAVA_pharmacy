
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Add_medicine extends JFrame{

    public Add_medicine() {
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel titleLabel = new JLabel("Add Medicine", SwingConstants.CENTER); //محاذاة النص الى المنتصف
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        panel.add(titleLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        separator.setForeground(Color.BLACK);
        panel.add(separator);
        
        JLabel medicine_Id = new JLabel("Medicine ID ");
        medicine_Id.setBounds(50, 100, 100, 30);
        panel.add(medicine_Id);
        
        JTextField id_field = new JTextField(15);
        id_field.setBounds(50, 150, 300, 20);
        panel.add(id_field);
        
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 200, 100, 30);
        panel.add(nameLabel);
        
        JTextField nameField = new JTextField(15);
        nameField.setBounds(50, 250, 300, 20);
        panel.add(nameField);
        
        JLabel companyLabel = new JLabel("Company Name");
        companyLabel.setBounds(50, 300, 100, 30);
        panel.add(companyLabel);
        
        JTextField companyField = new JTextField(15);
        companyField.setBounds(50, 340, 300, 20);
        panel.add(companyField);
        
        JLabel quantity = new JLabel("Quantity");
        quantity.setBounds(50, 390, 100, 30);
        panel.add(quantity);
        
        JTextField quantityField = new JTextField(15);
        quantityField.setBounds(50, 430, 300, 20);
        panel.add(quantityField);
        
        JLabel priceLabel = new JLabel("Price per unit");
        priceLabel.setBounds(440, 100, 100, 30);
        panel.add(priceLabel);
        
        JTextField priceField = new JTextField(15);
        priceField.setBounds(440, 150, 300, 20);
        panel.add(priceField);
        
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(450, 250, 100, 50);
        panel.add(saveButton);
        
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id =  id_field.getText();
                String name = nameField.getText();
                String comp_name = companyField.getText();
                int quant = Integer.valueOf(quantityField.getText());
                int price = Integer.valueOf(priceField.getText());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                    Statement st = con.createStatement();
                    String sql = "INSERT INTO medicine (id_medicine,name,company,quantity,price_unit) VALUES ('" + id + "', '" + name + "', '" + comp_name + "', '" + quant + "', '" + price + "');";
                    st.executeUpdate(sql);
                    con.close();
                    JOptionPane.showMessageDialog(null,"medicine added successfully!");
                    setVisible(false);
                    new Add_medicine().setVisible(true);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                        
      
            }
        });
        
        add(panel);
        
    }
    

}
