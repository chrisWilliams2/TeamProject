package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Database.ConnectToDatabase;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JTable;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

public class EditOwner {

	protected JFrame frame;

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
					EditOwner window = new EditOwner();
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
	Connection con = null;

	public void refreshTable()
	{
		try{
			String query = "select * from dbuseraccount.owner";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JLabel lblCustId;
	private JTextField textFieldCustID;
	public EditOwner() 
	{
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 359);
		frame.getContentPane().setLayout(null);
		
		lblCustId = new JLabel("Owner ID:");
		lblCustId.setBounds(6, 44, 70, 16);
		frame.getContentPane().add(lblCustId);
		
		JLabel lblFname = new JLabel("First Name:");
		lblFname.setBounds(6, 80, 70, 16);
		frame.getContentPane().add(lblFname);
		
		JLabel lblSname = new JLabel("Surname:");
		lblSname.setBounds(6, 120, 70, 16);
		frame.getContentPane().add(lblSname);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(6, 163, 70, 16);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(6, 206, 70, 16);
		frame.getContentPane().add(lblPhoneNo);
		
		textFieldCustID = new JTextField();
		textFieldCustID.setColumns(10);
		textFieldCustID.setBounds(68, 40, 122, 28);
		frame.getContentPane().add(textFieldCustID);
		
		textField = new JTextField();
		textField.setBounds(68, 74, 122, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(68, 114, 122, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(68, 157, 122, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(68, 200, 122, 28);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(257, 19, 373, 215);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = table_1.getSelectedRow();
					String CustID =(table_1.getModel().getValueAt(row, 0)).toString();
					
					String query = "select * from dbuseraccount.owner where CustID= '"+ CustID +"' ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textFieldCustID.setText(rs.getString("CustID"));
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
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() 
		{
			//loads the users table from database and displays in the JTable
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String query = "select *  from dbuseraccount.owner";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		btnLoad.setBounds(413, 269, 90, 28);
		frame.getContentPane().add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action == 0)
				try{
					String query = ("Update owner set FName= '"+ textField.getText()+"' ,LName= '"+ textField_1.getText()+"' , Address= '"+ textField_2.getText()+"' , PhoneNo= '"+ textField_5.getText()+"'" );
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					ps.close();
					
				}
				catch(Exception ex )
				{
					ex.printStackTrace();
				}
				refreshTable();
			}
		});
		btnSave.setBounds(6, 269, 90, 28);
		frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnCancel.setBounds(108, 269, 90, 28);
		frame.getContentPane().add(btnCancel);
		
		
		
	}
}
