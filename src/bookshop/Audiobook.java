package bookshop;

import java.time.LocalDate;
import java.util.Date;

//ceates Audiobook subclass which inherits from Book
public class Audiobook extends Book {
	private float length;
	private String format;
	//class constructor
	public Audiobook(String type,int barcode, String title, String language,String genre,  LocalDate release_date, int quantity,
			float price,float length,String format) {
		super(type,barcode, title,language, genre,  release_date, quantity, price);//attributes inherited from parent class Book
		this.length=length;
		this.format=format;
	}

	public float getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public Boolean checkaudioformat(String input) {//checks format
		input.toUpperCase();
		if(input.equals("MP3")||input.equals("WMA")||input.equals("AAC")) {
			return true;
		}else {
			return false;
		}
	}

}
