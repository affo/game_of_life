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
	private int rows;
	private int columns;
	private int horizzontalTraslation;
	private int verticalTraslation;
	private List<Position> initialConfiguration;
	private HashMap<Position, JEntity> entities;
	private EpochRunner runner;
	private boolean firstRun;

	public GameGrid() {
		rows = 25; /* use odd numbers */
		columns = 50; /* use odd numbers */
		horizzontalTraslation = rows / 2 + 1;
		verticalTraslation = columns / 2 + 1;
		initialConfiguration = new ArrayList<Position>();
		entities = new HashMap<Position, JEntity>();
		runner = null;
		firstRun = true;
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		initGrid();
	}

	public boolean isFirstRun() {
		return firstRun;
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
		System.out.println("INITIAL CONFIGURATION:");
		Iterator<Position> itr = initialConfiguration.iterator();
		while (itr.hasNext()) {
			Position pos = itr.next();
			String string = "container (" + pos.getRow() + ", "
					+ pos.getColumn() + ") is alive";
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
	
	public void removeGridListeners(){
		System.out.println();
	}

	public void addToInitialConfiguration(JEntity entity) {
		initialConfiguration.add(entity.getPosition());
	}

	public void removeFromInitialConfiguration(JEntity entity) {
		initialConfiguration.remove(entity.getPosition());
	}
	
	public void setInitialConfiguration() {
		runner = new EpochRunner(getInitialConfiguration());
		firstRun = false;
		showInitialConfiguration();
	}

	public void runEpoch() {
		System.out.println();
		WorldInterface world = runner.runEpoch();
		Set<Entity> aliveEntities = world.getAliveEntities();
		Iterator<Entity> aliveIterator = aliveEntities.iterator();
		
		System.out.println("\nCLEAN BOARD");
		Iterator<Position> keyIterator = entities.keySet().iterator();
		while(keyIterator.hasNext()){
			JEntity entityContainer = entities.get(keyIterator.next());
			if(entityContainer.isAlive()){
				entityContainer.die();
			}
		}	
		
		System.out.println("\nRUN EPOCH:");
		while (aliveIterator.hasNext()) {
			Entity entity = aliveIterator.next();
			JEntity entityContainer = entities.get(entity.getPosition());
			if(entityContainer != null){ /* happens when outside visible grid */
				if (!entityContainer.isAlive()) {
					System.out.println("rise container ("
							+ entity.getPosition().getRow() + ","
							+ entity.getPosition().getColumn() + ") was: "+ entityContainer.isAlive());
					entityContainer.rise();
				} else {
					System.out.println("kill container ("
							+ entity.getPosition().getRow() + ","
							+ entity.getPosition().getColumn() + ") was: "+ entityContainer.isAlive());
					entityContainer.die();
				}
			}
		}
		revalidate();
	}

}
