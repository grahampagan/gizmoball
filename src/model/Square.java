package model;

import java.util.ArrayList;

import physics.*;

public class Square {
//	private int xpos1;
//	private int ypos1;
//	private int xpos2;
//	private int ypos2;
//	ArrayList<LineSegment> ls;
//
//	public Square(int x1, int y1, int x2, int y2) {
//		xpos1 = x1;
//		ypos1 = y2;
//		xpos2=x2;
//		ypos2=y2;
//		ls = new ArrayList();
//		
//		//creates the four sides and adds them to the list
//		
//		ls.add(new LineSegment(x1, y1, x2, y1));	//bottom
//		ls.add(new LineSegment(x1, y1, x1, y2));	//left
//		ls.add(new LineSegment(x1, y2, x2, y2));	//top
//		ls.add(new LineSegment(x2, y2, x2, y1));	//right
//
//	}
	
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	ArrayList<LineSegment> ls;

	public Square(int x, int y, int w, int h) {
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		ls = new ArrayList();
		
		ls.add(new LineSegment(x, y + h, x + w, y + h));	//bottom
		ls.add(new LineSegment(x, y, x, y + h));			//left
		ls.add(new LineSegment(x, y, x + w, y));			//top
		ls.add(new LineSegment(x + w, y, x + w, y + h));	//right
		
	}

	public ArrayList<LineSegment> getLineSeg() {
		return ls;
	}

//	public int getX1() {
//		return xpos1;
//	}
//
//	public int getY1() {
//		return ypos1;
//	}
//
//	public int getX2() {
//		return xpos2;
//	}
//
//	public int getY2() {
//		return ypos2;
//	}
//	
//	public int getWidth(){
//		return xpos2-xpos1;
//	}
//	
//	public int getHeight(){
//		return ypos2-ypos1;
//	}

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
