package Gui;
import Database.ConnectToDatabase;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;


public class CreateUserAccountGui {

	protected JFrame frmCreateUserAccount;
	protected JLabel txtRole;
	protected JLabel txtName;
	protected JLabel txtSurname;
	protected JLabel txtUsername;
	protected JLabel txtPassword;
	protected JTextField textFirstname;
	protected JTextField textSurname;
	protected JTextField textUsername;
	protected JTextField textPassword;
	private JButton btnCancel;
	private JLabel lblConfirmPassword;
	private JTextField textConfirmPass;
	



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
					CreateUserAccountGui window = new CreateUserAccountGui();
					window.frmCreateUserAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	/**
	 * Create the application.
	 */
	public CreateUserAccountGui() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCreateUserAccount = new JFrame();
		frmCreateUserAccount.setTitle("Create user account");
		frmCreateUserAccount.setBounds(100, 100, 450, 310);
		frmCreateUserAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCreateUserAccount.getContentPane().setLayout(null);
		
		JLabel txtpnPleaseEnterThe = new JLabel();
		txtpnPleaseEnterThe.setText("Please enter the following details:");
		txtpnPleaseEnterThe.setBounds(10, 11, 275, 20);
		frmCreateUserAccount.getContentPane().add(txtpnPleaseEnterThe);
		
		txtRole = new JLabel();
		txtRole.setText("Role:");
		txtRole.setBounds(10, 42, 80, 23);
		frmCreateUserAccount.getContentPane().add(txtRole);
		
		txtName = new JLabel();
		txtName.setText("Firstname:");
		txtName.setBounds(10, 69, 80, 28);
		frmCreateUserAccount.getContentPane().add(txtName);
		
		txtSurname = new JLabel();
		txtSurname.setText("Surname:");
		txtSurname.setBounds(10, 100, 80, 28);
		frmCreateUserAccount.getContentPane().add(txtSurname);
		
		txtUsername = new JLabel();
		txtUsername.setText("Username:");
		txtUsername.setBounds(10, 131, 80, 28);
		frmCreateUserAccount.getContentPane().add(txtUsername);
		
		txtPassword = new JLabel();
		txtPassword.setText("Password:");
		txtPassword.setBounds(10, 163, 80, 28);
		frmCreateUserAccount.getContentPane().add(txtPassword);
		
		lblConfirmPassword = new JLabel();
		lblConfirmPassword.setText("Confirm Password:");
		lblConfirmPassword.setBounds(10, 198, 111, 28);
		frmCreateUserAccount.getContentPane().add(lblConfirmPassword);
		
		JRadioButton jrbVet = new JRadioButton("Vet");
		jrbVet.setBounds(121, 42, 63, 25);
		frmCreateUserAccount.getContentPane().add(jrbVet);
		
		JRadioButton jrbReceptionist = new JRadioButton("Receptionist");
		jrbReceptionist.setBounds(187, 42, 104, 25);
		frmCreateUserAccount.getContentPane().add(jrbReceptionist);

		JRadioButton jrbAdmin = new JRadioButton("Admin");
		jrbAdmin.setBounds(303, 42, 104, 25);
		frmCreateUserAccount.getContentPane().add(jrbAdmin);
		
		ButtonGroup group = new ButtonGroup();
		group.add(jrbVet);
		group.add(jrbReceptionist);
		group.add(jrbAdmin);
		

		
		textFirstname = new JTextField();
		textFirstname.setBounds(121, 69, 164, 28);
		frmCreateUserAccount.getContentPane().add(textFirstname);
		textFirstname.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(121, 100, 164, 28);
		frmCreateUserAccount.getContentPane().add(textSurname);
		textSurname.setColumns(10);
		
		textUsername = new JTextField();
		textUsername.setBounds(121, 131, 164, 28);
		frmCreateUserAccount.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(121, 163, 164, 28);
		frmCreateUserAccount.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		textConfirmPass = new JTextField();
		textConfirmPass.setColumns(10);
		textConfirmPass.setBounds(121, 198, 164, 28);
		frmCreateUserAccount.getContentPane().add(textConfirmPass);
		
		JButton btnCreateNewUser = new JButton("Create New  User");
		btnCreateNewUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String role = "";
				String firstName = textFirstname.getText();
				String surName = textSurname.getText();
				String username = textUsername.getText();
				String password = textPassword.getText();
				String confirmPass = textConfirmPass.getText();
				String passOk = "false";
				 
				
				
				if(password.equals(confirmPass) )
				{
					 passOk = "true";
				}
				if(passOk.equals("false"))
				{
					JOptionPane.showMessageDialog(null, "Passwords do not match", "Try again.", 0);	
				}
				if(jrbVet.isSelected()){
					role = "Vet";
				}
				if(jrbReceptionist.isSelected()){
					role = "Receptionist";
				}
				if(jrbAdmin.isSelected()){
					role = "Admin";
				}
				
				if(firstName.length() < 1 || surName.length() < 1 || username.length() < 1 || password.length() < 1 || confirmPass.length() <1 || passOk.equals("false"))
				{
					JOptionPane.showMessageDialog(null, "Please enter information into every field", "Enter all information", 0);
					
				}
				else
				{
					
					//dbConnector connect = new dbConnector();
					ConnectToDatabase.writeData(role,firstName, surName, username, password);
					
					
				
					

					
					JOptionPane.showMessageDialog(null, "User created successfully", "Success.", 0);
					frmCreateUserAccount.dispose();
				}
			}
		});



		btnCreateNewUser.setBounds(10, 237, 104, 28);
		frmCreateUserAccount.getContentPane().add(btnCreateNewUser);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frmCreateUserAccount.dispose();
			}
		});

		btnCancel.setBounds(317, 237, 111, 28);
		frmCreateUserAccount.getContentPane().add(btnCancel);
		
		;
	}
}
