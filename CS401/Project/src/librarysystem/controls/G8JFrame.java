package librarysystem.controls;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class G8JFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private G8JPanel contentPane;
	private G8JPanelCenter JPanelCenter;
	private G8JPanelTrailing JPanelTrailing;

	/**
	 * Create the frame.
	 */
	public G8JFrame() {
		setTitle("Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new G8JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		G8JPanelLeading JPanelLeading = new G8JPanelLeading();
		FlowLayout fl_JPanelLeading = (FlowLayout) JPanelLeading.getLayout();
		fl_JPanelLeading.setAlignment(FlowLayout.LEADING);
		contentPane.add(JPanelLeading);
		
		JPanelCenter = new G8JPanelCenter();
		contentPane.add(JPanelCenter);		
		
		JPanelTrailing = new G8JPanelTrailing();
		contentPane.add(JPanelTrailing);
		
	}
	
	public void registerPanel(G8JPanel panel) {
		JPanelCenter.registerPanel(panel);
	}
	
	public void authorizationChanged() {
		JPanelCenter.drawNavigator();
		setStatusMessage("Login succeeded!!!", Color.BLUE);
	}
	
	public void setStatusMessage(String message, Color color) {
		JPanelTrailing.setMessage(message, color);
	}

}
