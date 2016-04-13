package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;


public class AdminGui {

	JFrame frmAdministrator;

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
					AdminGui window = new AdminGui();
					window.frmAdministrator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministrator = new JFrame();
		frmAdministrator.setTitle("Administrator");
		frmAdministrator.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmAdministrator.setBounds(100, 100, 403, 300);
		frmAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministrator.getContentPane().setLayout(null);
		
		JButton btnListUsers = new JButton("List User Accounts");
		btnListUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnListUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd == 1)
				{
					ListUsersGui window = new ListUsersGui();
					window.frmListUsers.setVisible(true);
				}
			}
		});
		btnListUsers.setForeground(Color.BLACK);
		
		btnListUsers.setToolTipText("List all recorded user accounts.");
		btnListUsers.setBounds(16, 27, 154, 45);
		frmAdministrator.getContentPane().add(btnListUsers);
		
		JButton btnCreateUsers = new JButton("Create User Account");
	
		btnCreateUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd == 1)
				{
					CreateUserAccountGui window = new CreateUserAccountGui();
					window.frmCreateUserAccount.setVisible(true);
				}
			}
		});
		btnCreateUsers.setToolTipText("Create a new user account");
		btnCreateUsers.setBounds(215, 27, 154, 45);
		frmAdministrator.getContentPane().add(btnCreateUsers);
		
		JButton btnEditUserAccount = new JButton("Edit User Account");
		btnEditUserAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd == 1){
					EditUserGui window = new EditUserGui();
					window.frmEditUserAccount.setVisible(true);
				}
			}
		});
		btnEditUserAccount.setToolTipText("Edit a user account");
		btnEditUserAccount.setBounds(215, 77, 154, 45);
		frmAdministrator.getContentPane().add(btnEditUserAccount);
		
		JButton btnDeleteUserAccount = new JButton("Delete User Account");
		btnDeleteUserAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd == 1){
					DeleteUserAccount window = new DeleteUserAccount();
					window.frmDeleteUserAccount.setVisible(true);
				}
			}
		});
		btnDeleteUserAccount.setToolTipText("Delete a user account");
		btnDeleteUserAccount.setBounds(16, 77, 154, 45);
		frmAdministrator.getContentPane().add(btnDeleteUserAccount);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAdministrator.dispose();
			}
		});
		btnLogout.setBounds(101, 181, 200, 45);
		frmAdministrator.getContentPane().add(btnLogout);
		 
		
	}
}
