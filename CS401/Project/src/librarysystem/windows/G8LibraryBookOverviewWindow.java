package librarysystem.windows;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Context;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8PanelOverview;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class G8LibraryBookOverviewWindow extends G8PanelOverview implements G8Navigatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitle;
	/**
	 * Create the panel.
	 */
	public G8LibraryBookOverviewWindow() {
		panelDetail.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(49, 26, 69, 20);
		panelDetail.add(lblIsbn);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(202, 23, 233, 26);
		panelDetail.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(49, 57, 69, 20);
		panelDetail.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(202, 52, 233, 26);
		panelDetail.add(textFieldTitle);
		textFieldTitle.setColumns(10);
	}
	
	public void fillWindow(List<Book> books) {
		String[][] rows = new String[books.size()][2];
		int i=0;
		for(Book book : books) {
			rows[i][0] = book.getIsbn();
			rows[i][1] = book.getTitle();			
			i++;
		}
		this.table.setModel(new DefaultTableModel(
				rows,
				new String[] {
					"ISBN", "Title"
				}
			));
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
	}
	
	@Override
	protected void selectionChanged() {
		this.textFieldIsbn.setText(this.table.getValueAt(table.getSelectedRow(), 0).toString());
		this.textFieldTitle.setText(this.table.getValueAt(table.getSelectedRow(), 1).toString());
	}
}
