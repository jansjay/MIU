package librarysystem.controls;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class G8JPanelLeading extends G8JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8JPanelLeading() {
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("SomeAction");		
		mnNewMenu.add(mntmNewMenuItem);

	}
	

}
