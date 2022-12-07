package librarysystem.controls;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;



public class G8JPanelTrailing extends G8JPanel {
	JTextArea textArea;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8JPanelTrailing() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(1, 1));		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLUE);
		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll, BorderLayout.CENTER);
		this.setMaximumSize(new Dimension(6000, 300));
	}
	
	public void setMessage(String message, Color color) {
		textArea.setText(message);
		textArea.setForeground(color);
	}

}
