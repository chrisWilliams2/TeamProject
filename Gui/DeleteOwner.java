package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
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

public class DeleteOwner {

	protected JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnLoad;
	public void refreshTable()
	{
		try{
			String query = "select * from dbuseraccount.Owner";
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
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 518, 339);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectThePerson = new JLabel("Select the Person You wish to delete");
		lblSelectThePerson.setBounds(115, 11, 200, 14);
		frame.getContentPane().add(lblSelectThePerson);
		JLabel lblFname = new JLabel("FName:");
		lblFname.setBounds(10, 56, 60, 14);
		frame.getContentPane().add(lblFname);
		
		JLabel lblSname = new JLabel("SName:");
		lblSname.setBounds(10, 97, 46, 14);
		frame.getContentPane().add(lblSname);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 126, 46, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No:");
		lblPhoneNo.setBounds(10, 203, 50, 14);
		frame.getContentPane().add(lblPhoneNo);
		
		textField = new JTextField();
		textField.setBounds(73, 56, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 94, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(73, 123, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(73, 146, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(73, 169, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(73, 200, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		table = new JTable();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					
					String query = "select * from dbuseraccount.Owner";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textField.setText(rs.getString("FName"));
						textField_1.setText(rs.getString("SName"));
						textField_2.setText(rs.getString("Address"));
						textField_3.setText(rs.getString(""));
						textField_4.setText(rs.getString(""));
						textField_5.setText(rs.getString("PhoneNo"));
						
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		table.setBounds(224, 30, 268, 198);
		frame.getContentPane().add(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user?", "Delete User",JOptionPane.YES_NO_OPTION);
				if(action == 0)
				{
				try{
					String query = ("Delete from owner where FName= '"+ textField.getText()+"' " );
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
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		btnLoad.setBounds(309, 248, 89, 23);
		frame.getContentPane().add(btnLoad);
	}
}
