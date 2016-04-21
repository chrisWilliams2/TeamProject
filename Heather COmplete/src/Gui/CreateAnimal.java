package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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

import javax.swing.UIManager;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTable;


public class CreateAnimal {

	protected JFrame frame;
	protected JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_2;

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
					CreateAnimal window = new CreateAnimal();
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
	public CreateAnimal() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}
	Connection con = null;
	private JTable table;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 610, 269);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 31, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(91, 24, 122, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(21, 68, 46, 14);
		frame.getContentPane().add(lblType);
		
		JLabel lblCreateAnimalAccount = new JLabel("Create Animal Account");
		lblCreateAnimalAccount.setBounds(175, 3, 125, 14);
		frame.getContentPane().add(lblCreateAnimalAccount);
		
		JLabel lblBreed = new JLabel("Breed:");
		lblBreed.setBounds(21, 140, 46, 14);
		frame.getContentPane().add(lblBreed);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 133, 125, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(21, 102, 46, 14);
		frame.getContentPane().add(lblGender);
		
		textField_3 = new JTextField();
		textField_3.setBounds(91, 61, 122, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(91, 95, 125, 28);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(91, 167, 122, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblOwnerId = new JLabel("Owner ID:");
		lblOwnerId.setBounds(21, 173, 70, 16);
		frame.getContentPane().add(lblOwnerId);
		
		JButton btnSaveDetails = new JButton("Save Details");
		btnSaveDetails.setBounds(31, 201, 119, 23);
		btnSaveDetails.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)  
			{
				
		        {
					String Name = textField.getText();
					String Type = textField_1.getText();
					String Gender = textField_4.getText();
					String Breed = textField_3.getText();
					int OwnerID = Integer.parseInt(textField_2.getText());
					 
				
					if(Name.length() < 1 || Type.length() < 1 || Gender.length() < 1 || Breed.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "Please enter information into every field", "Enter all information", 0);
						
					}
					else
					{
						
						//dbConnector connect = new dbConnector();
						ConnectToDatabase.writeDataAnimal(Breed, Gender, Name, Type, OwnerID);
						
						
					
						JOptionPane.showMessageDialog(null, "User created successfully", "Success.", 0);
						frame.dispose();
					}
		        }
				}
			});
		frame.getContentPane().add(btnSaveDetails);
		
		JLabel lblOwnerName = new JLabel("Owner Name:");
		lblOwnerName.setBounds(352, 24, 99, 16);
		frame.getContentPane().add(lblOwnerName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 40, 322, 149);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = table.getSelectedRow();
					String CustID =(table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select * from dbuseraccount.owner where CustID= '"+ CustID +"' ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textField_2.setText(rs.getString("CustID"));
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		table.setBounds(248, 119, 193, 70);
		frame.getContentPane().add(scrollPane);
		
		
		
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(248, 201, 193, 23);
		btnLoad.addActionListener(new ActionListener() 
		{
			//loads the users table from database and displays in the JTable
			public void actionPerformed(ActionEvent arg0) 
			{
				try{
					String query = "select *  from dbuseraccount.owner";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		frame.getContentPane().add(btnLoad);
		
	
		
	}
}
