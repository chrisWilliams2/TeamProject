package Gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class Animal {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Animal window = new Animal();
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
	public Animal() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(176, 224, 230));
		frame.setBounds(100, 100, 366, 264);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseChooseWhat = new JLabel("Please Choose What To Do With The Animal");
		lblPleaseChooseWhat.setBounds(50, 11, 253, 32);
		frame.getContentPane().add(lblPleaseChooseWhat);
		
		JButton btnNewButton = new JButton("Create Account");
		btnNewButton.setBackground(new Color(176, 224, 230));
		btnNewButton.setBounds(22, 54, 112, 121);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	CreateAnimal a = new CreateAnimal();
					a.frame.setVisible(true);
		        }
			}
		});
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.setBackground(new Color(176, 224, 230));
		btnEditAccount.setBounds(216, 54, 112, 121);
		frame.getContentPane().add(btnEditAccount);
		btnEditAccount.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	EditAnimal a = new EditAnimal();
					a.frame.setVisible(true);
		        }
			}
		});
		
		JLabel lblEnsureAllDetails = new JLabel("Ensure all details are correct before leaving this page");
		lblEnsureAllDetails.setBounds(32, 186, 299, 30);
		frame.getContentPane().add(lblEnsureAllDetails);
	}

}
