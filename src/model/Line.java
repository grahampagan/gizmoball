package model;

import physics.*;

public class Line {
	private int xpos;
	private int ypos;
	private int width;
	private LineSegment ls;

	public Line(int x, int y, int w) {
		xpos = x;
		ypos = y;
		width = w;
		ls = new LineSegment(x, y, x + w, y);
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
}
