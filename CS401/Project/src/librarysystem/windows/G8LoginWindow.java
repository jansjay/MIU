package librarysystem.windows;

import librarysystem.controls.G8AuthenticateActionListener;
import librarysystem.controls.G8JPanel;
import librarysystem.controls.G8Navigatable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JTextField;

import business.Context;
import business.LoginException;
import business.SystemController;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class G8LoginWindow extends G8JPanel  implements G8Navigatable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
	public G8LoginWindow() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Username");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("");
		add(label);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new G8AuthenticateActionListener(this) {
			public void actionPerformed(ActionEvent e) {
				try {
					SystemController.getInstance().login(textField.getText(), textField_1.getText());					
					this.getG8JPanel().getG8JFrame().authorizationChanged();
				} catch (LoginException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		add(label_1);
		
		JLabel label_2 = new JLabel("");
		add(label_2);
	}
	
	/**
	 * Create the panel.
	 */
	public G8LoginWindow(String title) {
		this();
		setTitle(title);		
	}
	
	@Override
	public boolean isNavigatorItemVisible() {
		return !Context.isLoggedIn();
	}
}
