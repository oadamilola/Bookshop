package bookshop;


public class User {// creates parent class User
	private int ID;
	private String username;
	private String surname;
	private int housenum;
	private String address;
	private String city;
	private String role;
	public User(int ID,String username,String surname,int housenum,String address,String city,String role)//class constructor
	{
		this.ID = ID;
		this.username = username;
		this.surname = surname;
		this.housenum = housenum;
		this.address = address;
		this.city = city;
		this.role = role;
	}
	 //get methods to get values of attributes
	public int getID() {
		return ID;
	}

	public String getUsername() {
		return username;
	}

	public String getSurname() {
		return surname;
	}

	public int getHousenum() {
		return housenum;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getRole() {
		return role;
	}
		
	//function to create User object
	public User createUser(String id) {
		Function r= new Function();
		User nuser=r.GetCurrentUser(id);
		return nuser;
	}
	
}
