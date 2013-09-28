package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JEntity extends JButton {
	
	private static final long serialVersionUID = 6094768595944547213L;
	
	boolean alive;
	
	public JEntity(ImageIcon o){
		super(o);
		alive = false;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void rise(){
		alive = true;
	}
	
	public void die(){
		alive = false;
	}
	

}
