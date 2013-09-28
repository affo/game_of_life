package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
	private static final int rows = 5; /* use odd numbers */
	private static final int columns = 5; /* use odd numbers */
	private int horizzontalTraslation = rows / 2 + 1;
	private int verticalTraslation = columns / 2 + 1;
	private List<Position> initialConfiguration = new ArrayList<Position>();
	private HashMap<Position, JEntity> entities = new HashMap<Position, JEntity>();

	public GameGrid() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		initGrid();
	}

	public List<Position> getInitialConfiguration() {
		return initialConfiguration;
	}

	private Position getRelative(Position coord) {
		int newX = coord.getRow() + horizzontalTraslation;
		int newY = -coord.getColumn() + verticalTraslation;
		return new Position(newX, newY);
	}

	private void initGrid() {
		int i, j;
		for (i = 0; i < columns; i++) {
			for (j = 0; j < rows; j++) {
				addCell(new Position(i, j));
			}
		}
		showEntities();
	}

	private void showEntities() {
		System.out.println("ENTITIES CONTAINERS:");
		Collection<JEntity> e = entities.values();
		Iterator<JEntity> itr = e.iterator();
		while (itr.hasNext()) {
			JEntity entitycontainer = itr.next();
			String string = "container for ("
					+ entitycontainer.getPosition().getRow() + ","
					+ entitycontainer.getPosition().getColumn()
					+ ") created in state: " + entitycontainer.isAlive();
			System.out.println(string);
		}
	}

	public void showInitialConfiguration() {
		System.out.println("\nINITIAL CONFIGURATION:");
		Iterator<Position> itr = initialConfiguration.iterator();
		while (itr.hasNext()) {
			Position pos = itr.next();
			String string = "container for (" + pos.getRow() + ","
					+ pos.getColumn() + ") setted to state: true";
			System.out.println(string);
		}
	}

	private void addCell(Position pos) {
		JEntity entityContainer = new JEntity(JEntity.DEAD, pos);
		entities.put(entityContainer.getPosition(), entityContainer);
		entityContainer.setBorder(BorderFactory.createEmptyBorder());
		entityContainer.addActionListener(new GridListener());
		GridBagConstraints c = new GridBagConstraints();
		Position relatives = getRelative(entityContainer.getPosition());
		c.fill = GridBagConstraints.BOTH;
		c.gridx = relatives.getRow();
		c.gridy = relatives.getColumn();
		add(entityContainer, c);
	}

	public void addToInitialConfiguration(JEntity entity) {
		initialConfiguration.add(entity.getPosition());
	}

	public void removeFromInitialConfiguration(JEntity entity) {
		initialConfiguration.remove(entity.getPosition());
	}

	public void update(WorldInterface world) {
		System.out.println("\nUPDATE EPOCH:");
		Set<Entity> editedEntities = world.getAvailableEntities();
		Iterator<Entity> edited = editedEntities.iterator();
		while (edited.hasNext()) {
			Entity entity = edited.next();
			System.out.println("entity to modify is ("
					+ entity.getPosition().getRow() + ","
					+ entity.getPosition().getColumn() + ") has to become: "
					+ entity.isAlive());
			JEntity entityContainer = entities.get(entity.getPosition());
			if (entityContainer != null) {
				System.out.println("container for ("
						+ entity.getPosition().getRow() + ","
						+ entity.getPosition().getColumn() + ") found!");
				if (entity.isAlive() && !entityContainer.isAlive()) {
					System.out.println("rise container ("
							+ entity.getPosition().getRow() + ","
							+ entity.getPosition().getColumn() + ")");
					entityContainer.rise();
				} else if (!entity.isAlive() && entityContainer.isAlive()) {
					System.out.println("kill container ("
							+ entity.getPosition().getRow() + ","
							+ entity.getPosition().getColumn() + ")");
					entityContainer.die();
				}
			} else {
				System.out.println("container for ("
						+ entity.getPosition().getRow() + ","
						+ entity.getPosition().getColumn() + ") NOT found!");
			}
		}
		revalidate();
	}

}
