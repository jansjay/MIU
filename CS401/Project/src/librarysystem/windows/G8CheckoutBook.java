package librarysystem.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.DataModelMapper;
import business.SystemController;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class G8CheckoutBook {

	 JFrame frame;
	private JTextField txtBookSearchField;
	private JTable tblBook;
	private JTextField txtMemberSearchField;
	private JButton btnSearchMember;
	private JScrollPane memberScrollPane;
	private JButton btnCheckOut;
	private JTable tblMember;
	private JTable tblCheckout;
	private JScrollPane checkoutScrollPane;
	private ControllerInterface controller;
	private DefaultTableModel memberModel;
	private DefaultTableModel bookModel;
	private DefaultTableModel checkoutModel;
	private JScrollPane bookScrollPane;
	private JButton btnBookSearch;
	private JTextField txtCheckoutSearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G8CheckoutBook window = new G8CheckoutBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public G8CheckoutBook() {
		initialize();
		controller = new SystemController();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 691, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtBookSearchField = new JTextField();
		txtBookSearchField.setToolTipText("Search Book by ISBN or Title");
		txtBookSearchField.setBounds(10, 11, 352, 32);
		frame.getContentPane().add(txtBookSearchField);
		txtBookSearchField.setColumns(20);
		
		btnBookSearch = new JButton("Search Book");
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = txtBookSearchField.getText();
				for(int i=0; i< bookModel.getRowCount();i++) {
					bookModel.removeRow(i);
				}
				DataModelMapper
				.addBooks(controller.searchBookByIsbnOrTitle(isbn), bookModel);
			}
		});
		btnBookSearch.setBounds(405, 11, 262, 32);
		frame.getContentPane().add(btnBookSearch);
		
		String[] bookColumns = getBookModelColumns();
		bookModel = new DefaultTableModel();
		bookModel.setColumnIdentifiers(bookColumns);
		
		tblBook = new JTable();
		tblBook.setModel(bookModel);
		tblBook.setBackground(new Color(255, 240, 245));
		tblBook.setBounds(597, 90, -533, -45);
		
		bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(6, 54, 661, 84);
		bookScrollPane.setViewportView(tblBook);
		frame.getContentPane().add(bookScrollPane);
		
		txtMemberSearchField = new JTextField();
		txtMemberSearchField.setToolTipText("Search Libarary Member by Member ID");
		txtMemberSearchField.setColumns(20);
		txtMemberSearchField.setBounds(10, 156, 352, 32);
		frame.getContentPane().add(txtMemberSearchField);
		
		btnSearchMember = new JButton("Search Member");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String member = txtMemberSearchField.getText();
				
				checkoutModel.setRowCount(0);
				if(member.isEmpty()) {
					DataModelMapper
					.addAllLibraryMember(controller.getLibraryMembers(), memberModel);
		
				}
				else {
					DataModelMapper
					.addLibraryMember(controller.searchMember(member), memberModel);
				}
			}
		});
		btnSearchMember.setBounds(405, 156, 262, 32);
		frame.getContentPane().add(btnSearchMember);
		
		memberModel = new DefaultTableModel();
		memberModel.setColumnIdentifiers(getMemberModelColumns());
		
		
		tblMember = new JTable();
		tblMember.setModel(memberModel);
		tblMember.setBounds(555, 278, -515, -45);
		tblMember.setBackground(new Color(255, 240, 245));
		
		memberScrollPane = new JScrollPane();
		memberScrollPane.setBounds(10, 199, 657, 102);
		memberScrollPane.setViewportView(tblMember);
		frame.getContentPane().add(memberScrollPane);
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setBounds(10, 333, 200, 32);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIsbn = tblBook.getSelectedRow();
				int selectedMemId = tblMember.getSelectedRow();
				if(selectedIsbn < 0 ||  selectedMemId < 0) return;
				String memberId = tblMember.getValueAt(selectedMemId,0).toString();
				String isbn = tblBook.getValueAt(selectedIsbn,0).toString();
				try{controller.checkoutBook(memberId, isbn);}
				catch(Exception ex) {}
				checkoutModel.setRowCount(0);
				List<business.CheckoutRecord> crs = controller.getCheckedOutBookByMemberIdOrIsbn(memberId);
				DataModelMapper.addAllCheckoutBook(crs,checkoutModel);
			}
		});
		frame.getContentPane().add(btnCheckOut);
		
		checkoutModel = new DefaultTableModel();
		checkoutModel.setColumnIdentifiers(getCheckoutModelColumns());
		tblCheckout = new JTable();
		tblCheckout.setModel(checkoutModel);
		tblCheckout.setBounds(464, 502, -515, -45);
		tblCheckout.setBackground(new Color(255, 240, 245));
		checkoutScrollPane = new JScrollPane();
		checkoutScrollPane.setBounds(10, 376, 657, 219);
		checkoutScrollPane.setViewportView(tblCheckout);
		frame.getContentPane().add(checkoutScrollPane);
		
		JButton btnSearchCheckedOut = new JButton("Search Checked Out");
		btnSearchCheckedOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = txtCheckoutSearch.getText();
				if(value.isEmpty()) return;
				checkoutModel.setRowCount(0);
				List<business.CheckoutRecord> crs = controller.getCheckedOutBookByMemberIdOrIsbn(value);
				DataModelMapper.addAllCheckoutBook(crs,checkoutModel);
			}
		});
		btnSearchCheckedOut.setBounds(439, 333, 200, 32);
		frame.getContentPane().add(btnSearchCheckedOut);
		
		txtCheckoutSearch = new JTextField();
		txtCheckoutSearch.setToolTipText("ISBN Or Member ID");
		txtCheckoutSearch.setColumns(20);
		txtCheckoutSearch.setBounds(230, 333, 186, 32);
		frame.getContentPane().add(txtCheckoutSearch);
		
	}

	private String[] getBookModelColumns() {
		return new String[] {
				"ISBN",
				"Title",
				"Authors",
				"Max Checkout Length",
				"No Of Copies"
		};
	}
	
	private String[] getMemberModelColumns() {
		return new String[]{
			"Member ID", 
			"First Name", 
			"Last Name",
			"Street", 
			"City", 
			"State", 
			"Zip", 
			"Telephone Number"
		};
	}
	
	private String[] getCheckoutModelColumns() {
		return new String[] {
			"Title",
			"ISBN",
			"Checked Out Date",
			"Return Date",
			
			"Member Id",
			"Member Name",
			"Status"
		};
	}
}
