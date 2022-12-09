package librarysystem.windows;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import business.Context;
import business.ControllerInterface;
import business.DataModelMapper;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8JPanel;
import librarysystem.controls.G8Navigatable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class G8CheckoutBook extends G8JPanel implements G8Navigatable {

	private static final long serialVersionUID = 1L;
	G8JPanel mainPanel;
	private JTextField txtBookSearchField;
	private JTable table;
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

	public G8CheckoutBook() {
		initialize();
		controller = new SystemController();
	}
	
	public G8CheckoutBook(String title) {
		this();
		setTitle(title);
	}

	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		mainPanel = new G8JPanel();
		add(mainPanel);
		mainPanel.setLayout(null);
		
		txtBookSearchField = new JTextField();
		txtBookSearchField.setToolTipText("Search Book by ISBN or Title");
		txtBookSearchField.setBounds(279, 17, 224, 20);
		mainPanel.add(txtBookSearchField);
		txtBookSearchField.setColumns(20);
		
		btnBookSearch = new JButton("Search");
		btnBookSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = txtBookSearchField.getText();
				bookModel.setRowCount(0);
				DataModelMapper
				.addBooks(controller.searchBookByIsbnOrTitle(isbn), bookModel);
			}
		});
		btnBookSearch.setBounds(520, 14, 147, 26);
		mainPanel.add(btnBookSearch);
		
		String[] bookColumns = getBookModelColumns();
		bookModel = new DefaultTableModel();
		bookModel.setColumnIdentifiers(bookColumns);
		
		table = new JTable();
		table.setModel(bookModel);
		table.setBackground(new Color(255, 240, 245));
		table.setBounds(597, 90, -533, -45);
		
		bookScrollPane = new JScrollPane();
		bookScrollPane.setBounds(6, 54, 661, 84);
		bookScrollPane.setViewportView(table);
		mainPanel.add(bookScrollPane);
		
		txtMemberSearchField = new JTextField();
		txtMemberSearchField.setToolTipText("Search Libarary Member by Member ID");
		txtMemberSearchField.setColumns(20);
		txtMemberSearchField.setBounds(279, 162, 224, 20);
		mainPanel.add(txtMemberSearchField);
		
		btnSearchMember = new JButton("Search");
		btnSearchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String member = txtMemberSearchField.getText();
				
				memberModel.setRowCount(0);
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
		btnSearchMember.setBounds(520, 159, 147, 26);
		mainPanel.add(btnSearchMember);
		
		memberModel = new DefaultTableModel();
		memberModel.setColumnIdentifiers(getMemberModelColumns());
		
		
		tblMember = new JTable();
		tblMember.setModel(memberModel);
		tblMember.setBounds(555, 278, -515, -45);
		tblMember.setBackground(new Color(255, 240, 245));
		
		memberScrollPane = new JScrollPane();
		memberScrollPane.setBounds(10, 199, 657, 102);
		memberScrollPane.setViewportView(tblMember);
		mainPanel.add(memberScrollPane);
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setBounds(520, 312, 149, 26);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIsbn = table.getSelectedRow();
				int selectedMemId = tblMember.getSelectedRow();
				if(selectedIsbn < 0 ||  selectedMemId < 0) return;
				String memberId = tblMember.getValueAt(selectedMemId,0).toString();
				String isbn = table.getValueAt(selectedIsbn,0).toString();
				try{controller.checkoutBook(memberId, isbn);}
				catch(Exception ex) {}
				checkoutModel.setRowCount(0);
				List<business.CheckoutRecord> crs = controller.getCheckedOutBookByMemberIdOrIsbn(memberId);
				DataModelMapper.addAllCheckoutBook(crs,checkoutModel, false);
			}
		});
		mainPanel.add(btnCheckOut);
		
		checkoutModel = new DefaultTableModel();
		checkoutModel.setColumnIdentifiers(getCheckoutModelColumns());
		tblCheckout = new JTable();
		tblCheckout.setModel(checkoutModel);
		tblCheckout.setBounds(464, 502, -515, -45);
		tblCheckout.setBackground(new Color(255, 240, 245));
		checkoutScrollPane = new JScrollPane();
		checkoutScrollPane.setBounds(10, 415, 657, 152);
		checkoutScrollPane.setViewportView(tblCheckout);
		mainPanel.add(checkoutScrollPane);
		
		JButton btnSearchCheckedOut = new JButton("Search");
		btnSearchCheckedOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutModel.setRowCount(0);
				String value = txtCheckoutSearch.getText();
				if(value.isEmpty()) return;
				
				List<business.CheckoutRecord> crs = controller.getCheckedOutBookByMemberIdOrIsbn(value);
				DataModelMapper.addAllCheckoutBook(crs,checkoutModel, false);
			}
		});
		btnSearchCheckedOut.setBounds(520, 372, 147, 26);
		mainPanel.add(btnSearchCheckedOut);
		
		txtCheckoutSearch = new JTextField();
		txtCheckoutSearch.setToolTipText("ISBN Or Member ID");
		txtCheckoutSearch.setColumns(20);
		txtCheckoutSearch.setBounds(279, 375, 224, 20);
		mainPanel.add(txtCheckoutSearch);
		
		JLabel lblSearchBook = new JLabel("Search Books (ISBN or Title)");
		lblSearchBook.setBounds(28, 20, 241, 14);
		mainPanel.add(lblSearchBook);
		
		JLabel lblMemberID = new JLabel("Search Members (Member ID)");
		lblMemberID.setBounds(28, 165, 241, 14);
		mainPanel.add(lblMemberID);
		
		JLabel lblNewLabel = new JLabel("Search Checkout (Member ID or ISBN)");
		lblNewLabel.setBounds(28, 378, 241, 14);
		mainPanel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 358, 657, 9);
		mainPanel.add(separator);
		
	}

	private String[] getBookModelColumns() {
		return new String[] {
				"ISBN",
				"Title",
				"Authors",
				"No Of Copies",
				"Max Checkout Length"
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
			"Copy Number",
			"Checked Out Date",
			"Return Date",			
			"Member Id",
			"Member Name",
			"Status"
		};
	}

	@Override
	public boolean isNavigatorItemVisible() {
		return Context.isAuth(Auth.LIBRARIAN) || Context.isAuth(Auth.BOTH);		
	}
}
