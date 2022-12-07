package librarysystem.windows;

import business.Book;
import business.CrudMode;
import librarysystem.controls.G8PanelDetails;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class G8LibraryBookDetailsWindow extends G8PanelDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTitle;

	/**
	 * Create the panel.
	 */
	public G8LibraryBookDetailsWindow() {
		
		JLabel lblTitle = new JLabel("Title");
		add(lblTitle);
		
		textFieldTitle = new JTextField();
		add(textFieldTitle);
		textFieldTitle.setColumns(10);
	}

	public void fillWindow(Book book, CrudMode mode) {
		super.fillWindow(mode);
		if(mode == CrudMode.Read || mode == CrudMode.Delete || mode == CrudMode.Update){
			this.textFieldTitle.setText(book.getTitle());
		}
		else{
			this.textFieldTitle.setText("");
		}
	}
	
	public G8LibraryBookDetailsWindow(String title) {
		this();
		setTitle(title);
	}
}
