package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Database.ConnectToDatabase;

import javax.swing.JPasswordField;

public class LoginHome {

	private JFrame frmLogin;
	private JTextField textFieldusername;
	private JPasswordField JPasswordFieldpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			 e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginHome window = new LoginHome();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	PreparedStatement ps =null;
	ResultSet rs = null;
	/**
	 * Create the application.
	 */
	public LoginHome() {
		initialize();
		con = ConnectToDatabase.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					


					String pass = new String((JPasswordFieldpassword.getPassword()));
					
					String login = "Select * From users where username=? and password =?";

					ps=(PreparedStatement) con.prepareStatement(login);
					ps.setString(1, textFieldusername.getText());
					ps.setString(2, pass);	
					

					rs=ps.executeQuery();
					if(rs.next()){
						String role = rs.getString("role");
						String role1 =  "Vet";
						String role2 = "Receptionist";
						String role3 = "Admin";
						
						if(role.equals(role1))
						{
							JOptionPane.showMessageDialog(null, "username and password is correct");
							vet window = new vet();
							window.frmVet.setVisible(true);
							frmLogin.dispose();
						}
						else if(role2.equals(role))
						{
							JOptionPane.showMessageDialog(null, "username and password is correct");
							Receptionist window = new Receptionist();
							window.frame.setVisible(true);
							frmLogin.dispose();
						}
						else if(role3.equals(role))
						{
							JOptionPane.showMessageDialog(null, "username and password is correct");
							AdminGui window3 = new AdminGui();
							window3.frmAdministrator.setVisible(true);
							frmLogin.dispose();
						}
						
					}
					else{
						JOptionPane.showMessageDialog(null, "username and password is not correct");

					}
						
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogin.setBounds(170, 160, 89, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		textFieldusername = new JTextField();
		textFieldusername.setBounds(152, 77, 122, 28);
		frmLogin.getContentPane().add(textFieldusername);
		textFieldusername.setColumns(10);
		
		JPasswordFieldpassword = new JPasswordField();
		JPasswordFieldpassword.setBounds(152, 116, 122, 28);
		frmLogin.getContentPane().add(JPasswordFieldpassword);
		JPasswordFieldpassword.setColumns(10);
		
		JLabel lblusername = new JLabel("Username:");
		lblusername.setBounds(23, 80, 118, 23);
		frmLogin.getContentPane().add(lblusername);
		
		JLabel lblpassword = new JLabel("Password:");
		lblpassword.setBounds(23, 116, 118, 23);
		frmLogin.getContentPane().add(lblpassword);
	}
}
