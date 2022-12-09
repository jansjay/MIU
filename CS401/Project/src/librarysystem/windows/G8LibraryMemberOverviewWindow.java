package librarysystem.windows;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.Context;
import business.ControllerInterface;
import business.DataModelMapper;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.controls.G8JFrame;
import librarysystem.controls.G8JPanel;
import librarysystem.controls.G8Navigatable;
import librarysystem.controls.G8PanelDetails;
import librarysystem.controls.G8PanelOverview;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class G8LibraryMemberOverviewWindow extends G8PanelOverview implements G8Navigatable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//G8PanelOverview
	ControllerInterface controller;
	//private JTable tblMember;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JTextField txtMemberId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	private JTextField txtTelephone;
	JButton btnAdd;
	JButton btnUpdate;
	JButton btnDelete;
	JPanel panel;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					G8LibraryMemberOverviewWindow window = new G8LibraryMemberOverviewWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public G8LibraryMemberOverviewWindow(String title) {
		this();
		this.setTitle(title);
		super.lblSearch.setText("Search member (id or name)");
	}
	public G8LibraryMemberOverviewWindow() {
		//super("Member Overview Window");
		initialize();
		controller = new SystemController();
		DataModelMapper.addAllLibraryMember(controller.getLibraryMembers(), model);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public boolean isNavigatorItemVisible() {
		return Context.isAuth(Auth.LIBRARIAN);
	}
	private void initialize() {
		//setBounds(100, 100, 1205, 678);
		//getContentPane().setForeground(new Color(255, 255, 255));
		//getContentPane().setLayout(null);
		super.btnLoadData.setText("All Members");
		panelDetail.setLayout(null);
		addTextFields();
		addButtons();
		addJTable();
		addBtnEvents();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void addBtnEvents() {
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validateFields()) {
					JOptionPane.showMessageDialog(null, "Please insert the values");
					return;
				}
				String[] row = getTextFieldValues();
				model.addRow(row);
				controller.saveMember(DataModelMapper.mapLibraryMember(row));
				emptyTextFields();
				JOptionPane.showMessageDialog(null, "Added Successfully");
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!validateFields()) {
					JOptionPane.showMessageDialog(null, "Please insert the values");
					return;
				}
				String[] values = getTextFieldValues();
				int rowNumber = table.getSelectedRow();
				if(rowNumber<=0) {
					JOptionPane.showMessageDialog(null, "Please select a row");
					return;
				}
				DataModelMapper.UpdateModelRow(model,values,rowNumber);
				controller.saveMember(DataModelMapper.mapLibraryMember(values));
				emptyTextFields();
				JOptionPane.showMessageDialog(null, "Updated Successfully");
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowNumber = table.getSelectedRow();
				if(rowNumber<=0) {
					JOptionPane.showMessageDialog(null, "Please select a row");
					return;
				}
				int confirmation = JOptionPane.showConfirmDialog(null, "Confirm to delete?");
				if(confirmation!=0)return;
				
				controller.removeMember(model.getValueAt(rowNumber,0).toString());
				model.removeRow(rowNumber);
				emptyTextFields();
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setSelectedRowIntoTextFields();
				txtMemberId.setEditable(false);
			}
		});
	}
	
	private void addJTable() {
		model = new DefaultTableModel();
		String[] columns = getTableColumnNames();
		model.setColumnIdentifiers(columns);
		
		//tblMember = new JTable();
		table.setModel(model);
		table.setBounds(1137, 283, -1077, 280);
		
		//scrollPane = new JScrollPane();
		//scrollPane.setBounds(10, 274, 1164, 356);
		//scrollPane.setViewportView(table);
		//add(scrollPane);
	}
	private void addTextFields() {
		txtMemberId = new JTextField();
		txtMemberId.setToolTipText("Member ID");
		txtMemberId.setBounds(251, 11, 628, 31);
		txtMemberId.setColumns(10);
		panelDetail.add(txtMemberId);
		
		txtFirstName = new JTextField();
		txtFirstName.setToolTipText("First Name");
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(251, 53, 628, 31);
		panelDetail.add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setToolTipText("Last Name");
		txtLastName.setColumns(10);
		txtLastName.setBounds(251, 95, 628, 31);
		panelDetail.add(txtLastName);
		
		txtStreet = new JTextField();
		txtStreet.setToolTipText("Street");
		txtStreet.setColumns(10);
		txtStreet.setBounds(251, 137, 226, 31);
		panelDetail.add(txtStreet);		
		txtCity = new JTextField();
		txtCity.setToolTipText("City");
		txtCity.setColumns(10);
		txtCity.setBounds(251, 172, 226, 46);
		panelDetail.add(txtCity);
		
		txtState = new JTextField();
		txtState.setToolTipText("State");
		txtState.setColumns(10);
		txtState.setBounds(251, 229, 66, 31);
		panelDetail.add(txtState);
		
		txtZip = new JTextField();
		txtZip.setToolTipText("Zip");
		txtZip.setColumns(10);
		txtZip.setBounds(395, 229, 78, 31);
		panelDetail.add(txtZip);
		
		txtTelephone = new JTextField();
		txtTelephone.setToolTipText("Telephone");
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(653, 137, 226, 31);
		panelDetail.add(txtTelephone);
	}

	private void addButtons() {
		
		btnAdd = new JButton("Add Member");
		btnAdd.setBounds(547, 198, 124, 31);
		panelDetail.add(btnAdd);
		
		btnUpdate = new JButton("Update Member");
		btnUpdate.setBounds(681, 198, 124, 31);
		panelDetail.add(btnUpdate);
		
		btnDelete = new JButton("Delete Member");
		btnDelete.setBounds(815, 198, 109, 31);
		panelDetail.add(btnDelete);
		
		JLabel lblMemberId = new JLabel("Member ID");
		lblMemberId.setBounds(31, 15, 115, 23);
		panelDetail.add(lblMemberId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(30, 61, 100, 14);
		panelDetail.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(31, 103, 164, 14);
		panelDetail.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(31, 145, 90, 14);
		panelDetail.add(lblAddress);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(146, 188, 49, 14);
		panelDetail.add(lblCity);
		
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setBounds(146, 237, 49, 14);
		panelDetail.add(lblNewLabel);
		
		JLabel lblZip = new JLabel("ZIP");
		lblZip.setBounds(367, 237, 18, 14);
		panelDetail.add(lblZip);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(146, 145, 49, 14);
		panelDetail.add(lblStreet);
		
		JLabel lblTelephone = new JLabel("TEL");
		lblTelephone.setBounds(609, 145, 34, 14);
		panelDetail.add(lblTelephone);
	}
	
	private String[] getTableColumnNames() {
		return new String[]{
			"Member ID", 
			"First Name", 
			"Last Name",
			"Street", 
			"City", 
			"State", 
			"Zip", 
			"Telephone Number"
		};
	}
	
	private void emptyTextFields(){
		txtMemberId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtStreet.setText("");
		txtCity.setText("");
		txtState.setText("");
		txtZip.setText("");
		txtTelephone.setText("");
	}
	
	private String[] getTextFieldValues(){
		return new String[]{
			txtMemberId.getText(),
			txtFirstName.getText(),
			txtLastName.getText(),
			txtStreet.getText(),
			txtCity.getText(),
			txtState.getText(),
			txtZip.getText(),
			txtTelephone.getText()
			};
	}
	
	private void setSelectedRowIntoTextFields() {
		
		int selectedRow = table.getSelectedRow();
		txtMemberId.setText(model.getValueAt(selectedRow, 0).toString());
		txtFirstName.setText(model.getValueAt(selectedRow, 1).toString());
		txtLastName.setText(model.getValueAt(selectedRow, 2).toString());
		txtStreet.setText(model.getValueAt(selectedRow, 3).toString());
		txtCity.setText(model.getValueAt(selectedRow, 4).toString());
		txtState.setText(model.getValueAt(selectedRow, 5).toString());
		txtZip.setText(model.getValueAt(selectedRow, 6).toString());
		txtTelephone.setText(model.getValueAt(selectedRow, 7).toString());
	}
	
	private boolean validateFields() {
		if(txtMemberId.getText().isEmpty()) return false;
		if(txtFirstName.getText().isEmpty()) return false;
		if(txtLastName.getText().isEmpty()) return false;
		if(txtStreet.getText().isEmpty()) return false;
		if(txtCity.getText().isEmpty()) return false;
		if(txtState.getText().isEmpty()) return false;
		if(txtZip.getText().isEmpty()) return false;
		if(txtTelephone.getText().isEmpty()) return false;
		return true;
	}
}
