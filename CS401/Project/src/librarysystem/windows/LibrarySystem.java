package librarysystem.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

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
					centerFrameOnDesktop(frame);
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
		setBounds(100, 100, 800, 650);
		setMinimumSize(new Dimension(1400,800));
		setDefaultCloseOperation(G8JFrame.EXIT_ON_CLOSE);
		registerPanel(new G8LoginWindow("Login"));
		G8LibraryBookDetailsWindow bookDetails = new G8LibraryBookDetailsWindow("Books Detail");
		registerPanel(bookDetails);
		registerPanel(new G8LibraryBookOverviewWindow("Books overview"));
		registerPanel(new G8LibraryMemberOverviewWindow("Member Overview"));
		registerPanel(new G8CheckoutBook("Checkout"));
		registerPanel(new G8CheckoutSearchWindow("Checkout Search"));
		setStatusMessage("Welcome !!!", Color.DARK_GRAY);
	}
	
	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		f.setBounds(0, 0, width, height);
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}

}
