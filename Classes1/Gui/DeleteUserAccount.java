package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;
import Database.ConnectToDatabase;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DeleteUserAccount {

	protected JFrame frmDeleteUserAccount;
	protected JLabel txtEmpID;
	protected JTextField textFieldSearch;
	private JTable table;
	private JLabel lblUsername;
	private JLabel lblSurname;
	private JLabel lblFirstName;
	private JLabel lblRole;
	private JTextField textFieldemployeeID;
	private JTextField textFieldRole;
	private JTextField textFieldfirstName;
	private JTextField textFieldsurName;
	private JTextField textFieldusername;
	private JButton btnLoad;
	private JScrollPane scrollPane;

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
					DeleteUserAccount window = new DeleteUserAccount();
					window.frmDeleteUserAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection con = null;
	
	//reloads the database when called
	public void refreshTable(){
		try{
			String query = "select firstName, surName, username, employeeid, role from dbuseraccount.users";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	/**
	 * Create the application.
	 */
	public DeleteUserAccount() {
		initialize();
		con = ConnectToDatabase.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDeleteUserAccount = new JFrame();
		frmDeleteUserAccount.setTitle("Delete User Account");
		frmDeleteUserAccount.setBounds(100, 100, 785, 375);
		frmDeleteUserAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDeleteUserAccount.getContentPane().setLayout(null);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldSearch.setText("");

			}
		});
		textFieldSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldSearch.setText("");

			}
		});
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String query = "select firstName, surName, username, employeeid, role from dbuseraccount.users where firstName=? ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.setString(1, textFieldSearch.getText());
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		textFieldSearch.setText("Search Database");
		textFieldSearch.setBounds(488, 6, 262, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select firstName, surName, username, employeeid, role from dbuseraccount.users";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnLoad.setBounds(325, 6, 90, 28);
		frmDeleteUserAccount.getContentPane().add(btnLoad);
		
		JLabel txtpnPleaseEnterThe = new JLabel();
		txtpnPleaseEnterThe.setText("User Details");
		txtpnPleaseEnterThe.setBounds(86, 55, 89, 20);
		frmDeleteUserAccount.getContentPane().add(txtpnPleaseEnterThe);
		
		txtEmpID = new JLabel();
		txtEmpID.setText("Empyolee ID:");
		txtEmpID.setBounds(18, 102, 74, 28);
		frmDeleteUserAccount.getContentPane().add(txtEmpID);
		
		lblUsername = new JLabel();
		lblUsername.setText("Username:");
		lblUsername.setBounds(18, 262, 74, 28);
		frmDeleteUserAccount.getContentPane().add(lblUsername);
		
		lblSurname = new JLabel();
		lblSurname.setText("Surname:");
		lblSurname.setBounds(18, 222, 74, 28);
		frmDeleteUserAccount.getContentPane().add(lblSurname);
		
		lblFirstName = new JLabel();
		lblFirstName.setText("First name:");
		lblFirstName.setBounds(18, 182, 74, 28);
		frmDeleteUserAccount.getContentPane().add(lblFirstName);
		
		lblRole = new JLabel();
		lblRole.setText("Role:");
		lblRole.setBounds(18, 142, 74, 28);
		frmDeleteUserAccount.getContentPane().add(lblRole);
		
		textFieldemployeeID = new JTextField();
		textFieldemployeeID.setEditable(false);
		textFieldemployeeID.setBounds(133, 102, 154, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldemployeeID);
		textFieldemployeeID.setColumns(10);
		
		textFieldRole = new JTextField();
		textFieldRole.setEditable(false);
		textFieldRole.setColumns(10);
		textFieldRole.setBounds(133, 142, 154, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldRole);
		
		textFieldfirstName = new JTextField();
		textFieldfirstName.setEditable(false);
		textFieldfirstName.setColumns(10);
		textFieldfirstName.setBounds(133, 185, 154, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldfirstName);
		
		textFieldsurName = new JTextField();
		textFieldsurName.setEditable(false);
		textFieldsurName.setColumns(10);
		textFieldsurName.setBounds(133, 222, 154, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldsurName);
		
		textFieldusername = new JTextField();
		textFieldusername.setEditable(false);
		textFieldusername.setColumns(10);
		textFieldusername.setBounds(133, 262, 154, 28);
		frmDeleteUserAccount.getContentPane().add(textFieldusername);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 42, 425, 254);
		frmDeleteUserAccount.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = table.getSelectedRow();
					String employeeid =(table.getModel().getValueAt(row, 3)).toString();
					
					String query = "select firstName, surName, username, employeeid, role from dbuseraccount.users where employeeid= '"+employeeid+"' ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textFieldemployeeID.setText(rs.getString("employeeid"));
						textFieldRole.setText(rs.getString("role"));
						textFieldfirstName.setText(rs.getString("firstName"));
						textFieldsurName.setText(rs.getString("surName"));
						textFieldusername.setText(rs.getString("username"));


						
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get user confirmation of update
				int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user?", "Delete User",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				try{
					String query = ("Delete from users where firstName= '"+textFieldfirstName.getText()+"' " );
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					ps.close();
					
					
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				refreshTable();
			
			}
		});
		btnDelete.setBounds(6, 302, 90, 28);
		frmDeleteUserAccount.getContentPane().add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frmDeleteUserAccount.dispose();
			}
		});
		btnCancel.setBounds(674, 302, 89, 28);
		frmDeleteUserAccount.getContentPane().add(btnCancel);
	}
}
