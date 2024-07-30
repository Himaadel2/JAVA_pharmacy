import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pharmacistDashboard extends JFrame {
    String username;
    public pharmacistDashboard(String username){
        this.username = username;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        p1.setLayout(null);

        JLabel pageTitle = new JLabel("Pharmacist Dashboard");
        JSeparator separator = new JSeparator();

        ImageIcon addMedicineIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\addMedicine.png");
        JButton addMedicineBtn = new JButton(addMedicineIcon);
        addMedicineBtn.setText("    Add Medicine");
        addMedicineBtn.setFont(new Font("Arial", Font.BOLD, 25));
        addMedicineBtn.setBackground(Color.WHITE);
        addMedicineBtn.setFocusPainted(false);
        addMedicineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Add_medicine();
            }
        });

        ImageIcon sellMedicineIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\sellMedicine.png");
        JButton sellMedicineBtn = new JButton(sellMedicineIcon);
        sellMedicineBtn.setText("    Sell Medicine");
        sellMedicineBtn.setFont(new Font("Arial", Font.BOLD, 25));
        sellMedicineBtn.setBackground(Color.WHITE);
        sellMedicineBtn.setFocusPainted(false);
        sellMedicineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new sellMedicine(username).setVisible(true);
            }
        });

        ImageIcon viewMedicineIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\viewUser.png");
        JButton viewMedicineBtn = new JButton(viewMedicineIcon);
        viewMedicineBtn.setText("    View Medicine");
        viewMedicineBtn.setFont(new Font("Arial", Font.BOLD, 25));
        viewMedicineBtn.setBackground(Color.WHITE);
        viewMedicineBtn.setFocusPainted(false);
        viewMedicineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewmedicine().setVisible(true);
            }
        });

        ImageIcon viewBillIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\viewBill.png");
        JButton viewBillBtn = new JButton(viewBillIcon);
        viewBillBtn.setText("    View Bill");
        viewBillBtn.setFont(new Font("Arial", Font.BOLD, 25));
        viewBillBtn.setBackground(Color.WHITE);
        viewBillBtn.setFocusPainted(false);
        viewBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new viewbill().setVisible(true);
            }
        });

        ImageIcon updateMedicineIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\updateUser.png");
        JButton updateMedicineBtn = new JButton(updateMedicineIcon);
        updateMedicineBtn.setText("    Update Medicine");
        updateMedicineBtn.setFont(new Font("Arial", Font.BOLD, 25));
        updateMedicineBtn.setBackground(Color.WHITE);
        updateMedicineBtn.setFocusPainted(false);
        updateMedicineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new updatemedicine().setVisible(true);
            }
        });

        ImageIcon profileIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\profileDashboard.png");
        JButton profileBtn = new JButton(profileIcon);
        profileBtn.setText("    Profile");
        profileBtn.setFont(new Font("Arial", Font.BOLD, 25));
        profileBtn.setBackground(Color.WHITE);
        profileBtn.setFocusPainted(false);
        profileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new profile(username).setVisible(true);
            }
        });

        ImageIcon logoutIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\logout.png");
        JButton logoutBtn = new JButton(logoutIcon);
        logoutBtn.setText("    Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 25));
        logoutBtn.setBackground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do you want to logout?", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0){
                    setVisible(false);
                    new loginFrame().setVisible(true);
                }
            }
        });

        ImageIcon exitIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\exit.png");
        JButton exitBtn = new JButton(exitIcon);
        exitBtn.setText("    Exit");
        exitBtn.setFont(new Font("Arial", Font.BOLD, 25));
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null,"Do you want to exit the application?", "Select", JOptionPane.YES_NO_OPTION);
                if(a==0){
                    System.exit(0);
                }
            }
        });

        p1.add(pageTitle);
        p1.add(separator);
        p1.add(addMedicineBtn);
        p1.add(sellMedicineBtn);
        p1.add(viewMedicineBtn);
        p1.add(viewBillBtn);
        p1.add(updateMedicineBtn);
        p1.add(profileBtn);
        p1.add(logoutBtn);
        p1.add(exitBtn);

        pageTitle.setFont(new Font("Arial", Font.BOLD, 30));
        pageTitle.setBounds(dim.width / 2 - 75, 30, 500, 30);
        separator.setBounds(0, 75, getWidth(), 5);
        separator.setBackground(Color.BLACK);

        addMedicineBtn.setBounds(200,100,350,120);
        sellMedicineBtn.setBounds(850, 100, 350, 120);
        viewMedicineBtn.setBounds(200,250,350,120);
        viewBillBtn.setBounds(850, 250, 350, 120);
        updateMedicineBtn.setBounds(200,400,350,120);
        profileBtn.setBounds(850, 400, 350, 120);
        logoutBtn.setBounds(200,550,350,120);
        exitBtn.setBounds(850, 550, 350, 120);

        getContentPane().add(p1);

        setVisible(true);
    }
}
