package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Position;
import model.shapes.CannonGlider;
import model.shapes.Glider;
import model.shapes.Random;
import model.shapes.Shape;
import model.shapes.Toad;

public class SampleFrame extends JFrame {

    private static final long serialVersionUID = -1369483078521464006L;
    SamplePanel panel;

    public SampleFrame(String title, int height, int width) {
	super(title);
	this.setPreferredSize(new Dimension(width, height));
	this.setResizable(false);
	panel = new SamplePanel();
	// this.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	this.add(panel, BorderLayout.CENTER);
    }

    private class SamplePanel extends JPanel {
	private static final long serialVersionUID = 7618654204985231137L;
	private List<JButton> buttons;

	public SamplePanel() {
	    super();
	    setBorder(BorderFactory.createCompoundBorder(null,
		    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

	    JLabel label = new JLabel(
		    "Choose a sample, than click on the grid where you want to place it");
	    this.add(label);

	    buttons = new ArrayList<JButton>();

	    buttons.add(shapeButton(new Glider()));
	    buttons.add(shapeButton(new CannonGlider()));
	    buttons.add(shapeButton(new Toad()));
	    buttons.add(shapeButton(new Random()));

	    for (JButton button : buttons) {
		this.add(button);
		button.setEnabled(true);
	    }
	}

	private JButton shapeButton(Shape shape) {
	    SampleButton shapeButton = new SampleButton(shape.getClass()
		    .getSimpleName(), shape);
	    shapeButton.addActionListener(new SampleListener());
	    return shapeButton;
	}
    }

    private class SampleButton extends JButton {
	private static final long serialVersionUID = -184931533941113663L;
	private Shape shape;

	public SampleButton(String text, Shape shape) {
	    super(text);
	    this.shape = shape;
	}

	public Set<Position> getShape(Position start) {
	    return shape.makeShape(start);
	}
    }

    private class SampleListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg) {
	    ViewManager viewManager = ViewManager.getManager();
	    JGrid grid = viewManager.getGrid();
	    SampleButton caller = (SampleButton) arg.getSource();

	    viewManager.disposeSample();
	    grid.removeGridListeners();
	    grid.addActionListener(new SamplePositioner(caller));
	}
    }

    public class SamplePositioner implements ActionListener {
	SampleButton caller;

	public SamplePositioner(SampleButton caller) {
	    this.caller = caller;
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
	    JEntity entityContainer = (JEntity) arg.getSource();
	    Set<Position> positions = caller.getShape(entityContainer
		    .getPosition());
	    Iterator<Position> itr = positions.iterator();
	    JGrid grid = ViewManager.getManager().getGrid();
	    while (itr.hasNext()) {
		grid.rise(grid.getEntityByPosition(itr.next()));
	    }
	    grid.removeSamplePositionerListener();
	    grid.addGridListeners();
	}
    }

}
