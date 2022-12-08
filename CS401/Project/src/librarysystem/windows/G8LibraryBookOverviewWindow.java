package librarysystem.windows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import business.Address;
import business.Author;
import business.Book;
import business.Context;
import business.CrudMode;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8PanelOverview;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class G8LibraryBookOverviewWindow extends G8PanelOverview implements G8Navigatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitle;
	private JLabel lblAuthors;
	private JTextField textFieldCopies;
	private JList listAuthors;
	private JButton btnSave;
	private CrudMode currentCrudMode = CrudMode.Read; 
	private int bookObjTagIndex = 0;
	private JTextField textFieldCheckoutLength;
	/**
	 * Create the panel.
	 */
	public G8LibraryBookOverviewWindow() {
		panelDetail.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(49, 26, 69, 20);
		panelDetail.add(lblIsbn);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(202, 23, 958, 26);
		panelDetail.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(49, 57, 69, 20);
		panelDetail.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(202, 54, 958, 26);
		panelDetail.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		lblAuthors = new JLabel("Authors");
		lblAuthors.setBounds(49, 91, 69, 20);
		panelDetail.add(lblAuthors);
		
		JLabel lblCopies = new JLabel("Copies");
		lblCopies.setBounds(49, 192, 69, 20);
		panelDetail.add(lblCopies);
		
		textFieldCopies = new JTextField();
		textFieldCopies.setBounds(202, 186, 958, 26);
		panelDetail.add(textFieldCopies);
		textFieldCopies.setColumns(10);
		
        listAuthors = new JList();
        listAuthors.setBorder(new LineBorder(new Color(10, 10, 10)));
		listAuthors.setBounds(0, 0, 1, 1);
		
		
		JScrollPane scrollBar = new JScrollPane(listAuthors);
		scrollBar.setBounds(202, 92, 958, 78);
		panelDetail.add(scrollBar);		
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToDb();
			}
		});
		btnSave.setBounds(202, 300, 115, 29);
		panelDetail.add(btnSave);
		
		JLabel lblCheckoutLength = new JLabel("Checkout Length (days)");
		lblCheckoutLength.setBounds(6, 244, 160, 20);
		panelDetail.add(lblCheckoutLength);
		
		textFieldCheckoutLength = new JTextField();
		textFieldCheckoutLength.setColumns(10);
		textFieldCheckoutLength.setBounds(202, 239, 958, 26);
		panelDetail.add(textFieldCheckoutLength);
	}
	
	public void fillWindow(List<Book> books) {
		Object[][] rows = new Object[books.size()][5];
		int i=0;
		for(Book book : books) {
			rows[i][0] = book;
			rows[i][1] = book.getTitle();
			rows[i][2] = String.join(", ", book.getAuthors().stream().map(e -> e.toString()).collect(Collectors.toList())); //String.join(", ", book.getAuthors().toArray());
			rows[i][3] = book.getNumCopies();
			rows[i][4] = book.getMaxCheckoutLength();
			i++;
		}
		this.table.setModel(new DefaultTableModel(
				rows,
				new String[] {
					"ISBN", "Title", "Authors" , "NumOfCopy", "Checkout Days"
				}
			) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
			}
		});
		this.table.setShowGrid(true);
	}

	public G8LibraryBookOverviewWindow(String title) {
		this();
		setTitle(title);
	}
	
	@Override
	public boolean isNavigatorItemVisible() {
		return Context.isAuth(Auth.ADMIN) || Context.isAuth(Auth.LIBRARIAN) || Context.isAuth(Auth.BOTH);
	}
	
	@Override
	public void populate() {
		SystemController sc = new SystemController();		
		fillWindow(sc.allBooks());
		setFieldStatus(CrudMode.Read);
	}
	
	@Override
	protected void selectionChanged() {
		System.out.println("table.getSelectedRow(): " + table.getSelectedRow());
		if(table.getSelectedRow() < 0) {
			//when load is clicked if new book is added
			return;
		}
		Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
		
		this.textFieldIsbn.setText(book.getIsbn());
		this.textFieldTitle.setText(book.getTitle());
		this.textFieldCopies.setText(book.getCopies().length  + "");
		this.textFieldCheckoutLength.setText(String.valueOf(book.getMaxCheckoutLength()));
		DefaultListModel listModel = new DefaultListModel();
		listModel.addAll(book.getAuthors());
		listAuthors.setModel(listModel);		
	}
	
	private void setFieldStatus(CrudMode mode) {
		this.textFieldIsbn.setEditable(mode == CrudMode.Create);
		this.textFieldTitle.setEditable(mode == CrudMode.Create || mode == CrudMode.Update);
		this.textFieldCopies.setEditable(mode == CrudMode.Create || mode == CrudMode.Update);
		this.listAuthors.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
		this.btnSave.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
		this.textFieldCheckoutLength.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
	}
	
	@Override
	protected void deleteClicked() {
		super.deleteClicked();
		currentCrudMode = CrudMode.Delete;
		setFieldStatus(CrudMode.Delete);
		this.populate();
	}
	@Override
	protected void newClicked() {
		super.newClicked();
		currentCrudMode = CrudMode.Create;
		setFieldStatus(CrudMode.Create);
		clearBookUIFieldsForNewCreation();
	}
	
	private void clearBookUIFieldsForNewCreation() {
		this.textFieldIsbn.setText("");
		this.textFieldTitle.setText("");
		this.textFieldCopies.setText("");
		//Object[] authorsObjList = this.listAuthors.getSelectedValues();
		DefaultListModel listModel = new DefaultListModel();
		listModel.addAll(SystemController.getInstance().getAllAuthors());
		listAuthors.setModel(listModel);
	}

	@Override
	protected void editClicked() {
		super.editClicked();
		currentCrudMode = CrudMode.Update;
		setFieldStatus(CrudMode.Update);
	}
	
	private Book createBookFromUIFields() {
		String isbn = this.textFieldIsbn.getText();
		String title = this.textFieldTitle.getText();
		String numCopies = this.textFieldCopies.getText();
		String checkoutLength = this.textFieldCheckoutLength.getText();
		List<Author> authors = new ArrayList<>();
		
		if(currentCrudMode == CrudMode.Create) {
			Object[] authorsObjList = this.listAuthors.getSelectedValues();
			for(Object obj: authorsObjList) {
				//Author(String f, String l, String t, Address a, String bio)
				authors.add((Author)obj);
			}
		} else {
			Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
			authors = book.getAuthors();
		}

		Book bk = new Book(isbn, title, 21, authors);
		
		return bk;
	}
	
	private void saveToDb() {
		switch(currentCrudMode) {
		case Create:
			Book book = createBookFromUIFields();
			SystemController.getInstance().saveBook(book);
			this.clearBookUIFieldsForNewCreation();
			this.populate();
			break;
		case Delete:
			break;
		case Update:
			//Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
			//update and save 
			Book book1 = createBookFromUIFields();
			SystemController.getInstance().saveBook(book1);
			
			break;
		default:
			break;
	}
		
	}
}
