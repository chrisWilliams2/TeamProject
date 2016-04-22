package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;


public class Treatment{

	protected JFrame frmTreatment;
	private JTextField aIDtxt;
	private JTextField aNameTxt;
	private JTextField ownerTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Treatment window = new Treatment();
					window.frmTreatment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CurrentDate(){
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		lblDate.setText(day+"/"+(month+1)+"/"+year);
	}
	
	Connection con = null;
	private JTextField txtBreed;
	private JTextField txtType;
	private JTextField txtGender;
	private JLabel lblDate;

	/**
	 * Create the application.
	 */
	public Treatment() {
		initialize();
		CurrentDate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTreatment = new JFrame();
		frmTreatment.setTitle("Treatment");
		frmTreatment.setBounds(100, 100, 620, 386);
		frmTreatment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTreatment.getContentPane().setLayout(null);
		
		Label label = new Label("Treatment : ");
		label.setBounds(29, 155, 69, 22);
		frmTreatment.getContentPane().add(label);
		
		Label label_1 = new Label("Animal ID: ");
		label_1.setBounds(206, 17, 62, 22);
		frmTreatment.getContentPane().add(label_1);
		
		Label label_2 = new Label("Name:");
		label_2.setBounds(408, 17, 51, 22);
		frmTreatment.getContentPane().add(label_2);
		
		String id = EditAnimalVet.getID();
		String aname = EditAnimalVet.getAname();
		String aType = EditAnimalVet.getType();
		String aBreed = EditAnimalVet.getType();
		String aGender = EditAnimalVet.getType();

		Label label_3 = new Label("Owner ID:");
		label_3.setBounds(10, 45, 62, 23);
		frmTreatment.getContentPane().add(label_3);
		
		JLabel lblBreed = new JLabel("Breed:");
		lblBreed.setBounds(212, 45, 55, 16);
		frmTreatment.getContentPane().add(lblBreed);
		
//		JLabel lblDate = new JLabel("Date:");
//		lblDate.setBounds(23, 17, 43, 16);
//		frmTreatment.getContentPane().add(lblDate);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(414, 46, 43, 16);
		frmTreatment.getContentPane().add(lblType);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(408, 74, 55, 16);
		frmTreatment.getContentPane().add(lblGender);
		
		JTextArea TreatmentInput = new JTextArea();
		TreatmentInput.setBounds(104, 155, 401, 138);
		frmTreatment.getContentPane().add(TreatmentInput);
		
		aIDtxt = new JTextField();
		aIDtxt.setEditable(false);
		aIDtxt.setBounds(280, 11, 122, 28);
		frmTreatment.getContentPane().add(aIDtxt);
		aIDtxt.setColumns(10);
		aIDtxt.setText(id);
		
		aNameTxt = new JTextField();
		aNameTxt.setEditable(false);
		aNameTxt.setColumns(10);
		aNameTxt.setBounds(465, 11, 122, 28);
		frmTreatment.getContentPane().add(aNameTxt);
		aNameTxt.setText(aname);
		
		ownerTxtField = new JTextField();
		ownerTxtField.setEditable(false);
		ownerTxtField.setColumns(10);
		ownerTxtField.setBounds(78, 40, 122, 28);
		frmTreatment.getContentPane().add(ownerTxtField);
		
		txtBreed = new JTextField();
		txtBreed.setEditable(false);
		txtBreed.setBounds(280, 40, 122, 28);
		frmTreatment.getContentPane().add(txtBreed);
		txtBreed.setColumns(10);
		txtBreed.setText(aBreed);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		txtType.setBounds(465, 40, 122, 28);
		frmTreatment.getContentPane().add(txtType);
		txtType.setColumns(10);
		txtType.setText(aType);
		
		txtGender = new JTextField();
		txtGender.setEditable(false);
		txtGender.setBounds(465, 68, 122, 28);
		frmTreatment.getContentPane().add(txtGender);
		txtGender.setColumns(10);
		txtGender.setText(aGender);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(81, 8, 62, 34);
		frmTreatment.getContentPane().add(lblDate);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(199, 309, 90, 28);
		frmTreatment.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(null, "Confirm update", "Update",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				try{
					String txt = TreatmentInput.getText();
					String aID = aIDtxt.getText();
					String date = lblDate.getText();
					
					if(txt.length()< 1)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Treatment", "Enter Treatment", 0);
					}
					else
					{
						Database.ConnectToDatabase.writeTreatment(aID, txt, date);
						
						JOptionPane.showMessageDialog(null, "Treatment Updated", "Success.", 0);
						frmTreatment.dispose();

					}
					
					
				}catch(Exception ex ){
					ex.printStackTrace();
				}
				
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(318, 309, 90, 28);
		frmTreatment.getContentPane().add(btnCancel);
		
		JLabel lblDate_1 = new JLabel("Date:");
		lblDate_1.setBounds(29, 15, 43, 20);
		frmTreatment.getContentPane().add(lblDate_1);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cmd = e.getButton();
				if(cmd==1)
				{
					frmTreatment.dispose();
				}
			}
		});
	}
}
