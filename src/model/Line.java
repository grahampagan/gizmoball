package model;

import physics.*;

public class Line {
	private int xpos;
	private int ypos;
	private Circle xCircle;
	private Circle yCircle;
	private int width;
	private LineSegment ls;
	private String id;

	public Line(int x, int y, int w, String i) {
		xpos = x;
		ypos = y;
		width = w;
		id = i;
		ls = new LineSegment(x, y, x + w, y);
		xCircle = new Circle(xpos, ypos, 0);
		yCircle = new Circle(xpos + w, y, 0);
	}

	public LineSegment getLineSeg() {
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
	
	public Circle getXCircle() {
		return xCircle;
	}
	
	public Circle getYCircle() {
		return yCircle;
	}
	
	public String getId(){
		return id;
	}
}
