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
import javax.swing.JTextField;

public class G8PanelOverview extends G8JPanel implements G8Populatable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTable table;
	protected JPanel panelDetail;
	protected JButton btnAddBookCopy;
	protected JButton btnSearch;
	protected JTextField textFieldSearch;
	protected JLabel lblSearch;
	protected JButton btnLoadData;

	/**
	 * Create the panel.
	 */
	public G8PanelOverview() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setMaximumSize(new Dimension(6000, 100));
		add(panelTop, BorderLayout.NORTH);
		
		JPanel panelBottom = new JPanel();
		add(panelBottom, BorderLayout.CENTER);
		
		panelTop.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		

		JPanel panelSearch = new JPanel();
		splitPane.setRightComponent(panelSearch);
		
		lblSearch = new JLabel("Search books\n(isbn or title)");
		panelSearch.add(lblSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setToolTipText("Search by isbn or title");
		panelSearch.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchClicked();
			}
		});
		panelSearch.add(btnSearch);
		
	
		JPanel panelButtons = new JPanel();
		splitPane.setLeftComponent(panelButtons);
		
		splitPane.setMaximumSize(new Dimension(6000, 100));
		panelTop.add(splitPane, BorderLayout.NORTH);
		
		btnLoadData = new JButton("Load Data");
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
		
		btnAddBookCopy = new JButton("Add Book Copy");
		btnAddBookCopy.setEnabled(false);
		btnAddBookCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookCopyClicked();
			}
		});
		btnAddBookCopy.setVisible(false);
		
		panelButtons.add(btnAddBookCopy);
		GridBagLayout gbl_panelBottom = new GridBagLayout();
		gbl_panelBottom.columnWidths = new int[]{450, 0};
		gbl_panelBottom.rowHeights = new int[]{132, 365, 0};
		gbl_panelBottom.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelBottom.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelBottom.setLayout(gbl_panelBottom);
		
		JPanel panelOverviewTable = new JPanel();
		GridBagConstraints gbc_panelOverviewTable = new GridBagConstraints();
		gbc_panelOverviewTable.anchor = GridBagConstraints.NORTHWEST;
		gbc_panelOverviewTable.fill = GridBagConstraints.HORIZONTAL;
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
	            btnAddBookCopy.setEnabled(table.getSelectedRowCount() >= 0);
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
	
	protected void addBookCopyClicked() {
		//System.out.println("super - addBookCopyClicked");
	}
	
	protected void searchClicked() {
		
	}
}
