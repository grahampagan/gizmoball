package model;

import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public class Absorber {
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	ArrayList<LineSegment> ls;

	public Absorber(int x, int y, int w, int h) {
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		ls = new ArrayList();
		
		ls.add(new LineSegment(x, y, x, y));	//bottom
		ls.add(new LineSegment(x, y, x, y));	//left
		ls.add(new LineSegment(x, y, x, y));	//top
		ls.add(new LineSegment(x, y, x, y));	//right
		
	}

	public ArrayList<LineSegment> getLineSeg() {
		return ls;
	}

		public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight(){
		return height;
	}	
}


