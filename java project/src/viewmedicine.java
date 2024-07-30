import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class viewmedicine extends JFrame {
    public viewmedicine() {
        setTitle("View Medicine");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLayout(null);

        JLabel titleLabel = new JLabel("View Medicine", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(200, 10, 400, 30);
        add(titleLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 790, 2);
        add(separator);

        String[] columns = {"ID", "Medicine ID", "Name", "Company Name", "Quantity", "Price per unit"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 760, 480);
        add(scrollPane);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                TableModel model = table.getModel();
                String id = model.getValueAt(index,0).toString();
                int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this medicine?", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0){
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
                        Statement st = con.createStatement();
                        String sql = "DELETE FROM medicine WHERE id_m = " + id + " ;";
                        st.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Medicine deleted successfully");
                        setVisible(false);
                        new viewmedicine().setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false", "root", "1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medicine");

            while (rs.next()) {
                Object[] row = new Object[columns.length];
                for (int i = 1; i <= columns.length; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        setVisible(true);
    }
}
