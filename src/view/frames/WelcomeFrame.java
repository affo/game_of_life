package view.frames;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;
import view.ViewManager;

public class WelcomeFrame extends JFrame {

    private static final long serialVersionUID = 9047490058030225053L;
    private WelcomePanel welcomePanel;

    public WelcomeFrame(String title) {
	super(title);
	this.setPreferredSize(new Dimension(610, 260));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setLayout(new BorderLayout());
	welcomePanel = new WelcomePanel();
	this.add(welcomePanel);
    }

    private class WelcomePanel extends JPanel {
	private static final long serialVersionUID = -3228318903039850883L;
	private JButton start;
	private JLabel logo;

	public WelcomePanel() {
	    super();
	    start = new JButton("start this amazing game");
	    start.addActionListener(new WelcomeListener());
	    start.setBorder(BorderFactory.createEmptyBorder());
	    start.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    logo = new JLabel(new ImageIcon(
		    Resources.class.getResource("img/logo.png")));
	    setLayout(new BorderLayout());
	    add(logo, BorderLayout.PAGE_START);
	    add(start, BorderLayout.CENTER);
	}
    }

    private class WelcomeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    ViewManager viewManager = ViewManager.getManager();
	    viewManager.game();
	    viewManager.disposeWelcome();
	}
    }
}
