package librarysystem.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class G8PopulateEvent implements ActionListener{
	G8Populatable populatable;
	public G8PopulateEvent(G8Populatable populatable) {
		this.populatable = populatable;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		populatable.populate();
		
	}

}
