package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Database.ConnectToDatabase;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class animals {

	protected JFrame frmVet;

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
					animals window = new animals();
					window.frmVet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	java.sql.Connection con = null;
	private JTable table;


	/**
	 * Create the application.
	 */
	public animals() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVet = new JFrame();
		frmVet.setTitle("Vet");
		frmVet.setBounds(100, 100, 536, 328);
		frmVet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVet.getContentPane().setLayout(null);
		
		JButton btnSelect = new JButton("Refresh");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					try{
						String query = "select * from dbuseraccount.animal";   //will need to change to animal table
						PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch(Exception ex){
						System.out.println(ex);
					}
				}
			}
		});
		btnSelect.setBounds(295, 255, 89, 23);
		frmVet.getContentPane().add(btnSelect);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 23, 450, 223);
		frmVet.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel txtpnAnimals = new JLabel();
		txtpnAnimals.setText("Animals:");
		txtpnAnimals.setBounds(10, 0, 99, 23);
		frmVet.getContentPane().add(txtpnAnimals);
		
		JButton btnSelect_1 = new JButton("Select");
		btnSelect_1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				
				
			}
		});
		btnSelect_1.setBounds(150, 255, 89, 23);
		frmVet.getContentPane().add(btnSelect_1);
	}
}
