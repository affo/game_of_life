package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

    public SampleFrame(String title) {
	super(title);
	this.setResizable(false);
	this.setLayout(new BorderLayout());
	panel = new SamplePanel();
	this.add(panel, BorderLayout.CENTER);
    }

    private class SamplePanel extends JPanel {
	private static final long serialVersionUID = 7618654204985231137L;
	private List<JButton> buttons;

	public SamplePanel() {
	    super();
	    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    JLabel label0 = new JLabel("Choose a sample");
	    JLabel label1 = new JLabel("than click on the grid");
	    JLabel label2 = new JLabel("where you want to place it");
	    label0.setAlignmentX(Component.CENTER_ALIGNMENT);
	    label1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    label2.setAlignmentX(Component.CENTER_ALIGNMENT);
	    label1.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
	    label2.setBorder(BorderFactory.createEmptyBorder(3, 0, 10, 0));
	    this.add(label0);
	    this.add(label1);
	    this.add(label2);

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
	    shapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		JEntity container = grid.getEntityByPosition(itr.next());
		if (container != null) {
		    grid.rise(container);
		}
	    }
	    grid.removeSamplePositionerListener();
	    grid.addGridListeners();
	}
    }

}
