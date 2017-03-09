package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Absorber;
import model.Ball;
import model.Model;
import model.Square;
import model.Triangle;
import model.absorberGizmo;
import model.board;
import model.circle;
import model.circleGizmo;
import model.gizmo;
import model.squareGizmo;
import model.triangleGizmo;
import physics.Circle;
import model.Line;


public  class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected Model gm;

	public Board(int w, int h, Model m) {
		// Observe changes in Model
		m.addObserver(this);
		width = w;
		height = h;
		gm = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
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
			g2.drawRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
			g2.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());

		}

		//draw all the circles
		for (circle c : gm.getCircles()) {
			Circle circle = c.getCircle();
			double x = circle.getCenter().getX()-(circle.getRadius());
			double y = circle.getCenter().getY()-(circle.getRadius());
			double d = (circle.getRadius())*2;
			g2.fillOval((int)x,(int)y, (int)d ,(int)d);
		}
		
		//draw all the absorbers
		Absorber a = gm.getAbsorber();
	    if(a !=null){
	    	g.drawRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
	    	g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
	    }
	    
	    //draw all the triangles
	    for(Triangle t : gm.getTriangles()){
	    	if(t!=null){
	    		g.drawPolygon(t.getXPoints(), t.getYPoints(), 3);
	    		g.fillPolygon(t.getXPoints(), t.getYPoints(), 3);
	    	}
	    }
	    
	    //draw grid highlight
	    if(gm.getBuildMode()){
	    	Square gh = gm.getGridHighlight();
	    	g.drawRect(gh.getX(), gh.getY(), gh.getWidth(), gh.getHeight());
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
