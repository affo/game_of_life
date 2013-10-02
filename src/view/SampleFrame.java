package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SampleFrame extends JFrame {

	private static final long serialVersionUID = -1369483078521464006L;
	SamplePanel panel;
	
	public SampleFrame(String title, int height, int width) {
		super(title);
		this.setPreferredSize(new Dimension(width, height));
		this.setResizable(false);
		panel = new SamplePanel();
		this.add(panel, BorderLayout.CENTER);
		this.dispose();
	}
	
	private class SamplePanel extends JPanel{
		private static final long serialVersionUID = 7618654204985231137L;
		private List<JButton> buttons;

		public SamplePanel() {
			super();
			buttons = new ArrayList<JButton>();
			buttons.add(new JButton("glider"));
			buttons.add(new JButton("rispo"));
			buttons.add(new JButton("cannon"));
			
			for(JButton button : buttons){
				this.add(button);
				button.setEnabled(true);
			}
		}
	}

}
