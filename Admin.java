package bookshop;

//ceates Admin subclass which inherits from User
public class Admin extends User {
	// Admin class constructor
	public Admin(int ID, String username, String surname,int housenum,String address,String city,String role) {
		super(ID, username, surname,housenum, address,city,role); //attributes inherited from super class User
		// TODO Auto-generated constructor stub
	}

}
