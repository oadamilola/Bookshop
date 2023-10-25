package bookshop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Function {
	public Boolean isNumber(String value) {//checks if string contains only numbers
		Boolean result= true;
		for(int i=0;i<value.length();i++) {//loops through string
			if (Character.isDigit(value.charAt(i))) {//checks each character
				
			}else {
				result=false;
			}
		}
		return result;
	}
	public ArrayList<Book> ReadBooks()  {//reads Stock file
		ArrayList<Book> ListofBooks= new ArrayList<Book>(); 
		Book book;
		File file=new File("Stock.txt");//opens file
		Scanner sc=null;
		try {
			sc = new Scanner(file);//reads file
			while(sc.hasNextLine()) {
				//);
				String[] filelines=sc.nextLine().split(", ",0);//splits into substrings and stores in array
				String type=filelines[1];
				int barcode=Integer.parseInt(filelines[0]);
				String title= filelines[2];
				String language= filelines[3];
				String genre= filelines[4];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //date formatter
				LocalDate reldate= LocalDate.parse(filelines[5],formatter); //parses date using formatter
				int quantity=Integer.parseInt(filelines[6]);
				float price= Float.parseFloat(filelines[7]);
				if(type.equals("paperback")) {
					int pages=Integer.parseInt(filelines[8]);
					String condition=filelines[9];
					book=new Paperback(type,barcode,title,language,genre,reldate,quantity,price,pages,condition); //creates paperback object
					ListofBooks.add(book);
				}else if(type.equals("ebook")) {
					int pages=Integer.parseInt(filelines[8]);
					String format=filelines[9];
					book=new EBook(type,barcode,title,language,genre,reldate,quantity,price,pages,format);//creates ebook object
					ListofBooks.add(book);
				}else if(type.equals("audiobook")) {
					float listenlen=Float.parseFloat(filelines[8]);
					String format=filelines[9];
					book=new Audiobook(type,barcode,title,language,genre,reldate,quantity,price,listenlen,format);//creates audiobook object
					ListofBooks.add(book);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc.close();
		return ListofBooks;
	}
	public ArrayList<Book> SearchBooks(String searchentry,int pos){//searches Stock file according to position passed
		ArrayList<Book> ListofBooks= new ArrayList<Book>();
		Book book;
		File file=new File("Stock.txt");
		Scanner sc=null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String[] filelines=sc.nextLine().split(", ",0);
				if(filelines[pos].toLowerCase().contains(searchentry.toLowerCase())) {//checks if book attribute contains search input to allow for broader search results
					String type=filelines[1];
					int barcode=Integer.parseInt(filelines[0]);
					String title= filelines[2];
					String genre= filelines[4];
					String language= filelines[3];
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate reldate= LocalDate.parse(filelines[5],formatter);
					int quantity=Integer.parseInt(filelines[6]);
					float price= Float.parseFloat(filelines[7]);
					if(type.equals("paperback")) {
						int pages=Integer.parseInt(filelines[8]);
						String condition=filelines[9];
						book=new Paperback(type,barcode,title,language,genre,reldate,quantity,price,pages,condition);
						ListofBooks.add(book);
					}else if(type.equals("ebook")) {
						int pages=Integer.parseInt(filelines[8]);
						String format=filelines[9];
						book=new EBook(type,barcode,title,language,genre,reldate,quantity,price,pages,format);
						ListofBooks.add(book);
					}else if(type.equals("audiobook")) {
						float listenlen=Float.parseFloat(filelines[8]);
						String format=filelines[9];
						book=new Audiobook(type,barcode,title,language,genre,reldate,quantity,price,listenlen,format);
						ListofBooks.add(book);
					}
				}else {
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc.close();
		return ListofBooks;//returns list of results
	}
	public Book FindBook(Object bookbarcode){//searches for specific book in Stockfile
		Book BookFound;
		Book NotBook= new Book(null, 0, null, "French", "Business", null, 0, 0); //creates null Book object to return if not found
		File file=new File("Stock.txt");
		Scanner sc=null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String[] filelines=sc.nextLine().split(", ",0);
				int barcode=Integer.parseInt(filelines[0]);
				if(barcode==((Integer) bookbarcode)) {
					String type=filelines[1];
					String title= filelines[2];
					String language= filelines[3];
					String genre= filelines[4];
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate reldate= LocalDate.parse(filelines[5],formatter);
					int quantity=Integer.parseInt(filelines[6]);
					float price= Float.parseFloat(filelines[7]);
					if(type.equals("paperback")) {
						int pages=Integer.parseInt(filelines[8]);
						String condition=filelines[9];
						BookFound=new Paperback(type,barcode,title,language,genre,reldate,quantity,price,pages,condition);
						return BookFound;//returns book object if found
					}else if(type.equals("ebook")) {
						int pages=Integer.parseInt(filelines[8]);
						String format=filelines[9];
						BookFound=new EBook(type,barcode,title,language,genre,reldate,quantity,price,pages,format);
						return BookFound;
					}else if(type.equals("audiobook")) {
						float listenlen=Float.parseFloat(filelines[8]);
						String format=filelines[9];
						BookFound=new Audiobook(type,barcode,title,language,genre,reldate,quantity,price,listenlen,format);
						return BookFound;
					}
					sc.close();
				}else {
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
		return NotBook;//if book is not found
	}
	
	public ArrayList<Book> MinLengthSearch(String searchentry){//searches Stock file based on minimum listening length
		ArrayList<Book> ListofBooks= new ArrayList<Book>();
		Book book;
		File file=new File("Stock.txt");
		Scanner sc=null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String[] filelines=sc.nextLine().split(", ",0);
				String type=filelines[1];
				if(type.equals("audiobook")) {
					float listenlen=Float.parseFloat(filelines[8]);
					float compare=Float.parseFloat(searchentry);
					if(listenlen>=compare) {
						int barcode=Integer.parseInt(filelines[0]);
						String title= filelines[2];
						String genre= filelines[4];
						String language= filelines[3];
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate reldate= LocalDate.parse(filelines[5],formatter);
						int quantity=Integer.parseInt(filelines[6]);
						float price= Float.parseFloat(filelines[7]);
						String format=filelines[9];
						book=new Audiobook(type,barcode,title,language,genre,reldate,quantity,price,listenlen,format); //creates new audiobook object
						ListofBooks.add(book);
					}
				}else {
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc.close();
		return ListofBooks;
	}
	
	public ArrayList<User> ReadUsers() {//reads UserAccounts file
		ArrayList<User> Users= new ArrayList<User>();//arraylist of user objects
		User newuser;
		File file2=new File("UserAccounts.txt");
		Scanner sc2=null;
		try {
			sc2 = new Scanner(file2);
			while(sc2.hasNextLine()) {
				String[] filelines=sc2.nextLine().split(", ",0);
				int ID=Integer.parseInt(filelines[0]);
				String username=filelines[1];
				String surname= filelines[2];
				int housenum=Integer.parseInt(filelines[3]);
				String address= filelines[4];
				String city= filelines[5];
				String role=filelines[7];
				if(role.equals("admin")) {
					newuser= new Admin(ID, username, surname, housenum, address, city, role);//creates new admin object
					Users.add(newuser);
				}else {
					float credit= Float.parseFloat(filelines[6]);
					newuser= new Customer(ID, username, surname, housenum, address, city, credit, role);//creates new customer object
					Users.add(newuser);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc2.close();
		return Users;
	}
	public User GetCurrentUser(String id) {//gets current users information from UserAccounts file
		File file2=new File("UserAccounts.txt");
		Scanner sc2=null;
		User notuser= new User(0, id, id, 0, id, id, id);
		try {
			sc2 = new Scanner(file2);
			while(sc2.hasNextLine()) {
				String[] filelines=sc2.nextLine().split(", ",0);
				if(filelines[0].equals(id)) {
					int userid=Integer.parseInt(filelines[0]);
					String username=filelines[1];
					String surname=filelines[2];
					int housenum=Integer.parseInt(filelines[3]);
					String postcode=filelines[4];
					String city=filelines[5];
					float credit= Float.parseFloat(filelines[6]);
					String role= filelines[7];
					if (role.equals("admin")) {
						Admin cuser= new Admin(userid, username, surname, housenum, postcode, city, role);
						return cuser;
					}else {
						Customer cuser= new Customer(userid, username, surname, housenum, postcode, city, credit, role);
						return cuser;
					}
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc2.close();
		return notuser;
		
	}
	public void Addbook(ArrayList<Book> BookList) { //updates Stock file
		BufferedWriter b= null;
		try {
			b= new BufferedWriter(new FileWriter("Stock.txt.",false));//creates bufferedwriter using new filewriter to write to Stock file
			for(Book book:BookList) {
				//concatenates attributes into correct format to write to file
				if(book.getType().equals("paperback")) {
					String text= new String(book.getBarcode()+", "+ book.getType() +", "+ book.getTitle() +", "+ book.getLanguage() +", "+ book.getGenre() +", "+
							book.DtoString(book.getRelease_date())+", "+ book.getQuantity()+", "+ book.getPrice()+", "+ ((Paperback) book).getPages()+", "+ ((Paperback) book).getCondition());
				b.write(text.toString()+"\n");
				}else if(book.getType().equals("ebook")) {
					String text= new String(book.getBarcode()+", "+ book.getType() +", "+ book.getTitle() +", "+ book.getLanguage() +", "+ book.getGenre() +", "+
							book.DtoString(book.getRelease_date())+", "+ book.getQuantity()+", "+ book.getPrice()+", "+ ((EBook) book).getPages()+", "+ ((EBook) book).getFormat());
					b.write(text.toString()+"\n");
				}else if(book.getType().equals("audiobook")) {
					String text= new String(book.getBarcode()+", "+ book.getType() +", "+ book.getTitle() +", "+ book.getLanguage() +", "+ book.getGenre() +", "+
							book.DtoString(book.getRelease_date())+", "+ book.getQuantity()+", "+ book.getPrice()+", "+ ((Audiobook) book).getLength()+", "+ ((Audiobook) book).getFormat());
					b.write(text.toString()+"\n");
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				if(b!=null) {
					b.close();//closes bufferedwriter
				}
			}catch(IOException e) {
				e.printStackTrace();

			}
		}
	}
	public void UpdateUser(ArrayList<User> Users) { //updates UserAccounts file
		BufferedWriter b= null;
		try {
			b= new BufferedWriter(new FileWriter("UserAccounts.txt.",false));
			for(User user:Users) {
				//concatenates user attributes into string to write to file
				if(user.getRole().equals("admin")) {
					String text= new String(user.getID()+", "+ user.getUsername() +", "+ user.getSurname() +", "+ user.getHousenum() +", "+ user.getAddress() +", "+
							user.getCity()+", "+", "+ user.getRole());
				b.write(text.toString()+"\n");
				}else if(user.getRole().equals("customer")) {
					String text= new String(user.getID()+", "+ user.getUsername() +", "+ user.getSurname() +", "+ user.getHousenum() +", "+ user.getAddress() +", "+
							user.getCity()+", "+ ((Customer)user).getCredit()+", "+ user.getRole());
					b.write(text.toString()+"\n");
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				if(b!=null) {
					b.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList<Book> AddtoList(String type,Integer barcode,String title,String language,String genre,String reldate,
			Integer quantity,float price,Integer value,String value2,float value3){ //adds new book to arraylist book
		ArrayList<Book> ListofBooks= new ArrayList<Book>();
		Book book;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(reldate,formatter);//formats date to use to construct new book object
		ListofBooks =ReadBooks();
		//creates new book object
		if(type.equals("paperback")) {
			book=new Paperback(type,barcode,title,language,genre,date,quantity,price,value,value2);
			ListofBooks.add(book);
		}else if(type.equals("ebook")) {
			book=new EBook(type,barcode,title,language,genre,date,quantity,price,value,value2);
			ListofBooks.add(book);
		}else if(type.equals("audiobook")) {
			book=new Audiobook(type,barcode,title,language,genre,date,quantity,price,value3,value2);
			ListofBooks.add(book);
		}
		return ListofBooks;
	}
	
	public Boolean Exists(ArrayList<Book> Books,String barc) {//checks if barcode exists in arraylist
		int barcint=Integer.parseInt(barc);
		for(Book book:Books) {
			if(book.getBarcode()==barcint) {
				return true;
			}
		}
		return false;
	}
	

}
