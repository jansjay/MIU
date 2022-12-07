package librarysystem.controls;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import business.LoginException;
import business.SystemController;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;


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
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmFileQuitMenuItem = new JMenuItem("Quit");		
		mntmFileQuitMenuItem.addActionListener(new G8ActionListener(this) {
			public void actionPerformed(ActionEvent e) {
				try {
					this.getG8JPanel().getG8JFrame().dispatchEvent(new WindowEvent(this.getG8JPanel().getG8JFrame(), WindowEvent.WINDOW_CLOSING));					
				} catch (Exception e1) {
					this.getG8JPanel().getG8JFrame().setStatusMessage(e1.getMessage(), Color.RED);
				}
			}
		});
		mnFileMenu.add(mntmFileQuitMenuItem);
		
		JMenu mnUserMenu = new JMenu("User");
		menuBar.add(mnUserMenu);
		
		JMenuItem mntmUserLogoggMenuItem = new JMenuItem("Logoff");
		mntmUserLogoggMenuItem.addActionListener(new G8ActionListener(this) {
			public void actionPerformed(ActionEvent e) {
				try {
					this.getG8JPanel().getG8JFrame().dispatchEvent(new WindowEvent(this.getG8JPanel().getG8JFrame(), WindowEvent.WINDOW_CLOSING));					
				} catch (Exception e1) {
					this.getG8JPanel().getG8JFrame().setStatusMessage(e1.getMessage(), Color.RED);
				}
			}
		});
		mnUserMenu.add(mntmUserLogoggMenuItem);

	}
	

}
