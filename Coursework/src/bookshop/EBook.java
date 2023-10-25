package bookshop;

import java.time.LocalDate;
import java.util.Date;

//ceates EBook subclass which inherits from Book
public class EBook extends Book {
	private int pages;
	private String format;
	//class constructor
	public EBook(String type,int barcode, String title,String language,  String genre, LocalDate release_date, int quantity,
			float price,int pages,String format) {
		super(type,barcode, title,language, genre,  release_date, quantity, price);//attributes inherited from parent class Book
		this.pages=pages;
		this.format=format;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public Boolean checkEformat(String input) { //checks format is valid
		input.toUpperCase();
		if(input.equals("EPUB")||input.equals("MOBI")||input.equals("PDF")) {
			return true;
		}else {
			return false;
		}
	}

}
