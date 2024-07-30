import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginFrame extends JFrame {
    JLabel userNameLabel;
    JLabel passwordLabel;
    JTextField userNameText;
    JTextField passwordText;
    JButton loginBtn;
    JButton resetBtn;

    public loginFrame() {
        setTitle("Login");
        setSize(500, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        userNameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        userNameText = new JTextField();
        passwordText = new JPasswordField();
        loginBtn = new JButton("Login");
        resetBtn = new JButton("Reset");
        p1.add(userNameLabel);
        p1.add(passwordLabel);
        p1.add(userNameText);
        p1.add(passwordText);
        p1.add(loginBtn);
        p1.add(resetBtn);
        userNameLabel.setBounds(100, 75, 75, 25);
        passwordLabel.setBounds(100, 125, 75, 25);
        userNameText.setBounds(200, 75, 200, 25);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                userNameText.requestFocusInWindow();
            }
        });
        userNameText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBtn.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        passwordText.setBounds(200, 125, 200, 25);
        passwordText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBtn.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        loginBtn.setBounds(100, 175, 100, 25);
        resetBtn.setBounds(300, 175, 100, 25);
        this.add(p1);
        p1.setBounds(0, 0, 600, 400);
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNameText.setText("");
                passwordText.setText("");
            }
        });
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                     Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy?useSSL=false","root","1234");
                    String username = userNameText.getText();
                    String password = passwordText.getText();
                    Statement st = con.createStatement();
                    String sql = "SELECT * FROM pharmacy.appuser WHERE username = '" + username + "' AND password = '" + password + "'";
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        dispose();
                        if(rs.getString("user_role").equals("Admin")){
                            new adminDashboard(username).setVisible(true);
                        }else{
                            new pharmacistDashboard(username).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong username or password", "Failed to login", JOptionPane.ERROR_MESSAGE);
                        userNameText.setText("");
                        passwordText.setText("");
                    }
                    con.close();
                }
                catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
