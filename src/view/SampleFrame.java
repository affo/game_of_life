package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Glider;
import model.Position;
import model.Shape;

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
			SampleButton glider = new SampleButton(new Glider(new Position(0, 0)));
			buttons = new ArrayList<JButton>();
			buttons.add(glider);
			
			for(JButton button : buttons){
				this.add(button);
				button.setEnabled(true);
			}
		}
	}
	
	private class SampleButton extends JButton{
		private static final long serialVersionUID = -184931533941113663L;
		private Shape shape;
		
		public SampleButton(Shape shape) {
			this.shape = shape;
		}
		
		public Set<Position> getShape() {
			return shape.getShape();
		}
	}

}
