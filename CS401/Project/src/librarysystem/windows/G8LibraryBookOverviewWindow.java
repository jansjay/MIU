package librarysystem.windows;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.Book;
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
	
	public void fillWindow(List<Book> books) {
		String[][] rows = new String[2][books.size()];
		int i=0;
		for(Book book : books) {
			rows[i][0] = book.getIsbn();
			rows[i][1] = book.getTitle();
			i++;
		}
		this.getOverviewTable().setModel(new DefaultTableModel(
				rows,
				new String[] {
					"Title", "ISBN"
				}
			));
	}

	public G8LibraryBookOverviewWindow(String title) {
		setTitle(title);
	}
	
	@Override
	public boolean isNavigatorItemVisible() {
		// Check has access
		return true;
	}
}
