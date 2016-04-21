package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Receptionist 
{
	protected JFrame frame;
	
	
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
		
		btnOwner.setBounds(10, 66, 165, 227);
		frame.getContentPane().add(btnOwner);
		btnOwner.setActionCommand("Owner");
		
		JButton btnAnimal = new JButton("Animal");
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
		btnAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		        	AppointmentGui window = new AppointmentGui();
					window.frmAppointment.setVisible(true);
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
