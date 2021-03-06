package model;

import java.awt.*;
import java.util.ArrayList;

import physics.LineSegment;
import physics.Circle;

public class Triangle {
	
	private int xPos1;
	private int xPos2;
	private int xPos3;
	
	private int yPos1;
	private int yPos2;
	private int yPos3;
	
	private int xboardpos;
	private int yboardpos;

	private Color colour;

	private int rotations;
	
	ArrayList<LineSegment> ls;
	ArrayList<Circle> cs;
	
	String ID;
	String connectedID;
	
	private boolean triggered;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, String i, int xpos, int ypos){
		
		ID = i;
		xboardpos = xpos;
		yboardpos=ypos;
		ls = new ArrayList<>();
		cs = new ArrayList<>();
		rotations = 0;
		colour = new Color(1, 145, 18);
		
		xPos1 = x1;
		xPos2 = x2;
		xPos3 = x3;
		
		yPos1=y1;
		yPos2=y2;
		yPos3=y3;
		
		ls.add(new LineSegment(x1, y1, x2, y2));
		ls.add(new LineSegment(x1, y1, x3, y3));
		ls.add(new LineSegment(x2, y2, x3, y3));
		
		cs.add(new Circle(x1, y1, 0));
		cs.add(new Circle(x2, y2, 0));
		cs.add(new Circle(x3, y3, 0));
		
		triggered = false;
		connectedID = "";

	}


public ArrayList<LineSegment> getLineSeg(){
	return ls;
}

public ArrayList<Circle> getCircles() {
	return cs;
}

public Color getColour() { return colour; }

public int getx1(){
	return xPos1;
}

public int getx2(){
	return xPos2;
}

public int getx3(){
	return xPos3;
}

public int gety1(){
	return yPos1;
}

public int gety2(){
	return yPos2;
}

public int gety3(){
	return yPos3;
}

public int[] getXPoints(){
	int[] a = new int[3];

	a[0]=xPos1;
	a[1]=xPos2;
	a[2]=xPos3;
	
	return a;

}

public int[] getYPoints(){
	int[] a = new int[3];

	a[0]=yPos1;
	a[1]=yPos2;
	a[2]=yPos3;
	
	return a;

}

public void rotate(int x1, int x2, int x3, int y1, int y2, int y3){
	xPos1 = x1;
	xPos2 = x2;
	xPos3 = x3;
	yPos1 = y1;
	yPos2 = y2;
	yPos3 = y3;
	
	ls.clear();
	cs.clear();
	
	ls.add(new LineSegment(x1, y1, x2, y2));
	ls.add(new LineSegment(x1, y1, x3, y3));
	ls.add(new LineSegment(x2, y2, x3, y3));
	
	cs.add(new Circle(x1, y1, 0));
	cs.add(new Circle(x2, y2, 0));
	cs.add(new Circle(x3, y3, 0));
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

public int getRotations(){
	return rotations;
}

public void incrementRotations(){
	rotations++;
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

public void trigger(){
	triggered = true;
}

public void unTrigger(){
	triggered = false;
}


}
