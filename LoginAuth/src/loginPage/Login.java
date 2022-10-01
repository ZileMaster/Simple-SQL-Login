package loginPage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame{

	private JFrame frame;
	private JTextField tfUser;
	private JPasswordField tfPass;
	public String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(75, 39, 71, 21);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(75, 89, 71, 21);
		frame.getContentPane().add(lblPass);
		
		tfUser = new JTextField();
		tfUser.setBounds(192, 32, 140, 28);
		frame.getContentPane().add(tfUser);
		tfUser.setColumns(10);
		
		tfPass = new JPasswordField();
		tfPass.setBounds(192, 81, 140, 28);
		frame.getContentPane().add(tfPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login1?useSSL=false", "root", "password");
					
					String user = tfUser.getText();
					String pass = tfPass.getText();
					
					Statement stm = con.createStatement();
					String sql = "select * from login where username='" + user + "' and password='" + pass +"'";
					
					ResultSet rs = stm.executeQuery(sql);
					
					if(rs.next()) {
						frame.dispose();
						HomePage hpage = new HomePage();
						hpage.show();
					}else {

						JOptionPane.showMessageDialog(null, "Invalid personel!!!");
						tfPass.setText("");
						tfUser.setText("");
					}
					
					con.close();
					
				}catch (Exception er) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(btnLogin, er);
				}
			}
		});
		btnLogin.setBounds(77, 164, 105, 43);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPass.setText("");
				tfUser.setText("");
			
			}
		});
		btnReset.setBounds(227, 164, 105, 43);
		frame.getContentPane().add(btnReset);
	}
}

