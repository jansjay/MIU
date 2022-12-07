package librarysystem.windows;

import librarysystem.controls.G8JPanel;
import librarysystem.controls.G8Navigatable;

import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class G8LoginWindow extends G8JPanel  implements G8Navigatable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8LoginWindow() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);

	}
	public G8LoginWindow(String title) {
		setTitle(title);
	}
	
	@Override
	public boolean isNavigatorItemVisible() {
		// Check already logged in
		return true;
	}
	

}
