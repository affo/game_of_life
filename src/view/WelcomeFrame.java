package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class WelcomeFrame extends JFrame {

	private static final long serialVersionUID = -1454375441166315775L;

	public WelcomeFrame(String title, int height, int width) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(width, height));
		this.setResizable(false);
	}
	
}