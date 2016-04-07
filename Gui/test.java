package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Database.ConnectToDatabase;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JCalendar;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

public class test {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	/**
	 * Create the application.
	 */
	public test() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1006, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(10, 11, 198, 153);
		frame.getContentPane().add(calendar);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int day = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
				System.out.println("day = " +day);
			}
		});
		btnNewButton.setBounds(10, 184, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(198, 318, 95, 20);
		frame.getContentPane().add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 110, 503, 297);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select e.employeeid,idStaffTimeTable,idday_of_week,idTimeSlots"
							+ " from users e ,stafftimetable s where e.employeeid = s.employeeid";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		btnNewButton_1.setBounds(444, 33, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		/*//sets current date
				try {
					String query = "Update dbuseraccount.scheduler set date = '"+lblDate.getText()+"'";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Data Updated");
					ps.close();
					
					
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				refreshTable();*/
	}
}
