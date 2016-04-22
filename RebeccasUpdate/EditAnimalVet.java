
package Gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import Database.ConnectToDatabase;
import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;


public class EditAnimalVet{

	protected JFrame frmEditAnimal;
	protected JTextField textFieldSearch;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtAnimalID;
	public static JTextField textAnimalName;
	public static JTextField txtType;
	public static JTextField txtBreed;
	protected static String aID;
	protected static String aName;
	protected static String aType;
	protected static String aBreed;
	protected static String aGender;

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
					EditAnimalVet window = new EditAnimalVet();
					window.frmEditAnimal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	private JTextField txtGender;
	
	//reloads the database when called
	public void refreshTable(){
		try{
			String query = "select aniID, Name, Type, Breed, Gender from dbuseraccount.animal";
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
	public EditAnimalVet() {
		initialize();
		con = ConnectToDatabase.dbConnector();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditAnimal = new JFrame();
		frmEditAnimal.setTitle("Edit Animal");
		frmEditAnimal.setBounds(100, 100, 785, 378);
		frmEditAnimal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditAnimal.getContentPane().setLayout(null);
		
		JLabel txtpnPleaseEnterThe = new JLabel();
		txtpnPleaseEnterThe.setText("Please change the details you want to edit:");
		txtpnPleaseEnterThe.setBounds(21, 21, 302, 24);
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
					String query = "select aniID, Name, Type, Breed, Gender from dbuseraccount.animal where name=? ";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.setString(1, textFieldSearch.getText());
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		
		JLabel lblAnimalID = new JLabel();
		lblAnimalID.setText("Animal ID:");
		lblAnimalID.setBounds(16, 57, 106, 29);
		frmEditAnimal.getContentPane().add(lblAnimalID);
		
		
		JLabel lblAnimalName = new JLabel();
		lblAnimalName.setText("Animal Name:");
		lblAnimalName.setBounds(16, 98, 106, 33);
		frmEditAnimal.getContentPane().add(lblAnimalName);
		
		JLabel lblType = new JLabel();
		lblType.setText("Type:");
		lblType.setBounds(16, 143, 106, 33);
		frmEditAnimal.getContentPane().add(lblType);
		
		JLabel lblBreed = new JLabel();
		lblBreed.setText("Breed:");
		lblBreed.setBounds(16, 188, 106, 33);
		frmEditAnimal.getContentPane().add(lblBreed);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(16, 236, 106, 29);
		frmEditAnimal.getContentPane().add(lblGender);
		
		txtAnimalID = new JTextField();
		txtAnimalID.setBackground(UIManager.getColor("Button.darkShadow"));
		txtAnimalID.setEditable(false);//locks animal id so it cannot be changed.
		txtAnimalID.setBounds(159, 57, 122, 33);
		frmEditAnimal.getContentPane().add(txtAnimalID);
		txtAnimalID.setColumns(10);
		
		textAnimalName = new JTextField();
		textAnimalName.setColumns(10);
		textAnimalName.setBounds(159, 98, 122, 33);
		frmEditAnimal.getContentPane().add(textAnimalName);
		
		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBounds(159, 143, 122, 33);
		frmEditAnimal.getContentPane().add(txtType);
		
		txtBreed = new JTextField();
		txtBreed.setBackground(UIManager.getColor("Button.darkShadow"));
		txtBreed.setEditable(false);
		txtBreed.setColumns(10);
		txtBreed.setBounds(159, 188, 122, 33);
		frmEditAnimal.getContentPane().add(txtBreed);
		
		txtGender = new JTextField();
		txtGender.setEditable(false);
		txtGender.setColumns(10);
		txtGender.setBackground((Color) null);
		txtGender.setBounds(159, 233, 122, 33);
		frmEditAnimal.getContentPane().add(txtGender);
		textFieldSearch.setBounds(549, 6, 207, 28);
		frmEditAnimal.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			//allows the user to update animals
			public void actionPerformed(ActionEvent arg0) {
				//get user confirmation of update
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				try{
					String query = ("Update animal set Name= '"+textAnimalName.getText()+"' ,Type= '"+txtType.getText()+"' , Breed= '"+txtBreed.getText()+"'where aniID= '"+txtAnimalID.getText()+"'" );
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
					String query = "select aniID, Name, Type, Breed, Gender from dbuseraccount.animal";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					ex.printStackTrace();
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
					String anId =(table.getModel().getValueAt(row, 0)).toString();
					
					
					String query = "select AniID, Name, Type, Breed, Gender from dbuseraccount.animal where AniID= '"+anId +" ' "; 
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						txtAnimalID.setText(rs.getString("AniID"));
						textAnimalName.setText(rs.getString("Name"));
						txtGender.setText(rs.getString("Gender"));
						txtBreed.setText(rs.getString("Breed"));
						txtType.setText(rs.getString("Type"));
//						textOwnerName.setText(rs.getString("ownerName"));
//						textOwnerID.setText(rs.getString("ownerID"));
						
						aID = rs.getString("AniID");
						aName = rs.getString("Name");
						aType = rs.getString("Type");	
						aBreed = rs.getString("Breed");
						aGender = rs.getString("Gender");
					}
				}catch(Exception ex){
					ex.printStackTrace();
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
		
		JButton btnTreatment = new JButton("New Treatment");
		btnTreatment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					Treatment app = new Treatment();
					app.frmTreatment.setVisible(true);
				}
			}
		});
		
		btnTreatment.setBounds(148, 305, 112, 28);
		frmEditAnimal.getContentPane().add(btnTreatment);
		
		JButton btnTreatmentHistory = new JButton("Treatment History");
		btnTreatmentHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					TreatmentHistory app = new TreatmentHistory();
					app.frmHistory.setVisible(true);
				}
			}
		});
		btnTreatmentHistory.setBounds(291, 305, 134, 28);
		frmEditAnimal.getContentPane().add(btnTreatmentHistory);
	}
	
	protected static String getID()
	{
		return aID;
	}
	protected static String getAname()
	{
		return aName;
	}
	protected static String getType()
	{
		return aType;
	}
	protected static String getBreed()
	{
		return aType;
	}
	protected static String getGender()
	{
		return aType;
	}
}

