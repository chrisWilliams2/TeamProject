package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import Database.ConnectToDatabase;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TreatmentHistory {

	protected JFrame frmHistory;
	private JTable table;


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
					TreatmentHistory window = new TreatmentHistory();
					window.frmHistory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con = null;
	private JScrollPane treatmentView;

	/**
	 * Create the application.
	 */
	public TreatmentHistory() {
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHistory = new JFrame();
		frmHistory.setTitle("Treatment History");
		frmHistory.setBounds(100, 100, 805, 334);
		frmHistory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHistory.getContentPane().setLayout(null);
		
		treatmentView = new JScrollPane();
		treatmentView.setBounds(6, 17, 784, 211);
		frmHistory.getContentPane().add(treatmentView);
		table = new JTable();
		treatmentView.setViewportView(table);
		
	
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					try{
						String animal = EditAnimalVet.getID();
						String query = ("select SessionID, Date, AnimalID, Treatment from dbuseraccount.treatment where AnimalID = '"+animal+"' ");
						PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
						ResultSet rs = ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						TableColumnModel columnModel = table.getColumnModel();
						columnModel.getColumn(0).setPreferredWidth(30);
						columnModel.getColumn(1).setPreferredWidth(40);
						columnModel.getColumn(2).setPreferredWidth(30);
						columnModel.getColumn(3).setPreferredWidth(500);
						
					}catch(Exception ex){
						System.out.println(ex);
					}
			}
		});
		
		
		
		btnLoad.setBounds(274, 261, 90, 28);
		frmHistory.getContentPane().add(btnLoad);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					frmHistory.dispose();
				}
			}
		});
		btnCancel.setBounds(409, 261, 90, 28);
		frmHistory.getContentPane().add(btnCancel);
		
		
	}
}
