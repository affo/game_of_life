package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Glider;
import model.Position;
import model.Shape;
import resources.Resources;

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
	    glider.addActionListener(new SampleListener());
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

	public URL getIconPath() {
	    return Resources.class.getResource("img/"
		    + shape.getClass().getSimpleName() + ".png");
	}
    }

    private class SampleListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent arg) {
	    ViewManager viewManager = ViewManager.getManager();
	    viewManager.disposeSample();
	    SampleButton caller = (SampleButton) arg.getSource();
	    Cursor shapeCursor = getShapeCursor(caller.getIconPath());
	    viewManager.getGrid().addActionListener(
		    new SamplePositioner(caller), shapeCursor);
	}

	private Cursor getShapeCursor(URL url) {
	    try {
		Image image = ImageIO.read(url);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		Point hotspot = new Point(width / 2, height / 2);
		Cursor shapeCursor = Toolkit.getDefaultToolkit()
			.createCustomCursor(image, hotspot, "Shape");
		return shapeCursor;
	    } catch (IOException e) {
		// fuck
	    }
	    return new Cursor(Cursor.DEFAULT_CURSOR);
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
	    Iterator<Position> itr = caller.getShape(
		    entityContainer.getPosition()).iterator();
	    JGrid grid = ViewManager.getManager().getGrid();
	    while (itr.hasNext()) {
		grid.rise(grid.getEntityByPosition(itr.next()));
	    }
	    grid.removeSamplePositionerListener();
	}
    }

}
