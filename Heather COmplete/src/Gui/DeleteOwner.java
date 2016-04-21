package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import Database.ConnectToDatabase;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class DeleteOwner {

	protected JFrame frame;
	private JTable table_1;

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
					DeleteOwner window = new DeleteOwner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JButton btnLoad;
	private JScrollPane scrollPane;
	private JLabel lblCustId;
	private JTextField textFieldCustId;
	public void refreshTable()
	{
		try{
			String query = "select * from dbuseraccount.owner";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	/**
	 * Create the application.
	 */
	public DeleteOwner() 
	{
		initialize();
		con = ConnectToDatabase.dbConnector();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 644, 339);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectThePerson = new JLabel("Select the Person You wish to delete");
		lblSelectThePerson.setBounds(115, 11, 200, 14);
		frame.getContentPane().add(lblSelectThePerson);
		JLabel lblFname = new JLabel("FName:");
		lblFname.setBounds(10, 99, 84, 14);
		frame.getContentPane().add(lblFname);
		
		JLabel lblSname = new JLabel("SName:");
		lblSname.setBounds(10, 139, 73, 14);
		frame.getContentPane().add(lblSname);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 171, 84, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(10, 203, 84, 14);
		frame.getContentPane().add(lblPhoneNo);
		
		textField = new JTextField();
		textField.setBounds(106, 92, 86, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 132, 86, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 164, 86, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(106, 196, 86, 28);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 30, 384, 241);
		frame.getContentPane().add(scrollPane);
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = table_1.getSelectedRow();
					String custID =(table_1.getModel().getValueAt(row, 0)).toString();
					String query = "select * from dbuseraccount.owner where CustID= '"+custID+"'";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textFieldCustId.setText(rs.getString("CustId"));
						textField.setText(rs.getString("FName"));
						textField_1.setText(rs.getString("LName"));
						textField_2.setText(rs.getString("Address"));
						textField_5.setText(rs.getString("PhoneNo"));
						
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user?", "Delete User",JOptionPane.YES_NO_OPTION);
				if(action == 0)
				{
				try{
					String query = ("Delete from owner where CustID= '"+ textFieldCustId.getText()+"' " );
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					ps.close();
				
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				refreshTable();
				}
			}
		});
		btnDelete.setBounds(10, 248, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnCancel.setBounds(115, 248, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() 
		{
			//loads the users table from database and displays in the JTable
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String query = "select * from dbuseraccount.owner";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		btnLoad.setBounds(333, 7, 89, 23);
		frame.getContentPane().add(btnLoad);
		
		lblCustId = new JLabel("OwnerID:");
		lblCustId.setBounds(10, 58, 55, 16);
		frame.getContentPane().add(lblCustId);
		
		textFieldCustId = new JTextField();
		textFieldCustId.setEditable(false);
		textFieldCustId.setColumns(10);
		textFieldCustId.setBounds(106, 52, 86, 28);
		frame.getContentPane().add(textFieldCustId);
	}
}
