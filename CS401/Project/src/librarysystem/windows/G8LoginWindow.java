package librarysystem.windows;

import librarysystem.controls.G8ActionListener;
import librarysystem.controls.G8JPanel;
import librarysystem.controls.G8Navigatable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
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
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class G8LoginWindow extends G8JPanel  implements G8Navigatable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	public G8LoginWindow() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		G8JPanel panel = new G8JPanel();
		add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 49, 188, 50);
		panel.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(199, 49, 450, 50);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(26, 101, 160, 50);
		panel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(199, 101, 450, 50);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 200, 450, 50);
		panel.add(label);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(199, 156, 450, 50);
		btnLogin.addActionListener(new G8ActionListener(this) {
			public void actionPerformed(ActionEvent e) {
				try {
					SystemController.getInstance().login(txtUsername.getText(), txtPassword.getText());					
					this.getG8JPanel().getG8JFrame().authorizationChanged();					
				} catch (LoginException e1) {
					this.getG8JPanel().getG8JFrame().setStatusMessage(e1.getMessage(), Color.RED);
				}
			}
		});
		panel.add(btnLogin);
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
