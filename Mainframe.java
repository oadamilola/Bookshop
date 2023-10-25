package bookshop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Choice;
import java.awt.Font;

public class Mainframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("WELCOME TO BOOKSHOP");
		lblNewJgoodiesTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewJgoodiesTitle.setBounds(324, 154, 221, 21);
		contentPane.add(lblNewJgoodiesTitle);
		
		String[] IDS= {"101","102","103","104"};

		JComboBox comboBox = new JComboBox(IDS);
		comboBox.setBounds(369, 185, 95, 21);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox text=(JComboBox)e.getSource();
				String id =(String)text.getSelectedItem();
				if(id=="101") {
					Adminframe aframe= new Adminframe(id);//creates new admin frame
					aframe.setVisible(true);
					dispose();
				}else if(id=="102") {
					Customerframe cframe= new Customerframe(id);//creates new customer frame
					cframe.setVisible(true);
					dispose();
				}else if(id=="103") {
					Customerframe cframe1= new Customerframe(id);
					cframe1.setVisible(true);
					dispose();
				}else if(id=="104") {
					Customerframe cframe2= new Customerframe(id);
					cframe2.setVisible(true);
					dispose();
				}
;				
			}
			
		});
		
		

	}
}
