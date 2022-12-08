package librarysystem.controls;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class G8PanelOverview extends G8JPanel implements G8Populatable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTable table;
	protected JPanel panelDetail;

	/**
	 * Create the panel.
	 */
	public G8PanelOverview() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setMaximumSize(new Dimension(6000, 100));
		add(panelTop, BorderLayout.NORTH);
		
		JPanel panelBottom = new JPanel();
		add(panelBottom);
		panelBottom.setLayout(new GridLayout(2, 1, 0, 0));
		
		panelTop.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		
		JPanel panelSearch = new JPanel();
		splitPane.setRightComponent(panelSearch);
		
		JPanel panelButtons = new JPanel();
		splitPane.setLeftComponent(panelButtons);
		
		splitPane.setMaximumSize(new Dimension(6000, 100));
		panelTop.add(splitPane, BorderLayout.NORTH);
		
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
		
		JPanel panelOverviewTable = new JPanel();
		panelBottom.add(panelOverviewTable);
		panelOverviewTable.setLayout(new GridLayout(0, 1, 0, 0));
		table = new JTable();
		panelOverviewTable.add(table);
		panelDetail = new JPanel();
		panelBottom.add(panelDetail);
		panelDetail.setLayout(new GridLayout(1, 1, 0, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent lse) {
	            btnEdit.setEnabled(table.getSelectedRowCount() >= 0);
	            btnDelete.setEnabled(table.getSelectedRowCount() >= 0);
	            selectionChanged();	        		        
		    }
		});	

	}
	protected void fillWindow() {		
	}
	
	protected void selectionChanged() {
		
	}
	
	@Override
	public void populate() {
	}
}
