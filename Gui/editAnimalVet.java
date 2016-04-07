package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

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


public class editAnimalVet{

	protected JFrame frmEditAnimal;
	protected JTextField textFieldSearch;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldanimalID;
	public static JTextField textAnimalName;
	public static JTextField textOwnerName;
	public static JTextField textOwnerID;

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
					editAnimalVet window = new editAnimalVet();
					window.frmEditAnimal.setVisible(true);
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
			String query = "select animalID, name, ownerName, ownerID from dbuseraccount.animals";
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
	public editAnimalVet() {
		initialize();
		con = ConnectToDatabase.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditAnimal = new JFrame();
		frmEditAnimal.setTitle("Edit User Account");
		frmEditAnimal.setBounds(100, 100, 785, 378);
		frmEditAnimal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditAnimal.getContentPane().setLayout(null);
		
		JLabel txtpnPleaseEnterThe = new JLabel();
		txtpnPleaseEnterThe.setText("Please change the details you want to edit:");
		txtpnPleaseEnterThe.setBounds(16, 46, 302, 24);
		frmEditAnimal.getContentPane().add(txtpnPleaseEnterThe);
		
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
					String query = "select animalID, name, ownerName, ownerID from dbuseraccount.animals where name=? ";
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
		labelEmpID.setText("Animal ID:");
		labelEmpID.setBounds(16, 82, 106, 29);
		frmEditAnimal.getContentPane().add(labelEmpID);
		
		
		JLabel lblAnimalName = new JLabel();
		lblAnimalName.setText("Animal Name:");
		lblAnimalName.setBounds(16, 125, 106, 33);
		frmEditAnimal.getContentPane().add(lblAnimalName);
		
		JLabel lblFirstname = new JLabel();
		lblFirstname.setText("Owner Name:");
		lblFirstname.setBounds(16, 170, 106, 33);
		frmEditAnimal.getContentPane().add(lblFirstname);
		
		JLabel lblSurname = new JLabel();
		lblSurname.setText("Owner ID:");
		lblSurname.setBounds(16, 215, 106, 33);
		frmEditAnimal.getContentPane().add(lblSurname);
		
		textFieldanimalID = new JTextField();
		textFieldanimalID.setBackground(UIManager.getColor("Button.darkShadow"));
		textFieldanimalID.setEditable(false);//locks animal id so it cannot be changed.
		textFieldanimalID.setBounds(159, 80, 122, 33);
		frmEditAnimal.getContentPane().add(textFieldanimalID);
		textFieldanimalID.setColumns(10);
		
		textAnimalName = new JTextField();
		textAnimalName.setColumns(10);
		textAnimalName.setBounds(159, 125, 122, 33);
		frmEditAnimal.getContentPane().add(textAnimalName);
		
		textOwnerName = new JTextField();
		textOwnerName.setColumns(10);
		textOwnerName.setBounds(159, 170, 122, 33);
		frmEditAnimal.getContentPane().add(textOwnerName);
		
		textOwnerID = new JTextField();
		textOwnerID.setBackground(UIManager.getColor("Button.darkShadow"));
		textOwnerID.setEditable(false);
		textOwnerID.setColumns(10);
		textOwnerID.setBounds(159, 215, 122, 33);
		frmEditAnimal.getContentPane().add(textOwnerID);
		textFieldSearch.setBounds(549, 6, 207, 28);
		frmEditAnimal.getContentPane().add(textFieldSearch);
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
					String query = ("Update users set role= '"+textAnimalName.getText()+"' ,ownerName= '"+textOwnerName.getText()+"' , ownerID= '"+textOwnerID.getText()+"'where animalID= '"+textFieldanimalID.getText()+"'" );
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
					String query = "select animalID, name, ownerName, ownerID from dbuseraccount.animals";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
			
		});
		
		btnLoad.setBounds(373, 4, 90, 28);
		frmEditAnimal.getContentPane().add(btnLoad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 42, 433, 251);
		frmEditAnimal.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try{
					int row = table.getSelectedRow();
					String employeeid =(table.getModel().getValueAt(row, 0)).toString();
					
					String query = "select animalID, name, ownerName, ownerID from dbuseraccount.animals where animalID= '"+employeeid+"' ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						textFieldanimalID.setText(rs.getString("animalID"));
						textAnimalName.setText(rs.getString("name"));
						textOwnerName.setText(rs.getString("ownerName"));
						textOwnerID.setText(rs.getString("ownerID"));



						
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		scrollPane.setViewportView(table);
		btnConfirm.setBounds(6, 305, 90, 28);
		frmEditAnimal.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frmEditAnimal.dispose();
			}
		});
		btnCancel.setBounds(666, 305, 90, 28);
		frmEditAnimal.getContentPane().add(btnCancel);
	}
}
