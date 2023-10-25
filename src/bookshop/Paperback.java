package bookshop;

import java.time.LocalDate;
import java.util.Date;
 
//ceates Paperback subclass which inherits from Book
public class Paperback extends Book {
	private int pages;
	private String condition;
	//class constructor
	public Paperback(String type,int barcode, String title, String language,String genre,  LocalDate release_date, int quantity,
			float price,int pages,String condition) {
		super(type,barcode, title,language, genre, release_date, quantity, price);//attributes inherited from parent class Book
		this.pages=pages;
		this.condition=condition;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Boolean checkcondition(String input) {
		input.toLowerCase();//makes input not case sensitive
		if(input.equals("new")||input.equals("used")) { //checks condition
			return true;
		}else {
			return false;
		}
	}

}
