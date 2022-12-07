package librarysystem.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class G8AuthenticateActionListener implements ActionListener  {

	private G8JPanel panel;
	public G8AuthenticateActionListener(G8JPanel panel){
		this.panel = panel;
	}
	public G8JPanel getG8JPanel() {
		return panel;
	}
}
