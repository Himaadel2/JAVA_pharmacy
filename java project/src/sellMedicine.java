import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sellMedicine extends JFrame {
    private String finalTotalPrice;
    private String billId;
    private String username;
    JTable medicineTable = new JTable();
    JTextField medicineIdField;

    public sellMedicine(String username) {
        this.username = username;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setLayout(null);

        JLabel pageTitle = new JLabel("Sell Medicine");
        JSeparator separator = new JSeparator();
        pageTitle.setFont(new Font("Arial", Font.BOLD, 30));
        separator.setBackground(Color.BLACK);

        //Left side
        JLabel searchLabel = new JLabel("Search");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField searchField = new JTextField();


        // Table of medicines
        JPanel tablePanel = new JPanel(new GridLayout(1, 1));
        String[] columnNames = {"Medicines"};
        DefaultTableModel medicineModel = new DefaultTableModel(columnNames, 0);
        // Create JTable
        medicineTable.setModel(medicineModel);
        // Add table to scroll pane (optional for large datasets)
        JScrollPane scrollPane = new JScrollPane(medicineTable);
        tablePanel.add(scrollPane);

        //right side

        //labels
        JLabel medicineIdLabel = new JLabel("Medicine ID");
        JLabel nameLabel = new JLabel("Name");
        JLabel companyNameLabel = new JLabel("Company Name");
        JLabel pricePerUnitLabel = new JLabel("Price Per Unit");
        JLabel numberOfUnitsLabel = new JLabel("No. of Units");
        JLabel totalPriceLabel = new JLabel("Total Price");

        //text fields
        medicineIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField companyNameField = new JTextField();
        JTextField pricePerUnitField = new JTextField();
        JTextField numberOfUnitsField = new JTextField();
        JTextField totalPriceField = new JTextField();

        //add to cart button
        JButton addToCartBtn = new JButton("Add to cart");
        addToCartBtn.setBackground(Color.white);

        // Table of medicines right side
        JPanel tablePanelRight = new JPanel(new GridLayout(1, 1));
        String[] columnNamesRight = {"Medicine ID", "Name", "Company Name", "Price Per Unit", "No. of Units", "Total Price"};
        DefaultTableModel medicineModelRight = new DefaultTableModel(columnNamesRight, 0);
        // Create JTable
        JTable medicineTableRight = new JTable(medicineModelRight);
        medicineTableRight.setSize(100, 430);
        // Add table to scroll pane (optional for large datasets)
        JScrollPane scrollPaneRight = new JScrollPane(medicineTableRight);
        scrollPaneRight.setSize(100, 430);
        tablePanelRight.add(scrollPaneRight);
        tablePanelRight.setBackground(Color.BLACK);

        //RS
        JLabel RSLabel = new JLabel("RS: ");
        RSLabel.setFont(new Font("Arial", Font.BOLD, 25));
        JLabel RSValueLabel = new JLabel("00");
        RSValueLabel.setFont(new Font("Arial", Font.BOLD, 25));

        p1.add(pageTitle);
        p1.add(separator);
        p1.add(searchLabel);
        p1.add(searchField);
        p1.add(tablePanel);
        p1.add(medicineIdLabel);
        p1.add(medicineIdField);
        p1.add(nameLabel);
        p1.add(nameField);
        p1.add(companyNameLabel);
        p1.add(companyNameField);
        p1.add(pricePerUnitLabel);
        p1.add(pricePerUnitField);
        p1.add(numberOfUnitsLabel);
        p1.add(numberOfUnitsField);
        p1.add(totalPriceLabel);
        p1.add(totalPriceField);
        p1.add(addToCartBtn);
        p1.add(tablePanelRight);
        p1.add(RSLabel);
        p1.add(RSValueLabel);


        //left side set bounds
        pageTitle.setBounds(dim.width / 2 - 75, 30, 500, 30);
        separator.setBounds(0, 75, getWidth(), 5);
        searchLabel.setBounds(180, 110, 100, 25);
        searchField.setBounds(50, 140, 320, 25);
        tablePanel.setBounds(50, 170, 320, 520);

        // right side set bounds
        medicineIdLabel.setBounds(550, 110, 100, 20);
        medicineIdField.setBounds(550, 130, 300, 20);
        nameLabel.setBounds(550, 160, 300, 20);
        nameField.setBounds(550, 180, 300, 20);
        companyNameLabel.setBounds(550, 210, 300, 20);
        companyNameField.setBounds(550, 230, 300, 20);
        pricePerUnitLabel.setBounds(950, 110, 100, 20);
        pricePerUnitField.setBounds(950, 130, 300, 20);
        numberOfUnitsLabel.setBounds(950, 160, 300, 20);
        numberOfUnitsField.setBounds(950, 180, 300, 20);
        totalPriceLabel.setBounds(950, 210, 300, 20);
        totalPriceField.setBounds(950, 230, 300, 20);
        addToCartBtn.setBounds(1150, 260, 100, 40);

// table panel right side
        tablePanelRight.setBounds(550, 310, 700, 250);

        RSLabel.setBounds(830, 580, 50, 20);
        RSValueLabel.setBounds(880, 580, 50, 20);


        getContentPane().add(p1);
        setVisible(true);
    }

    private void medicineName(String nameOrUniqueId){
        DefaultTableModel model = (DefaultTableModel) medicineTable.getModel();
        model.setRowCount(0);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharma?useSSL=false", "root", "1234");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM pharma.medicine WHERE name LIKE '" + nameOrUniqueId + "%' OR unique_id LIKE '" + nameOrUniqueId + "%'";
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                model.addRow(new Object[]{rs.getString("unique_id" + " - " + rs.getString("name"))});
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
    }

    private void clearMedicineFields(){
        medicineIdField.setText("");

    }
}
