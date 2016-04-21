package Classes;

public class Owner 
{
	private String FName;
	private String LName;
	private String Add1, Add2, Add3;
	private String PhoneNo;
	private int CustID;

	
	public Owner()
	{
	}
	
	public void setFName(String in)
	{
		FName = in;
	}
	
	public void setLname(String i)
	{
		LName = i;
	}
	
	public void setAddress(String home, String address, String here)
	{
		Add1 = home;
		Add2 = address;
		Add3 = here;
	}
	
	public void setPhoneNo(String number)
	{
		PhoneNo = number;
	}
	
	public String getFName()
	{
		return FName;
	}
	
	public String getSName()
	{
		return LName;
	}
	
	public String getAddress()
	{
		return Add1 + " \n" + Add2 + " \n" + Add3 + " \n";
	}
	
	public String getPhoneNo()
	{
		return PhoneNo;
	}
	
	public int getCustID()
	{
		return CustID;
	}
	
	public String  view()
	{
		return FName + " " + LName + " \n" + Add1 + " \n" + Add2 + " \n" + Add3 + " \n" + PhoneNo;
	}
	
	public String toString()
	{
		return FName + " " + LName;
	}
}
