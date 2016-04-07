package Classes;

public class Ani 
{
	private String name;
	private String type;
	private String breed;
	private String gender;
	private String treatment;
	private String prescription;
	private static int AniNo = 1;
	private int AniID;
	private String overnight;
	
	public Ani()
	{
		AniID = AniNo;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String in)
	{
		name = in;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String t)
	{
		name = t;
	}
	
	public String getBreed()
	{
		return breed;
	}
	
	public void setBreed(String b)
	{
		breed = b;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public void setGender(String in)
	{
		gender = in;
	}
	
	public String getTreatment()
	{
		return treatment;
	}
	
	public void setTreatment(String i)
	{
		treatment = i;
	}
	
	public String getPrescription()
	{
		return prescription;
	}
	
	public void setPrescription(String p)
	{
		prescription = p;
	}
	
	public int getAnimalID()
	{
		return AniID;
	}
	
	public void setOvernight(String in)
	{
		overnight = in;
	}
	
	public String toString()
	{
		return "Name: " +name + " Animal ID: " + AniID;
	}
	
	public String view()
	{
		return "Name: " +name + " Animal ID: " + AniID + "\nGender: " + gender +" Type: " + type +" Breed: " + breed;
	}
}
