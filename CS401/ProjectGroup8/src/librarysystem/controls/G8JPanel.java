package librarysystem.controls;

import javax.swing.JPanel;

public class G8JPanel extends JPanel {

	private String title = "Default Title";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8JPanel() {

	}
	
	public G8JPanel(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
