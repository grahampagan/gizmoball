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
	ArrayList<Circle> cs;
	private String ID;
	private String connectedID;
	private boolean triggered;
	
	private int xboardpos;
	private int yboardpos;

	public Square(int x, int y, int w, int h, String i, int j, int k) {
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		ls = new ArrayList();
		cs = new ArrayList();
		ID = i;

		triggered = false;
		
		xboardpos = j;
		yboardpos=k;
		ls.add(new LineSegment(x, y + h, x + w, y + h));	//bottom
		ls.add(new LineSegment(x, y, x, y + h));			//left
		ls.add(new LineSegment(x, y, x + w, y));			//top
		ls.add(new LineSegment(x + w, y, x + w, y + h));	//right
		
		cs.add(new Circle(x, y ,0));			//top left
		cs.add(new Circle(x + w, y, 0));		//top right
		cs.add(new Circle(x, y + h, 0));		//bottom left
		cs.add(new Circle(x + w, y + h, 0));	//bottom right
		
	}

	public ArrayList<LineSegment> getLineSeg() {
		return ls;
	}
	
	public ArrayList<Circle> getCircles() {
		return cs;
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
	
	public String getID(){
		return ID;
	}
	
	public int getPositionX(){
		return xboardpos;
	}
	public int getPositionY(){
		return yboardpos;
	}
	
	public void setPositionX(int x) {
		xboardpos = x;
	}
	
	public void setPositionY(int y) {
		yboardpos = y;
	}
	
	public String getConnected(){
		return connectedID;
	}
	
	public void setConnected(String a){
		connectedID = a;
	}
	
	public boolean isConnected(){
		String clear = "";
		
		if(connectedID.equals(clear)){
			return false;
		}else{
			return true;
		}
	}
	
	public void trigger(){
		triggered = true;
	}
	
	public void unTrigger(){
		triggered = false;
	}


}
