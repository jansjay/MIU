package librarysystem.windows;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
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

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.Font;

public class G8CheckoutSearchWindow extends G8JPanel implements G8Navigatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JFrame frame;
	G8JPanel mainPanel;
	private JTable tblCheckout;
	private JScrollPane checkoutScrollPane;
	private ControllerInterface controller;
	private DefaultTableModel memberModel;
	private DefaultTableModel bookModel;
	private DefaultTableModel checkoutModel;
	private JTextField txtCheckoutSearch;
	private JCheckBox chckbxOverdueOnly;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public G8CheckoutSearchWindow() {
		initialize();
		controller = new SystemController();
	}
	
	public G8CheckoutSearchWindow(String title) {
		this();
		setTitle(title);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*frame = new JFrame();
		frame.setBounds(100, 100, 691, 643);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		setLayout(new GridLayout(0, 1, 0, 0));
		
		mainPanel = new G8JPanel();
		add(mainPanel);
		mainPanel.setLayout(null);
		
		String[] bookColumns = getBookModelColumns();
		bookModel = new DefaultTableModel();
		bookModel.setColumnIdentifiers(bookColumns);
		
		memberModel = new DefaultTableModel();
		memberModel.setColumnIdentifiers(getMemberModelColumns());
		
		checkoutModel = new DefaultTableModel();
		checkoutModel.setColumnIdentifiers(getCheckoutModelColumns());
		tblCheckout = new JTable();
		tblCheckout.setModel(checkoutModel);
		tblCheckout.setBounds(464, 502, -515, -45);
		tblCheckout.setBackground(new Color(255, 240, 245));
		checkoutScrollPane = new JScrollPane();
		checkoutScrollPane.setBounds(26, 85, 791, 152);
		checkoutScrollPane.setViewportView(tblCheckout);
		mainPanel.add(checkoutScrollPane);
		
		JButton btnSearchCheckedOut = new JButton("Search");
		btnSearchCheckedOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutModel.setRowCount(0);
				String value = txtCheckoutSearch.getText();
				//if(value.isEmpty()) return;
				
				List<business.CheckoutRecord> crs = controller.searchCheckedOutBookByMemberIdOrIsbn(value);
				DataModelMapper.addAllCheckoutBook(crs,checkoutModel, chckbxOverdueOnly.isSelected());
			}
		});
		btnSearchCheckedOut.setBounds(670, 48, 147, 26);
		mainPanel.add(btnSearchCheckedOut);
		
		txtCheckoutSearch = new JTextField();
		txtCheckoutSearch.setToolTipText("ISBN Or Member ID");
		txtCheckoutSearch.setColumns(20);
		txtCheckoutSearch.setBounds(290, 18, 400, 20);
		mainPanel.add(txtCheckoutSearch);
		
		JLabel lblNewLabel = new JLabel("Search Checkout (Member ID or ISBN)");
		lblNewLabel.setBounds(26, 21, 241, 14);
		mainPanel.add(lblNewLabel);
		
		chckbxOverdueOnly = new JCheckBox("Search Overdue only");
		chckbxOverdueOnly.setBounds(696, 18, 147, 23);
		mainPanel.add(chckbxOverdueOnly);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer buffer = new StringBuffer();
				String topOrBottom = "";
				for (int col = 0; col < tblCheckout.getColumnCount(); col++) {
					topOrBottom += "|" + rightPad("=", 50, "=") + "|";					
			    	buffer.append("|" + rightPad(tblCheckout.getColumnName(col), 50, " ") + "|");
				}
				buffer.append("\n");
				buffer.append(topOrBottom);
				buffer.append("\n");
				for (int row = 0; row < tblCheckout.getRowCount(); row++) {
				    for (int col = 0; col < tblCheckout.getColumnCount(); col++) {
				    	buffer.append("|" + rightPad(tblCheckout.getValueAt(row, col).toString(), 50, " ") + "|");
				    }
				    buffer.append("\n");
				}
				buffer.append(topOrBottom);
				buffer.insert(0, "\n");
				buffer.insert(0, topOrBottom);
				textArea.setText(buffer.toString());
				System.out.println(buffer.toString());
			}
		});
		btnPrint.setBounds(702, 266, 115, 29);
		mainPanel.add(btnPrint);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		textArea.setBounds(26, 321, 791, 308);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 311, 791, 233);
		scrollPane.setViewportView(textArea);
		
		
		mainPanel.add(scrollPane);
	}

	private String rightPad(String value, int padLength, String character) {
		if(value.length() > padLength)
			return value.substring(0, padLength);
		for(int i = value.length(); i < padLength; i++)
			value += character;
		return value;
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
