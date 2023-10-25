package bookshop;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Book {
	private String type;
	private int barcode;
	private String title;
	private String language;
	private String genre;
	private LocalDate release_date;
	private int quantity;
	private float price;
	public Book(String type,int barcode,String title,String language,String genre,LocalDate release_date,int quantity,float price)
	{
		this.type= type;
		this.barcode = barcode;
		this.title = title;
		if(genre == null) {
			this.genre = genre;
		}
		if(language == null) {
			this.language= language; 
		}
		if(!(language.equals("English") | language.equals("French") ) ) { //used for debugging
			throw new IllegalArgumentException("wrong language");
		}else {
			this.language = language;			
		}
		if(!(genre.equals("Biography") | genre.equals("Computer Science") | genre.equals("Politics") | genre.equals("Business") ) ) {
			throw new IllegalArgumentException("wrong genre");
		}else {
			this.genre = genre;			
		}
		this.genre = genre;
		this.release_date = release_date;
		this.quantity = quantity;
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public int getBarcode() {
		return barcode;
	}
	public String getTitle() {
		return title;
	}
	public String getLanguage() {
		return language;
	}
	public String getGenre() {
		return genre;
	}

	public LocalDate getRelease_date() {
		return release_date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public String DtoString(LocalDate release_date) {//method to format date to string in correct date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String reldate2=formatter.format(release_date);
		return reldate2;
	}
	
	public Boolean checkdate(String date) {//checks date inputted is correct format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			LocalDate.parse(date,formatter);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	public Boolean checkletters(String input) {//checks input is only letters
		return input.matches("[a-zA-Z]+");
	}
	public Boolean checkbarcode(String input) {//checks barcode is 8 numbers
		Function r= new Function();
		if(r.isNumber(input)&&(input.length()== 8)){
			return true;
		}else {
			return false;
		}
	}
	public void UpdateStock(ArrayList<Book> Items,ArrayList<Integer>Amount) {
		Function r=new Function();
		ArrayList<Book> ListofBooks= r.ReadBooks();
		Integer count=0;
		for(Book book2:Items) {//loops through books in basket
			for(Book book:ListofBooks) { //loops through books from file
				if(book.getBarcode()==book2.getBarcode()) {//checks if barcodes are equal
					int temp=book.getQuantity();//gets book quantity in stock
					temp=temp-(Amount.get(count));//gets quantity of book in basket being purchased and takes it away from stock quantity
					book.setQuantity(temp);//sets new quantity
				}
			}
			count++;
		}
		r.Addbook(ListofBooks);//updates Stock file
	}
	
	public Boolean CheckQinStock(Book item,int num) {//checks if there is enough stock before item is added to basket num is quantity of the item currently in basket 
		Function r=new Function();
		ArrayList<Book> ListofBooks= r.ReadBooks();
		for(Book book:ListofBooks) {
			if(book.getBarcode()==item.getBarcode()) {
				if(book.getQuantity()==0) {
					return false;
				}else if(book.getQuantity()<=num){
					return false;
				}else {
					return true;
				}
			}
		}
		return false;
	}
	
	

}

