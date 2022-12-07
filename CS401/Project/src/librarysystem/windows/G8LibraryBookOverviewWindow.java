package librarysystem.windows;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Context;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8PanelOverview;

public class G8LibraryBookOverviewWindow extends G8PanelOverview implements G8Navigatable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8LibraryBookOverviewWindow() {
	}
	
	public void fillWindow(List<String> bookIds) {
		String[][] rows = new String[bookIds.size()][2];
		int i=0;
		for(String bookId : bookIds) {
			rows[i][0] = i + "";
			rows[i][1] = bookId;
			i++;
		}
		this.getOverviewTable().setModel(new DefaultTableModel(
				rows,
				new String[] {
					"Index", "Book ID"
				}
			));
	}

	public G8LibraryBookOverviewWindow(String title) {
		setTitle(title);
	}
	
	@Override
	public boolean isNavigatorItemVisible() {
		return Context.isAuth(Auth.ADMIN) || Context.isAuth(Auth.LIBRARIAN) || Context.isAuth(Auth.BOTH);
	}
	
	@Override
	public void populate() {
		List<Book> books = new ArrayList<>();
		SystemController sc = new SystemController();		
		fillWindow(sc.allBookIds());
	}
}
