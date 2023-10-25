package bookshop;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

public class Customerframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	//private JTable stable;
	Function r;
	Paperback p;
	EBook e;
	Audiobook a;
	Book b;
	Basket userbasket;
	User u;
	private DefaultTableModel dtmview;
	private DefaultTableModel dtmsearch;
	private DefaultTableModel dtmbasket;
	private String id;

	/**
	 * Create the frame.
	 * @param cframe 
	 * @param id 
	 */
	public Customerframe(String receivedid) {
		setTitle("Customer User ID: "+receivedid);
		this.id=receivedid;
		User u= new User(1, receivedid, receivedid, 1, receivedid, receivedid, receivedid);//creates user object b to access methods
		User currentuser=u.createUser(id);//creates currentuser object using id from login
		
		Book b = new Book(null, 0, null, "French", "Business", null, 0, 0) ; //creates book object b to access methods
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 839, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 819, 495);
		contentPane.add(tabbedPane);
		
		JPanel panel_1= new JPanel();
		tabbedPane.addTab("Search",null, panel_1,null);
		panel_1.setLayout(null);
		
		JScrollPane scrollpane1= new JScrollPane();
		scrollpane1.setBounds(0, 59, 804, 347);
		panel_1.add(scrollpane1);

		
		JPanel panel_2= new JPanel();
		tabbedPane.addTab("Basket",null, panel_2,null);
		panel_2.setLayout(null);
		
		JScrollPane scrollpane2= new JScrollPane();
		scrollpane2.setBounds(0, 59, 804, 347);
		panel_2.add(scrollpane2);
		
		
		String[] columnnames= {"Barcode","Title","Language","Genre","Release Date","Quantity","Retail Price","Pages","Condition","Length","Format"};
		JTable stable = new JTable(){
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		stable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollpane1.setViewportView(stable);
		dtmsearch=new DefaultTableModel(){ //sorts table
			public Class<?> getColumnClass(int index){
				if(index==6) {
					return Integer.class;
				}else {
					return String.class;
				}
			}
		};
		dtmsearch.setColumnIdentifiers(columnnames);
		stable.setModel(dtmsearch);
		
		textField = new JTextField();
		textField.setBounds(10, 30, 334, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		String[] options= {"Title","Genre","Type","Barcode","Minimum length"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(685, 30, 119, 19);
		panel_1.add(comboBox);
		
		JButton btnNewButton = new JButton("search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().equals("")) {
					JFrame f=new JFrame();
					JOptionPane.showMessageDialog(f,"SEARCH FIELD EMPTY","ERROR",JOptionPane.ERROR_MESSAGE);
					//ERROR
				}else {
					dtmsearch.setRowCount(0);
					String criteria = (String) comboBox.getSelectedItem();
					r = new Function();
					ArrayList<Book> Results= new ArrayList<Book>();
					if(criteria.equals("Barcode")) { //performs search of barcodes using text from text field 
						Results= r.SearchBooks(textField.getText().trim(),0);

					}else if(criteria.equals("Title")){//performs search of titles using text from text field 
						Results= r.SearchBooks(textField.getText().trim(),2);
						
					}else if(criteria.equals("Minimum length")){//performs search of audiobooks using minimum length inputted
						Results= r.MinLengthSearch(textField.getText().trim());
						
					}else if(criteria.equals("Genre")){//performs search of genre using text from text field 
						Results= r.SearchBooks(textField.getText().trim(),4);
					}
					//loops through search results and adds to table
					for(Book book:Results) {

						if(book.getType().equals("paperback")) {
							Object[] value1= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
									book.getQuantity(),book.getPrice(),((Paperback) book).getPages(),((Paperback) book).getCondition(),"",""};
							dtmsearch.addRow(value1);
						}else if(book.getType().equals("ebook")) {
							Object[] value1= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
									book.getQuantity(),book.getPrice(),((EBook) book).getPages(),"","",((EBook) book).getFormat()};
							dtmsearch.addRow(value1);
						}else if(book.getType().equals("audiobook")) {
							Object[] value1= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
									book.getQuantity(),book.getPrice(),"","",((Audiobook) book).getLength(),((Audiobook) book).getFormat()};
							dtmsearch.addRow(value1);
						}
						setVisible(true);
					}
				}
				TableRowSorter<DefaultTableModel>sorter2= new TableRowSorter<DefaultTableModel>(dtmsearch);
				stable.setRowSorter(sorter2);
				sorter2.setSortKeys(Arrays.asList(new RowSorter.SortKey(6,SortOrder.ASCENDING)));
				
			}
		});
		btnNewButton.setBounds(344, 30, 72, 19);
		panel_1.add(btnNewButton);
		
		JLabel totalbasket = new JLabel("Total: £0.00");
		totalbasket.setBounds(713, 405, 91, 17);
		panel_2.add(totalbasket);
		
		ArrayList<Book> ItemsinBasket= new ArrayList<Book>(); //arraylist of books in basket
		ArrayList<Integer> Number= new ArrayList<Integer>();//arraylist of quantities of each book in basket
		
		userbasket=new Basket();
		JButton addtobasket = new JButton("Add to Basket");
		addtobasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = stable.getSelectedRow();
				Object barcode=stable.getValueAt(row, 0);
				ArrayList<Object> Items= new ArrayList<Object>();
				stable.clearSelection(); //deselects item
				int rowfound=0;
				for(int i=0;i<dtmbasket.getRowCount();i++) { //stores all barcodes in basket in arraylist
					Items.add(dtmbasket.getValueAt(i, 0));
				}
				
				Book item=userbasket.AddItem(barcode);
				int count=0;
				int pos=0;
				int q=0;
				for(Book book:ItemsinBasket) {//loops through items in basket
					if(book.getBarcode()==item.getBarcode()) {//when book with the same barcode is found
						pos=count;//stores index of item
						q=Number.get(pos);//gets corresponding quantity using index
					}
					count++;
				}
				if(b.CheckQinStock(item, (q))) {//checks if there is enough stock to add item to basket or increase quantity
					if((userbasket.AlreadyIn(barcode,Items))) {
						//increments quantity of item in basket 
						q++;
						Number.set(pos, q); //increments quantity in Number arraylist
						dtmbasket.setRowCount(0);
						int index=0;
						for(Book book:ItemsinBasket) {//updates basket
							Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),Number.get(index),(book.getPrice()*Number.get(index))};
							dtmbasket.addRow(value);
							index++;
						}
						float total=userbasket.calculateTotal(ItemsinBasket,Number);//updates total
						totalbasket.setText("Total: £"+total);
					}else {
						ItemsinBasket.add(item);//adds item to arraylist
						Number.add(1);//adds quantity of 1 to number arraylist
						dtmbasket.setRowCount(0);
						int index=0;
						for(Book book:ItemsinBasket) {
							Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),Number.get(index),(book.getPrice()*Number.get(index))};
							dtmbasket.addRow(value);
							index++;
						}
						float total=userbasket.calculateTotal(ItemsinBasket,Number);
						totalbasket.setText("Total: £"+total);
					}
				}else {
					JFrame f=new JFrame();
					JOptionPane.showMessageDialog(f,"NOT ENOUGH STOCK AVAILABLE TO ADD TO BASKET","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		addtobasket.setBounds(696, 420, 108, 19);
		panel_1.add(addtobasket);
	
		
		
		String[] columnnames2= {"Barcode","Title","Language","Quantity","Price"};
		JTable baskettable = new JTable(){
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		baskettable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollpane2.setViewportView(baskettable);
		dtmbasket=new DefaultTableModel();
		dtmbasket.setColumnIdentifiers(columnnames2);
		baskettable.setModel(dtmbasket);
		
		JLabel usercredits = new JLabel("Total Credits: £"+((Customer)currentuser).getCredit());
		usercredits.setBounds(677, 44, 125, 13);
		usercredits.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_2.add(usercredits);
		
		JButton btnNewButton_1 = new JButton("Checkout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ItemsinBasket.isEmpty()) {
					JFrame f=new JFrame();
					JOptionPane.showMessageDialog(f,"BASKET IS EMPTY","ERROR",JOptionPane.ERROR_MESSAGE);
				}else {
					float total=userbasket.calculateTotal(ItemsinBasket,Number);
					if(userbasket.Checkout((Customer) currentuser, total)) {//calls function to checkout basket
						//SUCCESS
						b.UpdateStock(ItemsinBasket,Number); //calls function to update Stock file
						((Customer)currentuser).updateCredit(total,currentuser.getID());
						String text= new String("\nThank You For Your Purchase!\n£"+total+" paid and your remaining credit balance is £"+ ((Customer)currentuser).getCredit()+
								".\nYour delivery address is "+currentuser.getHousenum() +" "+ currentuser.getAddress() +" "+ currentuser.getCity()); //concatenates values to string for receipt
						JFrame f=new JFrame();
						JOptionPane.showMessageDialog(f,text,"Checkout Successful",JOptionPane.INFORMATION_MESSAGE);
						//resets basket
						ItemsinBasket.clear();
						Number.clear();
						dtmbasket.setRowCount(0);
						totalbasket.setText("Total: £0.00");
						usercredits.setText("Total Credits: £"+((Customer)currentuser).getCredit());
						
						
					}else { //user doesnt have enough credits
						JFrame f=new JFrame();
						JOptionPane.showMessageDialog(f,"Not Enough Credits to Pay for Basket","ERROR",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			
		});
		btnNewButton_1.setBounds(10, 422, 85, 21);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cancel");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemsinBasket.clear(); //clears contents of ArrayList
				Number.clear();
				dtmbasket.setRowCount(0); //clears basket table
				totalbasket.setText("Total: £0.00"); //resets total
			}
		});
		btnNewButton_1_1.setBounds(719, 428, 85, 21);
		panel_2.add(btnNewButton_1_1);
		
		
		
		JButton removebtn = new JButton("Remove");
		removebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = baskettable.getSelectedRow();
				if(row==-1) {
					JFrame f=new JFrame();
					JOptionPane.showMessageDialog(f,"ITEM NOT SELECTED","ERROR",JOptionPane.ERROR_MESSAGE);
				}else {
					//removes selected row
					ItemsinBasket.remove(row); 
					Number.remove(row); //removes corresponding quantity value
					//updates basket table
					dtmbasket.setRowCount(0);
					int index=0;
					for(Book book:ItemsinBasket) {
						Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),Number.get(index),(book.getPrice()*Number.get(index))};
						dtmbasket.addRow(value);
						index++;
					}
					//updates total of basket
					float total=userbasket.calculateTotal(ItemsinBasket,Number);
					totalbasket.setText("Total: £"+total);
				}
				
				
			}
		});
		removebtn.setBounds(635, 428, 85, 21);
		panel_2.add(removebtn);
		
		
		JLabel Basketlbl = new JLabel("BASKET");
		Basketlbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		Basketlbl.setBounds(362, 23, 125, 21);
		panel_2.add(Basketlbl);
		
		JPanel panel_3= new JPanel();
		tabbedPane.addTab("Library",null, panel_3,null);
		panel_3.setLayout(null);
		
		JScrollPane scrollpane= new JScrollPane();
		scrollpane.setBounds(10, 51, 794, 386);
		panel_3.add(scrollpane);
		
		//prevents user from editing table
		table = new JTable(){
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		scrollpane.setViewportView(table);
		dtmview=new DefaultTableModel();
		dtmview.setColumnIdentifiers(columnnames);
		table.setModel(dtmview);
		
		JLabel lblNewLabel = new JLabel("BOOK LIBRARY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(324, 10, 203, 31);
		panel_3.add(lblNewLabel);
		
		JButton logoutbtn = new JButton("logout");
		logoutbtn.setBounds(720, 5, 85, 21);
		panel_2.add(logoutbtn);
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Mainframe mframe= new Mainframe();
				mframe.setVisible(true);
			}
		});
		
		
		JButton viewbtn = new JButton("View Books");
		viewbtn.setBounds(675, 20, 129, 21);
		panel_3.add(viewbtn);
		viewbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r = new Function();
				ArrayList<Book> ListofBooks= new ArrayList<Book>();
				ListofBooks= r.ReadBooks();
				dtmview.setRowCount(0);//used to clear table
				for(Book book:ListofBooks) {
					//gets values of attributes of each book depending on book type to add to table
					if(book.getType().equals("paperback")) {
						Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
								book.getQuantity(),book.getPrice(),((Paperback) book).getPages(),((Paperback) book).getCondition(),"",""}; //concatenates values into an object
						dtmview.addRow(value);
					}else if(book.getType().equals("ebook")) {
						Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
								book.getQuantity(),book.getPrice(),((EBook) book).getPages(),"","",((EBook) book).getFormat()};
						dtmview.addRow(value);
					}else if(book.getType().equals("audiobook")) {
						Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.getRelease_date(),
								book.getQuantity(),book.getPrice(),"","",((Audiobook) book).getLength(),((Audiobook) book).getFormat()};
						dtmview.addRow(value);
					}
					setVisible(true);
				}
			viewbtn.setText("Refresh"); //changes button text once button has been pressed
			}
		});
		
		
	}
}
