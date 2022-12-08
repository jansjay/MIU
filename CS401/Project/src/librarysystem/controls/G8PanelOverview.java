package librarysystem.controls;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Insets;

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
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newClicked();
			}
		});
		panelButtons.add(btnNew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editClicked();
			}
		});
		btnEdit.setEnabled(false);
		panelButtons.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClicked();
			}
		});
		btnDelete.setEnabled(false);
		panelButtons.add(btnDelete);
		GridBagLayout gbl_panelBottom = new GridBagLayout();
		gbl_panelBottom.columnWidths = new int[]{450, 0};
		gbl_panelBottom.rowHeights = new int[]{132, 365, 0};
		gbl_panelBottom.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelBottom.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelBottom.setLayout(gbl_panelBottom);
		
		JPanel panelOverviewTable = new JPanel();
		GridBagConstraints gbc_panelOverviewTable = new GridBagConstraints();
		gbc_panelOverviewTable.fill = GridBagConstraints.BOTH;
		gbc_panelOverviewTable.insets = new Insets(0, 0, 5, 0);
		gbc_panelOverviewTable.gridx = 0;
		gbc_panelOverviewTable.gridy = 0;
		panelBottom.add(panelOverviewTable, gbc_panelOverviewTable);
		panelOverviewTable.setLayout(new GridLayout(1, 1, 0, 0));
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane = new JScrollPane(table);
		
		panelOverviewTable.add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

		    public void valueChanged(ListSelectionEvent lse) {
	            btnEdit.setEnabled(table.getSelectedRowCount() >= 0);
	            btnDelete.setEnabled(table.getSelectedRowCount() >= 0);
	            selectionChanged();	        		        
		    }
		});	
		panelDetail = new JPanel();
		GridBagConstraints gbc_panelDetail = new GridBagConstraints();
		gbc_panelDetail.fill = GridBagConstraints.BOTH;
		gbc_panelDetail.gridx = 0;
		gbc_panelDetail.gridy = 1;
		panelBottom.add(panelDetail, gbc_panelDetail);
		panelDetail.setLayout(new GridLayout(1, 1, 0, 0));

	}
	protected void fillWindow() {		
	}
	
	protected void selectionChanged() {		
	}
	
	protected void editClicked() {		
	}
	
	protected void newClicked() {		
	}
	
	protected void deleteClicked() {		
	}
	
	@Override
	public void populate() {
	}
}
