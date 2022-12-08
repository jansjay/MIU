package librarysystem.windows;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Context;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8PanelDetails;
import librarysystem.controls.G8PanelOverview;

public class G8LibraryBookOverviewWindow extends G8PanelOverview implements G8Navigatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private G8LibraryBookDetailsWindow detailsWindow;
	/**
	 * Create the panel.
	 */
	public G8LibraryBookOverviewWindow() {
	}
	
	public void fillWindow(List<Book> books) {
		String[][] rows = new String[books.size()][2];
		int i=0;
		for(Book book : books) {
			rows[i][0] = book.getIsbn();
			rows[i][1] = book.getTitle();			
			i++;
		}
		this.getOverviewTable().setModel(new DefaultTableModel(
				rows,
				new String[] {
					"ISBN", "Title"
				}
			));
		this.getOverviewTable().setShowGrid(true);
	}

	public G8LibraryBookOverviewWindow(String title, G8LibraryBookDetailsWindow detailsWindow) {
		this();
		this.detailsWindow = detailsWindow;
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
}
