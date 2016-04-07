package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

public class Appointment {

	protected JFrame frame;
	protected JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointment window = new Appointment();
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
	public Appointment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(152, 251, 152));
		frame.setBounds(100, 100, 718, 391);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 682, 286);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"9.00", null, null, null, null, null, null, null},
				{"9.30", null, null, null, null, null, null, null},
				{"10.00", null, null, null, null, null, null, null},
				{"10.30", null, null, null, null, null, null, null},
				{"11.00", null, null, null, null, null, null, null},
				{"11.30", null, null, null, null, null, null, null},
				{"12.00", null, null, null, null, null, null, null},
				{"12.30", null, null, null, null, null, null, null},
				{"13.00", null, null, null, null, null, null, null},
				{"13.30", null, null, null, null, null, null, null},
				{"14.00", null, null, null, null, null, null, null},
				{"14.30", null, null, null, null, null, null, null},
				{"15.00", null, null, null, null, null, null, null},
				{"15.30", null, null, null, null, null, null, null},
				{"16.00", null, null, null, null, null, null, null},
				{"16.30", null, null, null, null, null, null, null},
			},
			new String[] {
				"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblSundayIsFor = new JLabel("Sunday is for emergency calls only");
		lblSundayIsFor.setBounds(237, 11, 183, 14);
		frame.getContentPane().add(lblSundayIsFor);
	}
}
