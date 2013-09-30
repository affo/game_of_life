package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.EpochRunner;

import model.Entity;
import model.Position;
import model.WorldInterface;

public class GameGrid extends JPanel {

	private static final long serialVersionUID = -6359874700490075658L;
	private static final int rows = 25; /* use odd numbers */
	private static final int columns = 50; /* use odd numbers */
	private int horizzontalTraslation = rows / 2 + 1;
	private int verticalTraslation = columns / 2 + 1;
	private List<Position> initialConfiguration = new ArrayList<Position>();
	private HashMap<Position, JEntity> entities = new HashMap<Position, JEntity>();
	private EpochRunner runner;

	public GameGrid() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		initGrid();
	}

	private List<Position> getInitialConfiguration() {
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
	
	public void setInitialConfiguration() {
		runner = new EpochRunner(getInitialConfiguration());
	}

	public void runEpoch() {
		System.out.println("\nRUN EPOCH:");
		WorldInterface world = runner.runEpoch();
		Set<Entity> editedEntities = world.getAliveEntities();
		Iterator<Entity> edited = editedEntities.iterator();
		while (edited.hasNext()) {
			Entity entity = edited.next();
			JEntity entityContainer = entities.get(entity.getPosition());
			if (!entityContainer.isAlive()) {
				System.out.println("rise container ("
						+ entity.getPosition().getRow() + ","
						+ entity.getPosition().getColumn() + ")");
				entityContainer.rise();
			} else {
				System.out.println("kill container ("
						+ entity.getPosition().getRow() + ","
						+ entity.getPosition().getColumn() + ")");
				entityContainer.die();
			}
		}
		revalidate();
	}

}
