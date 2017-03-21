package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Absorber;
import model.Ball;
import model.Model;
import model.Square;
import model.Triangle;
import model.circle;
import model.flipper2;
import physics.Circle;
import model.Line;

import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;



public class Board extends JPanel implements Observer {

	public class Flipper implements KeyListener{
		
		public Flipper(int x,int y){
		xpos = x;
		ypos = y;
	//	setPreferredSize(new Dimension(800, 600));
	//	setFocusable(true);

		init();
		updateTransforms();
		//ID = id;
		
	}
	
	public void init() {
		rect = new Rectangle(30, 100);
		transform = new AffineTransform();
		rotationTransform = new AffineTransform();
		translateTransform = new AffineTransform();
		addKeyListener(this);
		
		
	}

	public void paintFlipper(Graphics g) {

		paintFlipper(g);
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


	
	
	
	
	}

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;
	
	private Rectangle rect;
	private String ID;
	private String connectedID;
	private int xpos;
	private int ypos;

	private int xboardpos;
	private int yboardpos;

	private int rotation = 0;
	AffineTransform transform;
	AffineTransform rotationTransform;
	AffineTransform translateTransform;
	

	public Board(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(gm.getBoardBackgroundColour());

	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		// Draw all the vertical lines
		for (Line vl : gm.getLines()) {
			g2.fillRect(vl.getX(), vl.getY(), vl.getWidth(), 1);
		}
		
		
		// Draw all the squares
		for (Square s : gm.getSquares()) {
			g2.setColor(s.getColour());
			g2.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g2.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());

		}

		//draw all the circles
		for (circle c : gm.getCircles()) {
			g2.setColor(c.getColour());
			Circle circle = c.getCircle();
			double x = circle.getCenter().getX()-(circle.getRadius());
			double y = circle.getCenter().getY()-(circle.getRadius());
			double d = (circle.getRadius())*2;
			g2.fillOval((int)x,(int)y, (int)d ,(int)d);
		}
		
		//draw all the absorbers
		for (Absorber a : gm.getAbsorbers()){
	    	g.setColor(a.getColour());
			g.drawRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
	    	g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
	    }
	    
	    //draw all the triangles
	    for(Triangle t : gm.getTriangles()){
	    	if(t!=null){
	    		g.setColor(t.getColour());
	    		g.drawPolygon(t.getXPoints(), t.getYPoints(), 3);
	    		g.fillPolygon(t.getXPoints(), t.getYPoints(), 3);
	    	}
	    }
	    
	    //draw flippers
	    
	    for(flipper2 f : gm.getFlippers2()){
	        g.drawRoundRect(f.getXPos()*20, f.getYPos()*20,f.getSquare().getWidth(),f.getSquare().getHeight(), 50, 30);
	        g.fillRoundRect(f.getXPos()*20, f.getYPos()*20,f.getSquare().getWidth(),f.getSquare().getHeight(), 50, 30);

	    }
	    
	    
	    //draw grid highlight
	    if(gm.getBuildMode()){
	    	try {
				BufferedImage grid = ImageIO.read(new File("grid.png"));
				g.drawImage(grid, 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	Square gh = gm.getGridHighlight();
	    	g.setColor(gm.getGridHighlightColour());
	    	float thickness = 3;
	    	Stroke oldStroke = g2.getStroke();
	    	g2.setStroke(new BasicStroke(thickness));
	    	g.drawRect(gh.getX(), gh.getY(), gh.getWidth(), gh.getHeight());
	    	g2.setStroke(oldStroke);
//	    	g.fillRect(gh.getX(), gh.getY(), gh.getWidth(), gh.getHeight());
	    }
		
		Ball b = gm.getBall();
		if (b != null) {
			g2.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g2.fillOval(x, y, width, width);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			repaint();
		}
	
	
	
	

	
	

	
}
