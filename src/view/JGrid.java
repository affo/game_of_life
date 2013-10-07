package view;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Entity;
import model.Position;
import model.WorldInterface;
import view.SampleFrame.SampleListener;
import view.listeners.GridListener;
import controller.EpochRunner;

public class JGrid extends JPanel {

    private static final long serialVersionUID = -6359874700490075658L;
    private int rows;
    private int columns;
    private int horizontalTraslation;
    private int verticalTraslation;
    private Set<Position> initialConfiguration;
    private HashMap<Position, JEntity> entities;
    private List<JEntity> alives;
    private EpochRunner runner;
    private boolean firstRun;

    public JGrid() {
	rows = 25; /* use odd numbers */
	columns = 50; /* use odd numbers */
	horizontalTraslation = rows / 2 + 1;
	verticalTraslation = columns / 2 + 1;
	initialConfiguration = new HashSet<Position>();
	entities = new HashMap<Position, JEntity>();
	alives = new ArrayList<JEntity>();
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

    public boolean canPlay() {
	return !initialConfiguration.isEmpty();
    }

    public Position getRelative(Position coord) {
	int newX = coord.getRow() + horizontalTraslation;
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

    private void addCell(Position pos) {
	JEntity entityContainer = new JEntity(JEntity.DEAD, pos);
	entityContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

    public void removeGridListeners() {
	Iterator<Position> keyIterator = entities.keySet().iterator();
	while (keyIterator.hasNext()) {
	    JEntity entityContainer = entities.get(keyIterator.next());
	    ActionListener listener[] = entityContainer.getActionListeners();
	    entityContainer.removeActionListener(listener[0]);
	    entityContainer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
    }

    public void addActionListener(ActionListener listener) {
	Iterator<Position> keyIterator = entities.keySet().iterator();
	while (keyIterator.hasNext()) {
	    JEntity entityContainer = entities.get(keyIterator.next());
	    entityContainer.addActionListener(listener);
	    entityContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
    }

    public void removeSampleListener() {
	Iterator<Position> keyIterator = entities.keySet().iterator();
	while (keyIterator.hasNext()) {
	    JEntity entityContainer = entities.get(keyIterator.next());
	    ActionListener listener[] = entityContainer.getActionListeners();
	    for (int i = 0; i < listener.length; i++) {
		if (listener[i] instanceof SampleListener) {
		    entityContainer.removeActionListener(listener[i]);
		}
	    }
	    entityContainer.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
    }

    public JEntity getEntityByPosition(Position pos) {
	return entities.get(pos);
    }

    public void addToInitialConfiguration(JEntity entity) {
	initialConfiguration.add(entity.getPosition());
	alives.add(entity);
    }

    public void removeFromInitialConfiguration(JEntity entity) {
	initialConfiguration.remove(entity.getPosition());
	alives.remove(entity);
    }

    public void setInitialConfiguration() {
	runner = new EpochRunner(initialConfiguration);
	initialConfiguration.clear();
	firstRun = false;
    }

    public void runEpoch() {
	Iterator<JEntity> itr = alives.iterator();
	while (itr.hasNext()) {
	    itr.next().die();
	}
	alives.clear();

	WorldInterface world = runner.runEpoch();
	Iterator<Entity> aliveIterator = world.getAliveEntities().iterator();
	int stillVisible = 0;
	while (aliveIterator.hasNext()) {
	    Entity entity = aliveIterator.next();
	    JEntity entityContainer = entities.get(entity.getPosition());
	    if (entityContainer != null) {
		stillVisible++;
		entityContainer.rise();
		alives.add(entityContainer);
	    }
	}
	if (stillVisible == 0) {
	    ViewManager.getManager().showEndDialog();
	}
	revalidate();
    }

    public void rise(JEntity entityContainer) {
	addToInitialConfiguration(entityContainer);
	entityContainer.rise();
    }

    public void kill(JEntity entityContainer) {
	removeFromInitialConfiguration(entityContainer);
	entityContainer.die();

    }

}
