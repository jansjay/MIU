package librarysystem.controls;

import java.awt.Container;

import javax.swing.BoxLayout;
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
	protected void setTitle(String title) {
		this.title = title;
	}
	
	public G8JFrame getG8JFrame() {
		Container container = getParent();
		while(!(container instanceof G8JFrame) && container != null) {
			container = container.getParent();
		}
		return (G8JFrame)container;
	}

}
