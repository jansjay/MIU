package librarysystem.controls;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;


import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class G8PanelOverview extends G8JPanel implements G8Populatable{
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));
		table = new JTable();	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(table, BorderLayout.CENTER);
		add(panel_1);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		
		JPanel panelSearch = new JPanel();
		splitPane.setRightComponent(panelSearch);
		
		JPanel panelButtons = new JPanel();
		splitPane.setLeftComponent(panelButtons);
		
		panel.add(splitPane);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new G8PopulateEvent(this));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		panelButtons.add(btnLoadData);
		
		JButton btnNew = new JButton("New");
		panelButtons.add(btnNew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		panelButtons.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		panelButtons.add(btnDelete);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent lse) {
		        if (!lse.getValueIsAdjusting()) {
		            btnEdit.setEnabled(lse.getFirstIndex() >= 0);
		            btnDelete.setEnabled(lse.getFirstIndex() >= 0);
		        }
		    }
		});	

	}
	
	protected JTable getOverviewTable() {
		return this.table;
	}

	protected void fillWindow() {		
	}

	@Override
	public void populate() {
	}
}
