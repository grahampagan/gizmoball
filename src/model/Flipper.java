package model;


import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

//import javax.swing.JFrame;
import javax.swing.JPanel;

public class Flipper extends JPanel implements KeyListener {

	private Rectangle rect;
	private String ID;
	private String connectedID;

	private int rotation = 0;
	AffineTransform transform;
	AffineTransform rotationTransform;
	AffineTransform translateTransform;

	public Flipper(String id) {
	//	setPreferredSize(new Dimension(800, 600));
	//	setFocusable(true);

	//	init();
		updateTransforms();
		ID = id;
		
	}

	public void init() {
		rect = new Rectangle(30, 100);
		transform = new AffineTransform();
		rotationTransform = new AffineTransform();
		translateTransform = new AffineTransform();
		addKeyListener(this);
		
		
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setTransform(transform);
		updateTransforms();
		g2d.setColor(Color.RED);
		g2d.fill(rect);
		
	}

	public void keyPressed(KeyEvent k) {
		int code=k.getKeyCode();
		if(code== KeyEvent.VK_UP){
			if (rotation == 0) {
				rotation = rotation + 90;
				repaint();
			}
		}
		updateTransforms();
	}

	public void updateTransforms() {
		// Resets transform to rotation
		rotationTransform.setToRotation(Math.toRadians(rotation));
		translateTransform.setToTranslation(getWidth() / 2, getHeight() / 2);

		// Chain the transforms (Note order matters)
		transform.setToIdentity();
		transform.concatenate(translateTransform);
		transform.concatenate(rotationTransform);
	}

	public void keyReleased(KeyEvent k) {
		int code=k.getKeyCode();
		if(code== KeyEvent.VK_UP){
			rotation = rotation - 90;

			repaint();

		}
		updateTransforms();

	}

	public void keyTyped(KeyEvent k) {
	}

	public String getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public String getConnected(){
		return connectedID;
	}
	
	public void setConnected(String a){
		connectedID = a;
	}
	
	public void clearConnected(){
		connectedID = "";
	}
	
	public boolean isConnected(){
		String clear = "";
		
		if(connectedID.equals(clear)){
			return false;
		}else{
			return true;
		}
	}


//	public static void main(String input[]) {
//		JFrame f = new JFrame("Flipper");
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(600, 800);
//		f.setResizable(false);
//		f.getContentPane().add(new Flipper());
//		f.setVisible(true);
//	}
	
}
