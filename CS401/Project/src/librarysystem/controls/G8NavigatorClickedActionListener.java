package librarysystem.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

public class G8NavigatorClickedActionListener implements ActionListener{
	G8JPanel toBeAddedPanel;
	G8JFrame parentFrame;
	JSplitPane splitPane;
	public G8NavigatorClickedActionListener(G8JFrame frame, JSplitPane splitPane, G8JPanel tobeAddedPanel) {
		this.toBeAddedPanel = tobeAddedPanel;
		this.splitPane = splitPane;
		this.parentFrame = frame;
		this.toBeAddedPanel.setLayout(new BoxLayout(this.toBeAddedPanel, BoxLayout.Y_AXIS));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		splitPane.setRightComponent(toBeAddedPanel);
		parentFrame.setTitle("Library System - " + toBeAddedPanel.getTitle());
	};
}
