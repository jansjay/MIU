package librarysystem.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

public class G8NavigatorClickedActionListener implements ActionListener{
	G8JPanel toBeAddedPanel;
	JSplitPane splitPane;
	public G8NavigatorClickedActionListener(JSplitPane splitPane, G8JPanel tobeAddedPanel) {
		this.toBeAddedPanel = tobeAddedPanel;
		this.splitPane = splitPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		splitPane.setRightComponent(toBeAddedPanel);
		
	};
}
