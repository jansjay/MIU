package librarysystem.controls;


import business.CrudMode;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class G8PanelDetails extends G8JPanel {

	protected JPanel panelDetails;
	protected JPanel panelButtons;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public G8PanelDetails() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panelDetails = new JPanel();
		add(panelDetails);
		
		JPanel panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		add(panelButtons);
		
		JButton btnOk = new JButton("OK");
		panelButtons.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);

	}

	protected void fillWindow(CrudMode mode) {
		
	}
}
