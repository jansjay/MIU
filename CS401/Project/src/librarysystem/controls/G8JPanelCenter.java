package librarysystem.controls;
import javax.swing.JSplitPane;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.JButton;


public class G8JPanelCenter extends G8JPanel {

	List<G8JPanel> panels = new ArrayList<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelLeft;
	JSplitPane splitPane;

	/**
	 * Create the panel.
	 */
	public G8JPanelCenter() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		splitPane = new JSplitPane();
		panel.add(splitPane);
		
		panelLeft = new JPanel();
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Center");
		panel_2.add(lblNewLabel_1);
		
	}
	
	void registerPanel(G8JPanel panel) {
		if(!panels.contains(panel))
			panels.add(panel);
		drawNavigator();
	}
	
	public void authorizationChanged() {
		drawNavigator();
	}
	
	void drawNavigator() {
		panelLeft.removeAll();
		for(G8JPanel panel : panels)
		{
			if (!(panel instanceof G8Navigatable) || 
				!((G8Navigatable)panel).isNavigatorItemVisible())
				continue;
			
			JButton btn = new JButton(panel.getTitle());
			panelLeft.add(btn);
			splitPane.setRightComponent(panel);
			btn.addActionListener(new G8NavigatorClickedActionListener(getG8JFrame(), splitPane, panel) );
		}		
	}
}
