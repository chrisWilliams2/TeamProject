package Gui;

import java.awt.EventQueue;
import Database.ConnectToDatabase;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.UIManager;

public class CreateOwner 
{
	protected JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;


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
					CreateOwner window = new CreateOwner();
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
	Connection con = null;
	public CreateOwner() 
	{
		initialize();
		con = ConnectToDatabase.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 451, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(29, 26, 86, 14);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(29, 52, 74, 14);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(29, 93, 73, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(6, 190, 92, 14);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblCreateAnOwner = new JLabel("Create an Owner Account");
		lblCreateAnOwner.setBounds(121, 6, 165, 14);
		frame.getContentPane().add(lblCreateAnOwner);
		
		textField = new JTextField();
		textField.setBounds(183, 23, 198, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 53, 198, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(183, 88, 198, 23);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(183, 123, 198, 23);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(183, 152, 198, 23);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(183, 187, 198, 23);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSaveDetails = new JButton("Save Details");
		btnSaveDetails.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();
		        if(cmd == 1)
		        {
					String FName = textField.getText();
					String SName = textField_1.getText();
					String Address = textField_2.getText() + textField_3.getText() + textField_4.getText();
					String PhoneNo = textField_5.getText().toString();
					 
				
					if(FName.length() < 1 || SName.length() < 1 || Address.length() < 1 || PhoneNo.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "Please enter information into every field", "Enter all information", 0);
						
					}
					else
					{
						
						//dbConnector connect = new dbConnector();
						ConnectToDatabase.writeDataOwner(FName,SName, Address, PhoneNo);
						
						
					
						JOptionPane.showMessageDialog(null, "User created successfully", "Success.", 0);
						frame.dispose();
					}
		        }
				}
			});
		btnSaveDetails.setBounds(139, 232, 108, 23);
		frame.getContentPane().add(btnSaveDetails);
	}
}
