import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class viewbill extends JFrame {
    
    public viewbill() {
        setTitle("View Bill");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("View Bill", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(titleLabel, BorderLayout.NORTH);

        JSeparator separator = new JSeparator();
        panel.add(separator, BorderLayout.CENTER);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Bill ID");
        model.addColumn("Date");
        model.addColumn("Total Paid");
        model.addColumn("Generated By");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);

//        try {
//            // Establish a connection to the database
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
//            Statement stmt = conn.createStatement();
//            String sql = "SELECT * FROM Bill";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // Populate the table with data from the ResultSet
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                    rs.getString("billId"),
//                    rs.getString("billdate"),
//                    rs.getString("totalpaid"),
//                    rs.getString("generatedBy")
//                });
//            }
//
//            // Close the connections
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }

    }
}
