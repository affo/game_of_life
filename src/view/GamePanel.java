package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.Resources;

import model.Position;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;

	public GamePanel() {
		super();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(50, 50, 50, 50)));
		initGrid();
	}
	
	private void initGrid(){
		for(int i = 0; i<50; i++){
			for(int j=0; j<25; j++){
				addCell(new Position(i, j));
			}
		}
	}
	
	private void addCell(Position pos) {
		JLabel label;
		URL path = Resources.class.getResource("img/died.png");
		label = new JLabel(new ImageIcon(path));
		label.setBorder(BorderFactory.createEmptyBorder());
		label.setEnabled(false);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = pos.getRow();
		c.gridy = pos.getColumn();
		add(label, c);
	}
}
