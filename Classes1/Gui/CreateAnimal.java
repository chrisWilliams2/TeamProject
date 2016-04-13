package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Database.ConnectToDatabase;

import javax.swing.UIManager;

public class CreateAnimal {

	protected JFrame frame;
	protected JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textFieldCustID;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(240, 128, 128));
		frame.setBounds(100, 100, 480, 222);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 31, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(77, 28, 109, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(21, 68, 46, 14);
		frame.getContentPane().add(lblType);
		
		JLabel lblCreateAnimalAccount = new JLabel("Create Animal Account");
		lblCreateAnimalAccount.setBounds(175, 3, 125, 14);
		frame.getContentPane().add(lblCreateAnimalAccount);
		
		JLabel lblBreed = new JLabel("Breed:");
		lblBreed.setBounds(222, 68, 46, 14);
		frame.getContentPane().add(lblBreed);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 60, 125, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(222, 31, 46, 14);
		frame.getContentPane().add(lblGender);
		
		textField_3 = new JTextField();
		textField_3.setBounds(77, 60, 109, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(275, 28, 122, 28);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSaveDetails = new JButton("Save Details");
		btnSaveDetails.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)  
			{
				
		        {
					String Name = textField.getText();
					String Type = textField_1.getText();
					String Gender = textField_4.getText();
					String Breed = textField_3.getText();
					//String OwnerID = textFieldCustID.getText();
					 
				
					if(Name.length() < 1 || Type.length() < 1 || Gender.length() < 1 || Breed.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "Please enter information into every field", "Enter all information", 0);
						
					}
					else
					{
						
						//dbConnector connect = new dbConnector();
						ConnectToDatabase.writeDataAnimal(Breed, Gender, Name, Type);
						
						
					
						JOptionPane.showMessageDialog(null, "User created successfully", "Success.", 0);
						frame.dispose();
					}
		        }
				}
			});
			
		btnSaveDetails.setBounds(160, 150, 119, 23);
		frame.getContentPane().add(btnSaveDetails);
		
		/*JLabel lblCustID = new JLabel("Owner ID:");
		lblCustID.setBounds(21, 110, 60, 14);
		frame.getContentPane().add(lblCustID);
		
		textFieldCustID = new JTextField();
		textFieldCustID.setColumns(10);
		textFieldCustID.setBounds(77, 96, 109, 28);
		frame.getContentPane().add(textFieldCustID);
		*/
		
		
		
	}
}
