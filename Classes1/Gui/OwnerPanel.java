package Gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Classes.Owner;

import javax.swing.JButton;
import javax.swing.UIManager;

public class OwnerPanel {

	protected JFrame frame;
	static ArrayList<Owner> own = new ArrayList<>();

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
					OwnerPanel window = new OwnerPanel();
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
	public OwnerPanel() 
	{
		frame = new JFrame();
		
		frame.setBounds(100, 100, 554, 273);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWhatDoYou = new JLabel("What Do You Wish To Do With The Owner?");
		lblWhatDoYou.setBounds(171, 11, 242, 25);
		frame.getContentPane().add(lblWhatDoYou);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(38, 65, 122, 120);
		frame.getContentPane().add(btnCreateAccount);
		btnCreateAccount.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	CreateOwner o = new CreateOwner();
					o.frame.setVisible(true);
		        }
			}
		});
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.setBounds(205, 65, 122, 120);
		frame.getContentPane().add(btnEditAccount);
		btnEditAccount.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	EditOwner o = new EditOwner();
					o.frame.setVisible(true);
		        }
			}
		});
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setBounds(374, 67, 122, 116);
		frame.getContentPane().add(btnDeleteAccount);
		btnDeleteAccount.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int cmd = e.getButton();

		        if(cmd == 1)
		        {
		        	DeleteOwner o = new DeleteOwner();
					o.frame.setVisible(true);
		        }
			}
		});
	}
}
