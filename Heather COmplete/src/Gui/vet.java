package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;

public class vet {

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
					vet window = new vet();
					window.frmVet.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVet = new JFrame();
		frmVet.setTitle("Vet");
		frmVet.setBounds(100, 100, 462, 311);
		frmVet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVet.getContentPane().setLayout(null);
		
		JButton btnAppointments = new JButton("View Appointments");
		btnAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				
				if(cmd==1)
				{
					AppointmentGui window = new AppointmentGui();
					window.frmAppointment.setVisible(true);
				}
			}
		});
		btnAppointments.setBounds(123, 32, 161, 82);
		frmVet.getContentPane().add(btnAppointments);
		
		JButton button = new JButton("View Animals");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				
				if(cmd==1)
				{
					animals app = new animals();
					app.frmVet.setVisible(true);
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(31, 136, 161, 82);
		frmVet.getContentPane().add(button);
		
		JButton btnEditAnimals = new JButton("Edit Animals");
		btnEditAnimals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					editAnimalVet app = new editAnimalVet();
					app.frmEditAnimal.setVisible(true);
				}
			}
		});
		btnEditAnimals.setBounds(230, 138, 161, 78);
		frmVet.getContentPane().add(btnEditAnimals);
	}
}
