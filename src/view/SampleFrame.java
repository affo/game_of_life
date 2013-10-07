package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Glider;
import model.Position;
import model.Shape;

public class SampleFrame extends JFrame {

    private static final long serialVersionUID = -1369483078521464006L;
    SamplePanel panel;

    public SampleFrame(String title, int height, int width) {
	super(title);
	this.setPreferredSize(new Dimension(width, height));
	this.setResizable(false);
	panel = new SamplePanel();
	this.add(panel, BorderLayout.CENTER);
    }

    private class SamplePanel extends JPanel {
	private static final long serialVersionUID = 7618654204985231137L;
	private List<JButton> buttons;

	public SamplePanel() {
	    super();
	    buttons = new ArrayList<JButton>();

	    SampleButton glider = new SampleButton("Glider", new Glider());
	    glider.addActionListener(new SampleListener(glider));
	    buttons.add(glider);

	    for (JButton button : buttons) {
		this.add(button);
		button.setEnabled(true);
	    }
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

    public class SampleListener implements ActionListener {
	private SampleButton caller;

	public SampleListener(SampleButton caller) {
	    super();
	    this.caller = caller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    JGrid grid = ViewManager.getManager().getGrid();
	    grid.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg) {
		    JEntity entityContainer = (JEntity) arg.getSource();
		    Iterator<Position> itr = caller.getShape(
			    entityContainer.getPosition()).iterator();
		    JGrid grid = ViewManager.getManager().getGrid();
		    while (itr.hasNext()) {
			System.out.println(itr.next());
			grid.rise(grid.getEntityByPosition(itr.next()));
		    }
		    grid.removeSampleListener();
		}
	    });
	}
    }

}
