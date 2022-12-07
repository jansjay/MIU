package librarysystem.controls;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;


import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

public class G8PanelOverview extends G8JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public G8PanelOverview() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		add(panel);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane);
		
		JButton btnNewButton = new JButton("New button");
		splitPane.setLeftComponent(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();		
		panel_1.add(table, BorderLayout.CENTER);

	}
	
	protected JTable getOverviewTable() {
		return this.table;
	}

	protected void fillWindow() {		
	}
}
