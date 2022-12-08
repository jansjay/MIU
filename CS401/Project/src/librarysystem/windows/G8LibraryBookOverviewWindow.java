package librarysystem.windows;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

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
		
		JLabel lblNewLabel = new JLabel("Copies");
		lblNewLabel.setBounds(49, 192, 69, 20);
		panelDetail.add(lblNewLabel);
		
		textFieldCopies = new JTextField();
		textFieldCopies.setBounds(202, 186, 958, 26);
		panelDetail.add(textFieldCopies);
		textFieldCopies.setColumns(10);
		
        listAuthors = new JList();
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
		btnSave.setBounds(1045, 239, 115, 29);
		panelDetail.add(btnSave);
	}
	
	public void fillWindow(List<Book> books) {
		Object[][] rows = new Object[books.size()][3];
		int i=0;
		for(Book book : books) {
			rows[i][0] = book.getIsbn();
			rows[i][1] = book.getTitle();
			rows[i][2] = book;
			i++;
		}
		this.table.setModel(new DefaultTableModel(
				rows,
				new String[] {
					"ISBN", "Title", "Object"
				}
			){
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false
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
		this.textFieldIsbn.setText(this.table.getValueAt(table.getSelectedRow(), 0).toString());
		this.textFieldTitle.setText(this.table.getValueAt(table.getSelectedRow(), 1).toString());
		Book book = (Book)this.table.getValueAt(table.getSelectedRow(), 2);
		this.textFieldCopies.setText(book.getCopies().length  + "");
		DefaultListModel listModel = new DefaultListModel();
		listModel.addAll(book.getAuthors());
		listAuthors.setModel(listModel);		
	}
	
	private void setFieldStatus(CrudMode mode) {
		this.textFieldIsbn.setEditable(mode == CrudMode.Create);
		this.textFieldTitle.setEditable(mode == CrudMode.Create || mode == CrudMode.Update);
		this.textFieldCopies.setEditable(false);
		this.listAuthors.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
		this.btnSave.setEnabled(mode == CrudMode.Create || mode == CrudMode.Update);
	}
	
	@Override
	protected void deleteClicked() {
		super.deleteClicked();
		currentCrudMode = CrudMode.Delete;
		setFieldStatus(CrudMode.Delete);
		
	}
	@Override
	protected void newClicked() {
		super.newClicked();
		currentCrudMode = CrudMode.Create;
		setFieldStatus(CrudMode.Create);
	}
	@Override
	protected void editClicked() {
		super.editClicked();
		currentCrudMode = CrudMode.Update;
		setFieldStatus(CrudMode.Update);
	}
	
	private void saveToDb() {
		switch(currentCrudMode) {
		case Create:
			break;
		case Delete:
			break;
		case Update:
			Book book = (Book)this.table.getValueAt(table.getSelectedRow(), 2);
			//update and save 
			break;
		default:
			break;
	}
	}
	
}
