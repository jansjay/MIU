package librarysystem.windows;

import java.awt.EventQueue;

import librarysystem.controls.G8JFrame;

public class LibrarySystem extends G8JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarySystem frame = new LibrarySystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibrarySystem() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(G8JFrame.EXIT_ON_CLOSE);
		registerPanel(new G8LoginWindow());
	}

}
