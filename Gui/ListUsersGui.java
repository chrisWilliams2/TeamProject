package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class ListUsersGui {

	protected JFrame frmListUsers;
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
					ListUsersGui window = new ListUsersGui();
					window.frmListUsers.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	private JTable table;
	/**
	 * Create the application.
	 */
	public ListUsersGui() {
		initialize();
		con = ConnectToDatabase.dbConnector();

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		
		frmListUsers = new JFrame();
		frmListUsers.setTitle("List Users");
		frmListUsers.setBounds(100, 100, 616, 325);
		frmListUsers.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmListUsers.getContentPane().setLayout(null);
		
		jbtnLoad = new JButton();
		jbtnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select * from dbuseraccount.users";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		});
		jbtnLoad.setText("Load User Data");
		jbtnLoad.setBounds(134, 23, 122, 28);
		frmListUsers.getContentPane().add(jbtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 57, 450, 223);
		frmListUsers.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
