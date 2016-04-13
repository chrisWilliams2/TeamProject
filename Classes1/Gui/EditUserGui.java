package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.MessageFormat;

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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.print.PrinterException;


public class EditUserGui {

	protected JFrame frmEditUserAccount;
	protected JTextField textFieldSearch;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldemployeeID;
	public static JTextField textFieldRole;
	public static JTextField textFieldfirstName;
	public static JTextField textFieldsurName;
	public static JTextField textFieldusername;

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
					EditUserGui window = new EditUserGui();
					window.frmEditUserAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	private JButton btnPrint;
	
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
	public EditUserGui() {
		initialize();
		con = ConnectToDatabase.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditUserAccount = new JFrame();
		frmEditUserAccount.setTitle("Edit User Account");
		frmEditUserAccount.setBounds(100, 100, 785, 378);
		frmEditUserAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditUserAccount.getContentPane().setLayout(null);
		
		JLabel txtpnPleaseEnterThe = new JLabel();
		txtpnPleaseEnterThe.setText("Please change the details you want to edit:");
		txtpnPleaseEnterThe.setBounds(16, 46, 302, 24);
		frmEditUserAccount.getContentPane().add(txtpnPleaseEnterThe);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addFocusListener(new FocusAdapter() {	
			//clear the searchbox when tabbed to
			public void focusGained(FocusEvent arg0) {
				textFieldSearch.setText("");

			}
		});
		textFieldSearch.addMouseListener(new MouseAdapter() {
			
			//clear the search box when clicked
			public void mouseClicked(MouseEvent arg0) {
				textFieldSearch.setText("");
			}
		});
		textFieldSearch.setText("Search Database");
		textFieldSearch.addKeyListener(new KeyAdapter() {
			
			//gathers all records from database where the first name is a match
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
		
		JLabel labelEmpID = new JLabel();
		labelEmpID.setText("EmployeeID:");
		labelEmpID.setBounds(16, 82, 106, 29);
		frmEditUserAccount.getContentPane().add(labelEmpID);
		
		
		JLabel label = new JLabel();
		label.setText("Role:");
		label.setBounds(16, 125, 106, 33);
		frmEditUserAccount.getContentPane().add(label);
		
		JLabel lblFirstname = new JLabel();
		lblFirstname.setText("Firstname:");
		lblFirstname.setBounds(16, 170, 106, 33);
		frmEditUserAccount.getContentPane().add(lblFirstname);
		
		JLabel lblSurname = new JLabel();
		lblSurname.setText("Surname:");
		lblSurname.setBounds(16, 215, 106, 33);
		frmEditUserAccount.getContentPane().add(lblSurname);
		
		JLabel lblusername = new JLabel();
		lblusername.setText("Username:");
		lblusername.setBounds(16, 260, 106, 33);
		frmEditUserAccount.getContentPane().add(lblusername);
		
		textFieldemployeeID = new JTextField();
		textFieldemployeeID.setEditable(false);//locks employee id so it cannot be changed.
		textFieldemployeeID.setBounds(159, 80, 122, 33);
		frmEditUserAccount.getContentPane().add(textFieldemployeeID);
		textFieldemployeeID.setColumns(10);
		
		textFieldRole = new JTextField();
		textFieldRole.setColumns(10);
		textFieldRole.setBounds(159, 125, 122, 33);
		frmEditUserAccount.getContentPane().add(textFieldRole);
		
		textFieldfirstName = new JTextField();
		textFieldfirstName.setColumns(10);
		textFieldfirstName.setBounds(159, 170, 122, 33);
		frmEditUserAccount.getContentPane().add(textFieldfirstName);
		
		textFieldsurName = new JTextField();
		textFieldsurName.setColumns(10);
		textFieldsurName.setBounds(159, 215, 122, 33);
		frmEditUserAccount.getContentPane().add(textFieldsurName);
		
		textFieldusername = new JTextField();
		textFieldusername.setColumns(10);
		textFieldusername.setBounds(159, 260, 122, 33);
		frmEditUserAccount.getContentPane().add(textFieldusername);
		textFieldSearch.setBounds(549, 6, 207, 28);
		frmEditUserAccount.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			//allows the user to update users firstName, surName, role and username
			//uses empolyeeid to update correct record
			public void actionPerformed(ActionEvent arg0) {
				//get user confirmation of update
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				try{
					String query = ("Update users set role= '"+textFieldRole.getText()+"' ,firstName= '"+textFieldfirstName.getText()+"' , surName= '"+textFieldsurName.getText()+"' ,username ='"+textFieldusername.getText()+"'where employeeid= '"+textFieldemployeeID.getText()+"'" );
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					ps.close();
					
					
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				refreshTable();
			}
		}
		);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			//loads the users table from database and displays in the JTable
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
		
		btnLoad.setBounds(334, 6, 90, 28);
		frmEditUserAccount.getContentPane().add(btnLoad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 42, 433, 251);
		frmEditUserAccount.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			// use up and down arrows on table
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode()==KeyEvent.VK_DOWN||event.getKeyCode()==KeyEvent.VK_UP ){
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
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
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
		btnConfirm.setBounds(6, 305, 90, 28);
		frmEditUserAccount.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frmEditUserAccount.dispose();
			}
		});
		btnCancel.setBounds(666, 305, 90, 28);
		frmEditUserAccount.getContentPane().add(btnCancel);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header = new MessageFormat("Table Print");
				MessageFormat footer = new MessageFormat("Page");// sort pasge number
				try {
					table.print(JTable.PrintMode.NORMAL, header,footer);
				} catch (PrinterException e) {
					System.err.format("Cannot Print ", e.getMessage())	;		
					}

				
			}
		});
		btnPrint.setBounds(436, 6, 90, 28);
		frmEditUserAccount.getContentPane().add(btnPrint);
	}
}
