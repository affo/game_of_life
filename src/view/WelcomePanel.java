package view;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;

public class WelcomePanel extends JPanel {

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