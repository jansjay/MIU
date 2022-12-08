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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class G8LibraryMemberOverviewWindow extends G8JPanel implements G8Navigatable{
	//G8PanelOverview
	ControllerInterface controller;
	private JTable tblMember;
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
	}
	public G8LibraryMemberOverviewWindow() {
		super("Member Overview Window");
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
		setBounds(100, 100, 1205, 678);
		//getContentPane().setForeground(new Color(255, 255, 255));
		//getContentPane().setLayout(null);
		
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
				int rowNumber = tblMember.getSelectedRow();
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
				int rowNumber = tblMember.getSelectedRow();
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
		
		tblMember.addMouseListener(new MouseAdapter() {
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
		
		tblMember = new JTable();
		tblMember.setModel(model);
		tblMember.setBounds(1137, 283, -1077, 280);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 274, 1164, 356);
		scrollPane.setViewportView(tblMember);
		add(scrollPane);
	}
	private void addTextFields() {
		txtMemberId = new JTextField();
		txtMemberId.setToolTipText("Member ID");
		txtMemberId.setBounds(10, 11, 226, 63);
		txtMemberId.setColumns(10);
		add(txtMemberId);
		
		txtFirstName = new JTextField();
		txtFirstName.setToolTipText("First Name");
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(267, 11, 226, 63);
		add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setToolTipText("Last Name");
		txtLastName.setColumns(10);
		txtLastName.setBounds(531, 11, 226, 63);
		add(txtLastName);
		
		txtStreet = new JTextField();
		txtStreet.setToolTipText("Street");
		txtStreet.setColumns(10);
		txtStreet.setBounds(10, 85, 226, 63);
		add(txtStreet);		
		txtCity = new JTextField();
		txtCity.setToolTipText("City");
		txtCity.setColumns(10);
		txtCity.setBounds(267, 85, 226, 63);
		add(txtCity);
		
		txtState = new JTextField();
		txtState.setToolTipText("State");
		txtState.setColumns(10);
		txtState.setBounds(531, 85, 226, 63);
		add(txtState);
		
		txtZip = new JTextField();
		txtZip.setToolTipText("Zip");
		txtZip.setColumns(10);
		txtZip.setBounds(10, 159, 226, 63);
		add(txtZip);
		
		txtTelephone = new JTextField();
		txtTelephone.setToolTipText("Telephone");
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(267, 159, 226, 63);
		add(txtTelephone);
	}

	private void addButtons() {
		
		btnAdd = new JButton("Add Member");
		btnAdd.setBounds(990, 19, 139, 46);
		add(btnAdd);
		
		btnUpdate = new JButton("Update Member");
		btnUpdate.setBounds(990, 104, 139, 46);
		add(btnUpdate);
		
		btnDelete = new JButton("Delete Member");
		btnDelete.setBounds(990, 186, 139, 46);
		add(btnDelete);
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
		
		int selectedRow = tblMember.getSelectedRow();
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
