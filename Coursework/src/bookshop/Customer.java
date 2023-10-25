package bookshop;

import java.util.ArrayList;

//ceates Customer subclass which inherits from User
public class Customer extends User {
	private float credit;
	
	//Customer class constructor
	public Customer(int ID, String username, String surname,int housenum,String address,String city,float credit,String role) {
		super(ID, username, surname,housenum, address,city,role); //attributes inherited from super class User
		this.credit=credit;
	}
	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}
	public void updateCredit(float total,int ID) {
		Function r=new Function();
		float temp;
		ArrayList<User> Users= r.ReadUsers();
		for(User user:Users) {
			if(user.getID()==ID) {
				temp=((Customer) user).getCredit();
				temp=temp-total;
				((Customer) user).setCredit(temp);
			}
			
		}
		r.UpdateUser(Users);
	}

}
