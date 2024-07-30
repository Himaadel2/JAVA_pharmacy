import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminDashboard extends JFrame {
    private String username;
    adminDashboard(String username){
        this.username = username;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.DARK_GRAY);

        JPanel p1 = new JPanel();
        p1.setLayout(null);

        JLabel pageTitle = new JLabel("Dashboard");
        JSeparator separator = new JSeparator();

        ImageIcon addUserIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\addUser.png");
        JButton addUserBtn = new JButton(addUserIcon);
        addUserBtn.setText("    Add User");
        addUserBtn.setFont(new Font("Arial", Font.BOLD, 25));
        addUserBtn.setBackground(Color.WHITE);
        addUserBtn.setFocusPainted(false);
        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addUser().setVisible(true);
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

        ImageIcon viewUserIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\viewUser.png");
        JButton viewUserBtn = new JButton(viewUserIcon);
        viewUserBtn.setText("    View User");
        viewUserBtn.setFont(new Font("Arial", Font.BOLD, 25));
        viewUserBtn.setBackground(Color.WHITE);
        viewUserBtn.setFocusPainted(false);
        viewUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewUser().setVisible(true);
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

        ImageIcon updateUserIcon = new ImageIcon("D:\\progamming\\java project\\src\\icons\\updateUser.png");
        JButton updateUserBtn = new JButton(updateUserIcon);
        updateUserBtn.setText("    Update User");
        updateUserBtn.setFont(new Font("Arial", Font.BOLD, 25));
        updateUserBtn.setBackground(Color.WHITE);
        updateUserBtn.setFocusPainted(false);
        updateUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new updateUser().setVisible(true);
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
        p1.add(addUserBtn);
        p1.add(profileBtn);
        p1.add(viewUserBtn);
        p1.add(logoutBtn);
        p1.add(updateUserBtn);
        p1.add(exitBtn);

        pageTitle.setFont(new Font("Arial", Font.BOLD, 30));
        pageTitle.setBounds(dim.width / 2 - 75, 30, 500, 30);
        separator.setBounds(0, 75, getWidth(), 5);
        separator.setBackground(Color.BLACK);

        addUserBtn.setBounds(200,100,350,150);
        profileBtn.setBounds(850, 100, 350, 150);
        viewUserBtn.setBounds(200,300,350,150);
        logoutBtn.setBounds(850, 300, 350, 150);
        updateUserBtn.setBounds(200,500,350,150);
        exitBtn.setBounds(850, 500, 350, 150);

        getContentPane().add(p1);

        setVisible(true);
    }
}
