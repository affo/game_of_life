package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Position;
import resources.Resources;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1860979716621182121L;
//	private int horizzontalTraslation;
//	private int verticalTraslation;
	private List<Position> alives = new ArrayList<Position>();
	
	public GamePanel() {
		super();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		initGrid();
	}
	
//	private Position getRelative(Position coord) {
//		int newX = coord.getRow() + horizzontalTraslation;
//		int newY = -coord.getColumn() + verticalTraslation;
//		return new Position(newX, newY);
//	}
	
	private void initGrid(){
		for(int i = 0; i<50; i++){
			for(int j=0; j<25; j++){
				addCell(new Position(i, j));
			}
		}
	}
	
	private void addCell(Position pos) {
		JEntity button = new JEntity(new ImageIcon(Resources.class.getResource("img/dead.png")));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.addActionListener(new GridListener(pos, button));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = pos.getRow();
		c.gridy = pos.getColumn();
		add(button, c);
	}

	public void rise(Position pos, JButton caller) {
		JEntity button = new JEntity(new ImageIcon(Resources.class.getResource("img/alive.png")));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.rise();
		alives.add(pos);
		button.addActionListener(new GridListener(pos, button));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = pos.getRow();
		c.gridy = pos.getColumn();
		remove(caller);
		add(button, c);
		revalidate();  
	}

	public void kill(Position pos, JEntity caller) {
		JEntity button = new JEntity(new ImageIcon(Resources.class.getResource("img/dead.png")));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.die();
		alives.remove(pos);
		button.addActionListener(new GridListener(pos, button));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = pos.getRow();
		c.gridy = pos.getColumn();
		remove(caller);
		add(button, c);
		revalidate(); 
	}
}
