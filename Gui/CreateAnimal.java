package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Classes.Ani;
import Database.ConnectToDatabase;

public class CreateAnimal {

	protected JFrame frame;
	protected JTextField textField;
	private JTextField textField_1;
	static ArrayList<Ani> a = new ArrayList<>();
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame.getContentPane().setBackground(new Color(240, 128, 128));
		frame.setBackground(new Color(240, 128, 128));
		frame.setBounds(100, 100, 480, 222);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(21, 31, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(77, 28, 109, 20);
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
		textField_1.setBounds(272, 65, 125, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(222, 31, 46, 14);
		frame.getContentPane().add(lblGender);
		
		textField_3 = new JTextField();
		textField_3.setBounds(77, 60, 109, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(275, 28, 122, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnSaveDetails = new JButton("Save Details");
		btnSaveDetails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();
		        if(cmd == 1)
		        {
					String Name = textField.getText();
					String Type = textField_1.getText();
					String Gender = textField_2.getText();
					String Breed = textField_3.getText();
					 
				
					if(Name.length() < 1 || Type.length() < 1 || Gender.length() < 1 || Breed.length() < 1)
					{
						JOptionPane.showMessageDialog(null, "Please enter information into every field", "Enter all information", 0);
						
					}
					else
					{
						
						//dbConnector connect = new dbConnector();
						ConnectToDatabase.writeDataAnimal(Name, Gender, Type, Breed);
						
						
					
						JOptionPane.showMessageDialog(null, "User created successfully", "Success.", 0);
						frame.dispose();
					}
		        }
				}
			});
			
		btnSaveDetails.setBounds(160, 150, 119, 23);
		frame.getContentPane().add(btnSaveDetails);
		
		
		
	}
}
