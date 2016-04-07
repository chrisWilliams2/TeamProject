package Gui;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import net.proteanit.sql.DbUtils;
import Database.ConnectToDatabase;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

public class AppointmentGui {

	protected JFrame frmAppointment;
	public static JButton jbtnLoad;
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
					AppointmentGui window = new AppointmentGui();
					window.frmAppointment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void CurrentDate(){
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		lblDate.setText(day+"/"+(month+1)+"/"+year);

		

	}
	Connection con = null;
	private JTable table;
	private JTextField textFieldTime;
	private JTextField textFieldDay;
	private JTextField textFieldAnimalName;
	private JLabel lblPetType;
	private JTextField textFieldPetType;
	private JLabel lblDate;
	private JLabel lblOwnerID;
	private JLabel lblInjury;
	private JTextField textFieldCustID;
	private JTextField textFieldInjury;
	private JComboBox<String> comboBox;
	private JTextField textFieldVet;
	
	//reloads the database when called
	public void refreshTable(){
		try{
			String query = "select * from dbuseraccount.scheduler";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	//fills the combobox with vets id
	public void fillCombo(){
		String Vet = "Vet";
		try {
			String query = "Select firstName from dbuseraccount.users where role='"+Vet+"'";

			PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String name= rs.getString("firstName");
				comboBox.addItem(name);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Create the application.
	 */
	public AppointmentGui() {
		initialize();
		con = ConnectToDatabase.dbConnector();
		CurrentDate();
		fillCombo();

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frmAppointment = new JFrame();
		frmAppointment.setTitle("Appointment Schedule");
		frmAppointment.setBounds(100, 100, 786, 471);
		frmAppointment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAppointment.getContentPane().setLayout(null);
		
		
		jbtnLoad = new JButton();
		jbtnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date = lblDate.getText();
				try{
					String createTableDate = "CREATE TABLE IF NOT EXISTS '"+date+"'" + 
							 "(Date VARCHAR(255), " +
							 "(Time VARCHAR(255), " +
							 " Monday VARCHAR, " +
							 " Tuesday VARCHAR, " +
							 " Wednesday VARCHAR, " +
							 " Thursday VARCHAR, " +
							 " Friday VARCHAR, " + 		
							 " Saturday VARCHAR, " +
							 " PRIMARY KEY (Date)"+
							 " FOREIGN KEY(Time)";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(createTableDate);
					 ps.executeUpdate();
					//table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
				//loads the table
				try{
					String query = "select * from dbuseraccount.scheduler";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
				String temp = (String)comboBox.getSelectedItem();
				String query = "Select firstName from users where firstName = ?";
				try {
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.setString(1, temp);
					ResultSet rs = ps.executeQuery();
					if(rs.next()){
						String add = rs.getString("firstName");
						textFieldVet.setText(add);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}	
			});
		jbtnLoad.setText("Load Appointments ");
		jbtnLoad.setBounds(255, 24, 147, 28);
		frmAppointment.getContentPane().add(jbtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(216, 57, 548, 369);
		frmAppointment.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					
					int row = table.getSelectedRow();
					String time =(table.getModel().getValueAt(row,0 )).toString();
					
					
					String query = "select * from dbuseraccount.scheduler where Time= '"+time+"'";

					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()){
						
						textFieldTime.setText(rs.getString("Time"));
					}
				}catch(Exception ex){
					ex.printStackTrace();;
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(6, 57, 91, 28);
		frmAppointment.getContentPane().add(lblTime);
		
		textFieldTime = new JTextField();
		textFieldTime.setEditable(false);
		textFieldTime.setBounds(95, 57, 122, 28);
		frmAppointment.getContentPane().add(textFieldTime);
		textFieldTime.setColumns(10);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(6, 91, 91, 23);
		frmAppointment.getContentPane().add(lblDay);
		
		textFieldDay = new JTextField();
		textFieldDay.setColumns(10);
		textFieldDay.setBounds(95, 88, 122, 28);
		frmAppointment.getContentPane().add(textFieldDay);
		
		JLabel lblAnimalID = new JLabel("Animal ID:");
		lblAnimalID.setBounds(6, 163, 91, 23);
		frmAppointment.getContentPane().add(lblAnimalID);
		
		textFieldAnimalName = new JTextField();
		textFieldAnimalName.setColumns(10);
		textFieldAnimalName.setBounds(95, 160, 122, 28);
		frmAppointment.getContentPane().add(textFieldAnimalName);
		
		lblPetType = new JLabel("Pet Type:");
		lblPetType.setBounds(6, 202, 91, 23);
		frmAppointment.getContentPane().add(lblPetType);
		
		textFieldPetType = new JTextField();
		textFieldPetType.setColumns(10);
		textFieldPetType.setBounds(95, 199, 122, 28);
		frmAppointment.getContentPane().add(textFieldPetType);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(0, 6, 62, 34);
		frmAppointment.getContentPane().add(lblDate);
		
		lblOwnerID = new JLabel("Owner ID:");
		lblOwnerID.setBounds(6, 126, 91, 23);
		frmAppointment.getContentPane().add(lblOwnerID);
		
		lblInjury = new JLabel("Injury:");
		lblInjury.setBounds(6, 250, 91, 23);
		frmAppointment.getContentPane().add(lblInjury);
		
		textFieldCustID = new JTextField();
		textFieldCustID.setColumns(10);
		textFieldCustID.setBounds(95, 123, 122, 28);
		frmAppointment.getContentPane().add(textFieldCustID);
		
		textFieldInjury = new JTextField();
		textFieldInjury.setColumns(10);
		textFieldInjury.setBounds(95, 247, 122, 28);
		frmAppointment.getContentPane().add(textFieldInjury);
		
		JButton btnCreateAppointment = new JButton("Create Appointment");
		btnCreateAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				//dbConnector connect = new dbConnector();
				//ConnectToDatabase.writeDataAppointment( date,custId,aniId,petType,injury);
				
				
			
				

				
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				try{
					String query = ("Update scheduler set date= '"+textFieldDay.getText()+"' , custID= '"+textFieldCustID.getText()+"' ,AniID ='"+textFieldAnimalName.getText()+"' , PetType= '"+textFieldPetType.getText()+"' , Injury= '"+textFieldInjury.getText()+"'where Time= '"+textFieldTime.getText()+"'" );
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					ps.close();
					
					
					
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				refreshTable();
			}
		});
		btnCreateAppointment.setBounds(25, 347, 154, 28);
		frmAppointment.getContentPane().add(btnCreateAppointment);
		
		comboBox = new JComboBox<String>();
	
		comboBox.setBounds(60, 18, 189, 34);
		frmAppointment.getContentPane().add(comboBox);
		
		textFieldVet = new JTextField();
		textFieldVet.setColumns(10);
		textFieldVet.setBounds(95, 285, 122, 28);
		frmAppointment.getContentPane().add(textFieldVet);
		
		JLabel labelVet = new JLabel("Vet:");
		labelVet.setBounds(6, 285, 91, 23);
		frmAppointment.getContentPane().add(labelVet);
		
		
		
		
	}
}
