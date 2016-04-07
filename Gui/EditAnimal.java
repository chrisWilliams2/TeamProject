package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import Database.ConnectToDatabase;
import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTable;
import javax.swing.JTextField;

public class EditAnimal {

	protected JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	Connection con = null;
	
	public void refreshTable()
	{
		try{
			String query = "select * from dbuseraccount.Animal";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAnimal window = new EditAnimal();
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
	
	public EditAnimal() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		frame.setBounds(100, 100, 555, 362);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseSelectThe = new JLabel("Please Select The Animal You Would Like To Edit");
		lblPleaseSelectThe.setBounds(119, 11, 251, 14);
		frame.getContentPane().add(lblPleaseSelectThe);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action == 0)
				try{
					String query = ("Update Animal Name= '"+ textField.getText()+"' ,Type= '"+ textField_1.getText()+"' , Gender = '"+ textField_2 +"' , Breed ='"+ textField_5.getText()+"'" );
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
		btnEdit.setBounds(6, 258, 89, 28);
		frame.getContentPane().add(btnEdit);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					
					String query = "select * from dbuseraccount.Animal";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textField.setText(rs.getString("Name"));
						textField_1.setText(rs.getString("Gender"));
						textField_2.setText(rs.getString("Type"));
						textField_3.setText(rs.getString("Breed"));
						
						
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		table.setBounds(258, 37, 243, 236);
		frame.getContentPane().add(table);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() 
		{
			//loads the users table from database and displays in the JTable
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String query = "select * from dbuseraccount.Animal";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		btnLoad.setBounds(332, 285, 90, 28);
		frame.getContentPane().add(btnLoad);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
			}
		});
		btnCancel.setBounds(121, 258, 90, 28);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 43, 55, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(6, 81, 55, 16);
		frame.getContentPane().add(lblType);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(6, 119, 55, 16);
		frame.getContentPane().add(lblGender);
		
		JLabel lblBreed = new JLabel("Breed");
		lblBreed.setBounds(6, 157, 55, 16);
		frame.getContentPane().add(lblBreed);
		
		textField = new JTextField();
		textField.setBounds(89, 37, 122, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 75, 122, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(89, 113, 122, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(89, 151, 122, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblOwnerId = new JLabel("Owner ID:");
		lblOwnerId.setBounds(6, 206, 55, 16);
		frame.getContentPane().add(lblOwnerId);
		
		textField_5 = new JTextField();
		textField_5.setBounds(89, 200, 122, 28);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
	}
}
