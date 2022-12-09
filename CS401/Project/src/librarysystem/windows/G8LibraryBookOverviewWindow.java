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
import librarysystem.controls.G8EmptyInputVerifier;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8NumberInputVerifier;
import librarysystem.controls.G8PanelOverview;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
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
	private JList<Author> listAuthors;
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
		textFieldIsbn.setInputVerifier(new G8EmptyInputVerifier("ISBN", false));
		panelDetail.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(49, 57, 69, 20);
		panelDetail.add(lblTitle);

		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(202, 54, 958, 26);
		textFieldTitle.setInputVerifier(new G8EmptyInputVerifier("Title", false));
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
		textFieldCopies.setInputVerifier(new G8NumberInputVerifier("No of Copies", false));
		panelDetail.add(textFieldCopies);
		textFieldCopies.setColumns(10);
		

        listAuthors = new JList<Author>();
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
		textFieldCheckoutLength.setInputVerifier(new G8NumberInputVerifier("Checkout Length", false));
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
		super.btnLoadData.setText("All Books");
		super.lblSearch.setText("Search books (isbn or title)");
		super.btnAddBookCopy.setVisible(true);
		this.populate();
	}

	@Override
	public boolean isNavigatorItemVisible() {
		return Context.isAuth(Auth.LIBRARIAN) || Context.isAuth(Auth.BOTH);
	}

	@Override
	public void populate() {
		SystemController sc = new SystemController();		
		fillWindow(sc.allBooks());
		setFieldStatus(CrudMode.Read);
		this.clearBookUIFields();
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

		DefaultListModel<Author> listModel = new DefaultListModel<>();

		listModel.addAll(book.getAuthors());
		listAuthors.setModel(listModel);
		setFieldStatus(CrudMode.Read);
	}

	private void setFieldStatus(CrudMode mode) {
		this.textFieldIsbn.setEditable(mode == CrudMode.Create);
		this.textFieldTitle.setEditable(mode == CrudMode.Create || mode == CrudMode.Update);
		this.textFieldCopies.setEditable(mode == CrudMode.Create || mode == CrudMode.Update);
		this.listAuthors.setEnabled(mode == CrudMode.Create);
		this.btnSave.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
		this.textFieldCheckoutLength.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
	}

	@Override
	protected void deleteClicked() {
		super.deleteClicked();
		currentCrudMode = CrudMode.Delete;
		setFieldStatus(CrudMode.Delete);
		saveToDb();
	}
	@Override
	protected void newClicked() {
		super.newClicked();
		currentCrudMode = CrudMode.Create;
		setFieldStatus(CrudMode.Create);
		clearBookUIFields();
		populateAuthorsForNewBookCreation();
	}
	
	@Override 
	public void addBookCopyClicked() {
		System.out.println("addBookCopyClicked");
		if(table.getSelectedRow() < 0) {
			return;
		}
		
		Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
		book.addCopy();
		SystemController.getInstance().saveBook(book, CrudMode.Update);
		this.populate();
	}
	
	@Override 
	public void searchClicked() {
		if(this.textFieldSearch.getText().isEmpty()) {
			return;
		}
		SystemController sc = new SystemController();
		fillWindow(sc.searchBookByIsbnOrTitle(this.textFieldSearch.getText()));
		setFieldStatus(CrudMode.Read);
	}
	
	private void populateAuthorsForNewBookCreation() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addAll(SystemController.getInstance().getAllAuthors());
		listAuthors.setModel(listModel);
	}
	
	private void clearBookUIFields() {
		this.textFieldIsbn.setText("");
		this.textFieldTitle.setText("");
		this.textFieldCopies.setText("");
		this.textFieldCheckoutLength.setText("");
		this.textFieldSearch.setText("");
		DefaultListModel listModel = new DefaultListModel();
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

		//Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
		//authors = book.getAuthors();
		Object[] authorsObjList = this.listAuthors.getSelectedValues();
		System.out.println("selected Auth len: " + authorsObjList.length);
		for(Object obj: authorsObjList) {
			System.out.println("author: " + obj);
			authors.add((Author)obj);
		}

		Book bk = new Book(isbn, title, Integer.parseInt(checkoutLength), authors);
		for(int i = 1; i < Integer.parseInt(numCopies); i++) {
			bk.addCopy();
		}

		return bk;
	}

	private void saveToDb() {
		try {
		switch(currentCrudMode) {
		case Create:
			Book newBook = createBookFromUIFields();
			SystemController.getInstance().saveBook(newBook, currentCrudMode);
			this.clearBookUIFields();
			getG8JFrame().setSuccessMessage("Book created successfully!!!");
			
			break;
		case Delete:
			if(table.getSelectedRow() < 0) {
				return;
			}
			Book delBook = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
			int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to delete the book with ISBN: " + delBook.getIsbn() + "?");
			if(confirmation!=0) return;
			System.out.println("Delete book isbn: " + delBook.getIsbn());
			SystemController.getInstance().deleteBook(delBook);
			getG8JFrame().setSuccessMessage("Book deleted successfully!!!");			
			this.clearBookUIFields();
			break;
		case Update:
			if(table.getSelectedRow() < 0) {
				return;
			}
			Book book = (Book)this.table.getValueAt(table.getSelectedRow(), this.bookObjTagIndex);
			book.setTitle(textFieldTitle.getText());
			book.setMaxCheckoutLength(Integer.parseInt(textFieldCheckoutLength.getText()));
			SystemController.getInstance().saveBook(book, currentCrudMode);
			getG8JFrame().setSuccessMessage("Book saved successfully!!!");			
			break;
		default:
			break;
		}
		this.populate();
		}
		catch(Exception ex) {
			getG8JFrame().setErrorMessage(ex.getMessage());
		}
	}
}
