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
	private JTextField textFieldIsbn;

	/**
	 * Create the panel.
	 */
	public G8LibraryBookDetailsWindow() {
		panelDetails.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(76, 38, 69, 20);
		panelDetails.add(lblIsbn);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(184, 35, 251, 26);
		panelDetails.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
	}

	public void fillWindow(Book book, CrudMode mode) {
		super.fillWindow(mode);
		if(mode == CrudMode.Read || mode == CrudMode.Delete || mode == CrudMode.Update){
			this.textFieldIsbn.setText(book.getIsbn());
		}
		else{
			this.textFieldIsbn.setText("");
		}
	}
	
	public G8LibraryBookDetailsWindow(String title) {
		this();
		setTitle(title);
	}
}
