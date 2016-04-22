package Database;
import java.sql.Connection;
import java.sql.DriverManager;



import java.sql.ResultSet;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class ConnectToDatabase {
	
	public static Connection con;
	
	
	public static Connection dbConnector(){
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/dbuseraccount?autoReconnect=true&useSSL=false","root","root");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return con;
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			return null;
		}
	}
	
	
	
		public  static void writeData(String role, String firstName, String surName, String username, String password){
			try{
				
				PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO users( role ,firstName, surName,username, password)VALUES ('"+role+"','"+firstName+"','"+surName+"','"+username+"','"+password+"')");

				ps.executeUpdate();
				ps.close();
				con.close();
				
				System.out.println("works");
				
				
			}catch(Exception ex){
				System.out.println(ex);
			}
		}
	
			public  static void checkpass(String username, char[] password){
				
				    try {
				    	String queryString = "SELECT * FROM users where username=? and password=?";
				    	PreparedStatement ps = (PreparedStatement) con.prepareStatement(queryString);
				        ResultSet results = ps.executeQuery(queryString); 

				        if(!results.next()) {

				              JOptionPane.showMessageDialog(null, "Wrong Username and Password.");  
				              
				        }
				    
 					/*
					PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO users( role ,firstName, surName,username, password)VALUES ('"+role+"','"+firstName+"','"+surName+"','"+username+"','"+password+"')");

					ps.executeUpdate();
					ps.close();
					con.close();
					
					System.out.println("works");
				*/
					
				}catch(Exception ex){
					System.out.println(ex);
				}
	}
			public  static void writeDataOwner(String FName, String LName, String Address, String PhoneNo)
			{
				try{
					
					PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO dbuseraccount.Owner(FName, LName, Address, PhoneNo)VALUES ( '" + FName + "','" + LName + "','" + Address + "','" + PhoneNo + "')");

					ps.executeUpdate();
					ps.close();
					con.close();
					
					System.out.println("works");
					
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			public  static void writeDataAnimal(String Breed, String Gender, String Name, String Type)
			{
				try{
					
					PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO  dbuseraccount.animal(Breed,Gender, Name, Type)VALUES ( '" + Breed + "','" + Gender + "','" + Name + "','" + Type + "')");

					ps.executeUpdate();
					ps.close();
					con.close();
					
					System.out.println("works");
					
				}
				
				catch(Exception e)
				{
					System.out.println(e+"here!");
				}
			}
			
		
				public  static void writeDataAppointment( String Date, String CustID, String AniID, String PetType, String Injury)
				{
					try{
						
						PreparedStatement ps = (PreparedStatement) con.prepareStatement("Update dbuseraccount.scheduler(Date, CustID, AniId, PetType, Injury)VALUES ('" + Date + "','" + CustID + "','" + AniID + "','" + PetType + "','" + Injury + "')");

						ps.execute();
						ps.close();
						con.close();
						
						System.out.println("works");
						
					}
					
					catch(Exception e)
					{
						System.out.println(e);
					}
			}
				
				public static void writeTreatment(String aID, String treat, String date)
				{
					try
					{
						PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO dbuseraccount.treatment(AnimalID, Treatment, Date)VALUES('" + aID + "','" + treat + "','" + date+"')");
						
						ps.executeUpdate();
						ps.close();
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
		

}
		
	
