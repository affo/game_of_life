package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Entity;
import model.Position;
import model.WorldInterface;

public class GameGrid extends JPanel {

	private static final long serialVersionUID = -6359874700490075658L;
	private static final int rows = 25; /* use odd numbers */
	private static final int columns = 50; /* use odd numbers */
	private int horizzontalTraslation = rows / 2 + 1;
	private int verticalTraslation = columns / 2 + 1;
	private List<Position> alives = new ArrayList<Position>();
	private Set<JEntity> entities = new HashSet<JEntity>();

	public GameGrid() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(10, 15, 10, 15)));
		initGrid();
	}

	public List<Position> getAlives() {
		return alives;
	}

	private Position getRelative(Position coord) {
		int newX = coord.getRow() + horizzontalTraslation;
		int newY = -coord.getColumn() + verticalTraslation;
		return new Position(newX, newY);
	}

	private void initGrid() {
		int i,j;
		for (i = 0; i < columns; i++) {
			for (j = 0; j < rows; j++) {
				addDead(new Position(i, j));
			}
		}
	}

	private void addDead(Position pos) {
		JEntity dead = new JEntity(JEntity.DEAD, pos);
		addCell(dead);
	}

	private void addCell(JEntity entityContainer) {
		entities.add(entityContainer);
		entityContainer.setBorder(BorderFactory.createEmptyBorder());
		entityContainer.addActionListener(new GridListener());
		GridBagConstraints c = new GridBagConstraints();
		Position relatives = getRelative(entityContainer.getPosition());
		c.fill = GridBagConstraints.BOTH;
		c.gridx = relatives.getRow();
		c.gridy = relatives.getColumn();
		add(entityContainer, c);
	}

	public void addAlive(JEntity entity) {
		alives.add(entity.getPosition());
	}
	
	public void removeAlive(JEntity entity) {
		alives.remove(entity.getPosition());
	}
	
	public void update(WorldInterface world) {
		Set<Entity> editedEntities = world.getAvailableEntities();
		Iterator<Entity> edited = editedEntities.iterator();
		Iterator<JEntity> toEdit = entities.iterator();
		while(edited.hasNext()){
			Entity entity = edited.next();
			while(toEdit.hasNext()){
				JEntity entityContainer = toEdit.next();
				if(entityContainer.getPosition().equals(entity.getPosition())){
					if(entity.isAlive() && !entityContainer.isAlive())
						entityContainer.rise();
					else if(!entity.isAlive() && entityContainer.isAlive())
						entityContainer.die();
					break;
				}
			}
		}
		revalidate();
		
	}
	
}
