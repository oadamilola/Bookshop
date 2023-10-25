package bookshop;

import java.util.ArrayList;

public class Basket {
	
	Function r = new Function();
	Book b = new Book(null, 0, null, "French", "Business", null, 0, 0) ;
	
	public Book AddItem(Object itembarcode) {//function to return book found using barcode
		Book item;
		item=r.FindBook(itembarcode);//returns book found
		return item;
		
	}
	public Boolean AlreadyIn(Object itembarcode,ArrayList<Object> Items) {//checks if barcode is already in arraylist
		if(Items.contains(itembarcode)) {
			return true;
		}else {
			return false;
		}
	}
	public Boolean BookIn(Book book,ArrayList<Book> Items) {//checks if book object is already in arraylist of books
		if(Items.contains(book)) {
			return true;
		}else {
			return false;
		}
	}
	public Boolean Checkout(Customer userp,float total) {//checks out basket if there is enough credit
		float credit=userp.getCredit();
		if(credit>=total) {
			credit=credit-total;
			userp.setCredit(credit);//updates user credit
			return true;
		}else {
			return false;
		}
		
	}
	public float calculateTotal(ArrayList<Book> Items,ArrayList<Integer> Amount) { //calculates total of basket
		float total=0;
		Integer count=0;
		for(Book book:Items) {
			total=total+(book.getPrice()*Amount.get(count));//multiplies individual book price by quantity in basket and adds to total
			count++;
		}
		return total;

	}

}
