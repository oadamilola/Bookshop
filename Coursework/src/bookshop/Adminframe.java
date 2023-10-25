package bookshop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

public class Adminframe extends JFrame {

	private JPanel contentPane;
	private JTextField Barcode;
	private JTextField Title;
	private JTextField RDate;
	private JTextField Price;
	private JTextField Quantity;
	private JTextField pagestext;
	private JTextField conditiontext;
	private JTextField lengthtext;
	private JTextField formattext;
	private JTable table;
	Function r;
	Paperback p;
	EBook eb;
	Audiobook a;
	Book b;
	private DefaultTableModel dtmview;
	private String id;

	/**
	 * Create the frame.
	 * @param aframe 
	 * @param id 
	 * @throws FileNotFoundException 
	 */
	public Adminframe(String receivedid) {
		setTitle("Admin User ID: "+receivedid);//throws FileNotFoundException
		this.id=receivedid;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 839, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 815, 513);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("View Books", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollpane= new JScrollPane();
		scrollpane.setBounds(10, 10, 790, 364);
		panel_1.add(scrollpane);
		
		JButton logoutbtn = new JButton("logout");
		logoutbtn.setBounds(715, 440, 85, 21);
		panel_1.add(logoutbtn);
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Mainframe mframe= new Mainframe();
				mframe.setVisible(true);
			}
		});
		
		
		String[] columnnames= {"Barcode","Title","Language","Genre","Release Date","Quantity","Retail Price","Pages","Condition","Length","Format"};
		table = new JTable() {
			public boolean isCellEditable(int row,int column) {
				return false;
			}
		};
		scrollpane.setViewportView(table);
		dtmview=new DefaultTableModel() {
			public Class<?> getColumnClass(int cindex){ //sorts table according to quantity
				if(cindex==5) {
					return Integer.class;
				}else {
					return String.class;
				}
			}
		};
		dtmview.setColumnIdentifiers(columnnames);
		table.setModel(dtmview);
		
		//creates button used to view all books
		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dtmview.setRowCount(0); 
					r = new Function();
					ArrayList<Book> ListofBooks= new ArrayList<Book>();
					ListofBooks= r.ReadBooks();
					for(Book book:ListofBooks) { 
						//creates objects using the book attributes values so it can be added as a row to table
						if(book.getType().equals("paperback")) {
							Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.DtoString(book.getRelease_date()),
									book.getQuantity(),book.getPrice(),((Paperback) book).getPages(),((Paperback) book).getCondition(),"",""};
							dtmview.addRow(value);
						}else if(book.getType().equals("ebook")) {
							Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.DtoString(book.getRelease_date()),
									book.getQuantity(),book.getPrice(),((EBook) book).getPages(),"","",((EBook) book).getFormat()};
							dtmview.addRow(value);
						}else if(book.getType().equals("audiobook")) {
							Object[] value= new Object[] {book.getBarcode(),book.getTitle(),book.getLanguage(),book.getGenre(),book.DtoString(book.getRelease_date()),
									book.getQuantity(),book.getPrice(),"","",((Audiobook) book).getLength(),((Audiobook) book).getFormat()};
							dtmview.addRow(value);
						}
					}
					btnNewButton_1.setText("Refresh");
					TableRowSorter<DefaultTableModel>sorter= new TableRowSorter<DefaultTableModel>(dtmview);
					table.setRowSorter(sorter);
					sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(5,SortOrder.ASCENDING)));
	
			}
		});
		btnNewButton_1.setBounds(10, 394, 85, 21);
		panel_1.add(btnNewButton_1);
				
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Books", null, panel, null);
		panel.setLayout(null);
		
		pagestext = new JTextField();
		pagestext.setColumns(10);
		pagestext.setBounds(306, 347, 168, 19);
		panel.add(pagestext);
		pagestext.setVisible(true);
		
		JLabel pageslabel = new JLabel("Pages");
		pageslabel.setBounds(199, 347, 85, 13);
		panel.add(pageslabel);
		pageslabel.setVisible(true);
		
		conditiontext = new JTextField();
		conditiontext.setColumns(10);
		conditiontext.setBounds(306, 387, 168, 19);
		panel.add(conditiontext);
		conditiontext.setVisible(true);
		
		JLabel conditionlabel = new JLabel("Condition");
		conditionlabel.setBounds(199, 387, 85, 13);
		panel.add(conditionlabel);
		conditionlabel.setVisible(true);
		
		
		JLabel lengthlabel = new JLabel("Length");
		lengthlabel.setBounds(199, 347, 85, 13);
		panel.add(lengthlabel);
		lengthlabel.setVisible(false);
		
		//comboBox to select book language
		String[] languages= {"English","French"};
		JComboBox comboBox2 = new JComboBox(languages);
		comboBox2.setBounds(306, 154, 168, 20);
		panel.add(comboBox2);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//comboBox to select book genre
		String[] genres= {"Politics","Business","Computer Science","Biography"};
		JComboBox comboBox3 = new JComboBox(genres);
		comboBox3.setBounds(306, 182, 168, 20);
		panel.add(comboBox3);
		comboBox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JLabel formatlabel = new JLabel("Format");
		formatlabel.setBounds(199, 387, 85, 13);
		panel.add(formatlabel);
		formatlabel.setVisible(false);
		
		//comboBox to select book type
		String[] booktypes= {"paperback","audiobook","ebook"};
		JComboBox comboBox = new JComboBox(booktypes);
		comboBox.setBounds(306, 305, 168, 20);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox text=(JComboBox)e.getSource();
				String booktype =(String)text.getSelectedItem();
				//sets hides and makes visible labels according to book type
				if(booktype=="paperback") {
					pageslabel.setVisible(true);
					conditionlabel.setVisible(true);
					lengthlabel.setVisible(false);
					formatlabel.setVisible(false);
					
				}else if(booktype=="ebook") {
					pageslabel.setVisible(true);
					conditionlabel.setVisible(false);
					lengthlabel.setVisible(false);
					formatlabel.setVisible(true);
					
				}else if(booktype=="audiobook") {
					pageslabel.setVisible(false);
					conditionlabel.setVisible(false);
					lengthlabel.setVisible(true);
					formatlabel.setVisible(true);

					
				}
			}
		});
		
		//button to add new book
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book b = new Book(null, 0, null, "English", "Business", null, 0, 0) ;
				r = new Function();
				//gets all input values 
				String booktype = (String) comboBox.getSelectedItem();
				String barinp=Barcode.getText().trim();
				String titleinp=Title.getText().trim();
				String langinp=(String) comboBox2.getSelectedItem();
				String genreinp=(String) comboBox3.getSelectedItem();;
				String dateinp=RDate.getText().trim();
				String quantityinp=Quantity.getText().trim();
				String priceinp=Price.getText().trim();
				String value1=pagestext.getText().trim();
				String value2=conditiontext.getText().trim();
				//checks all input fields have been filled
				if((barinp.equals(""))||(titleinp.equals(""))||(langinp.equals(""))||(genreinp.equals(""))
				||(dateinp.equals(""))||(quantityinp.equals(""))||(priceinp.equals(""))||(value1.equals(""))
				||(value2.equals(""))) {
					JFrame f=new JFrame();
					JOptionPane.showMessageDialog(f,"ALL FIELDS MUST BE FILLED","ERROR",JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(!(priceinp.matches("\\d*\\.?d{0,2}"))) { //checks price is correct format
						JFrame f=new JFrame();
						JOptionPane.showMessageDialog(f,"INVALID PRICE","ERROR",JOptionPane.INFORMATION_MESSAGE);
					}else {
						ArrayList<Book> Books= new ArrayList<Book>();
						Books= r.ReadBooks();//reads books from file
						if(!(r.Exists(Books, barinp))) { //checks if book exists
							if((b.checkbarcode(barinp)&&(r.isNumber(quantityinp))
									&& (b.checkdate(dateinp)))) { //checks date quantity and barcode are valid
								if(booktype.equals("paperback")) {	
									if((((Paperback) b).checkcondition(value2))&&(r.isNumber(value1))){ //checks condition and pages is valid
										r.Addbook(r.AddtoList(booktype, Integer.parseInt(barinp), titleinp, langinp, genreinp, dateinp, Integer.parseInt(quantityinp),Float.parseFloat(priceinp),
												Integer.parseInt(value1), value2,Float.parseFloat(priceinp)));	//adds book to file
										JFrame f=new JFrame();
										JOptionPane.showMessageDialog(f,"Book Has Been Added","Success",JOptionPane.INFORMATION_MESSAGE);
										//valid 
									}
								
								}else if(booktype.equals("ebook")) {
									if( (((EBook) b).checkEformat(value2)) &&  (r.isNumber(value1))){ //checks format and pages are valid
										r.Addbook(r.AddtoList(booktype, Integer.parseInt(barinp), titleinp, langinp, genreinp, dateinp, Integer.parseInt(quantityinp),Float.parseFloat(priceinp),
												Integer.parseInt(value1), value2,Float.parseFloat(priceinp)));//adds book to file
										JFrame f=new JFrame();
										JOptionPane.showMessageDialog(f,"Book Has Been Added","Success",JOptionPane.INFORMATION_MESSAGE);
										//valid
									}
								
								
								}else if(booktype.equals("audiobook")) {
									if((((Audiobook) b).checkaudioformat(value2))&& (value1.matches("\\d*\\.?d{0,1}")) ){ //checks format and listening length
										r.Addbook(r.AddtoList(booktype, Integer.parseInt(barinp), titleinp, langinp, genreinp, dateinp, Integer.parseInt(quantityinp),Float.parseFloat(priceinp),
												Integer.parseInt(quantityinp), value2,Float.parseFloat(value1)));//adds book to file
										JFrame f=new JFrame();
										JOptionPane.showMessageDialog(f,"Book Has Been Added","Success",JOptionPane.INFORMATION_MESSAGE);
										//valid
									}
								}
							}else {
								JFrame f=new JFrame();
								JOptionPane.showMessageDialog(f,"INVALID INPUT(S)","ERROR",JOptionPane.ERROR_MESSAGE);
							}
						
						}else {
							JFrame f=new JFrame();
							JOptionPane.showMessageDialog(f,"BOOK ID ALREADY EXISTS","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
					
			}
		});
		btnNewButton.setBounds(578, 326, 107, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Book Type");
		lblNewLabel.setBounds(199, 306, 107, 13);
		panel.add(lblNewLabel);
		
		Barcode = new JTextField();
		Barcode.setBounds(306, 96, 168, 19);
		panel.add(Barcode);
		Barcode.setColumns(10);
		
		Title = new JTextField();
		Title.setColumns(10);
		Title.setBounds(306, 125, 168, 19);
		panel.add(Title);
		
		JLabel lblNewLabel_1 = new JLabel("Barcode");
		lblNewLabel_1.setBounds(199, 99, 97, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Title");
		lblNewLabel_1_1.setBounds(199, 128, 97, 13);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Language");
		lblNewLabel_1_2.setBounds(199, 157, 97, 13);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Genre");
		lblNewLabel_1_3.setBounds(199, 186, 97, 13);
		panel.add(lblNewLabel_1_3);
		
		RDate = new JTextField();
		RDate.setColumns(10);
		RDate.setBounds(306, 212, 168, 19);
		panel.add(RDate);
		
		Quantity = new JTextField();
		Quantity.setColumns(10);
		Quantity.setBounds(306, 241, 168, 19);
		panel.add(Quantity);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(306, 271, 168, 19);
		panel.add(Price);
		
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Release Date");
		lblNewLabel_1_3_1.setBounds(199, 215, 97, 13);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Quantity");
		lblNewLabel_1_3_2.setBounds(199, 244, 85, 13);
		panel.add(lblNewLabel_1_3_2);
		
		JLabel pricelbl = new JLabel("Price");
		pricelbl.setBounds(199, 272, 85, 13);
		panel.add(pricelbl);
		
		
		JLabel lblNewLabel_2 = new JLabel("Add New Book");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(335, 50, 146, 30);
		panel.add(lblNewLabel_2);
		
		
	}
}
