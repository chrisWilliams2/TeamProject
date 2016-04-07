package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Receptionist 
{
	protected JFrame frame;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist window = new Receptionist();
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
	public Receptionist() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setAutoRequestFocus(false);
		frame.setBounds(400, 100, 676, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOwner = new JButton("Owner");
		btnOwner.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	OwnerPanel op = new OwnerPanel();
					op.frame.setVisible(true);
		        }
			}
		});
		btnOwner.setForeground(new Color(0, 0, 0));
		btnOwner.setBackground(new Color(245, 222, 179));
		btnOwner.setBounds(10, 66, 165, 227);
		frame.getContentPane().add(btnOwner);
		btnOwner.setActionCommand("Owner");
		
		JButton btnAnimal = new JButton("Animal");
		btnAnimal.setBackground(new Color(245, 222, 179));
		btnAnimal.setBounds(214, 66, 226, 227);
		frame.getContentPane().add(btnAnimal);
		btnAnimal.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	Animal a = new Animal();
					a.frame.setVisible(true);
		        }
			}
		});
		
		JButton btnAppointment = new JButton("Appointment");
		btnAppointment.setBackground(new Color(245, 222, 179));
		btnAppointment.setBounds(479, 66, 171, 227);
		frame.getContentPane().add(btnAppointment);
		btnAppointment.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	Appointment a = new Appointment();
					a.frame.setVisible(true);
		        }
			}
		});
		
		JLabel lblPleaseSelectOne = new JLabel("Please Select One Of The Below");
		lblPleaseSelectOne.setForeground(new Color(0, 0, 0));
		lblPleaseSelectOne.setBounds(254, 0, 181, 44);
		frame.getContentPane().add(lblPleaseSelectOne);
		
		JLabel lblPleaseEnsureYou = new JLabel("Please ensure all details are entered when a new customer requests an appointment");
		lblPleaseEnsureYou.setForeground(new Color(0, 0, 0));
		lblPleaseEnsureYou.setBounds(121, 316, 488, 22);
		frame.getContentPane().add(lblPleaseEnsureYou);
	}
	
}
