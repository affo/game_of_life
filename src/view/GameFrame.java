package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 2729314215802559201L;

    public GameFrame(String title, int height, int width) {
	super(title);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setPreferredSize(new Dimension(width, height));
	this.setResizable(false);
    }

}
