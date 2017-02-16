package model;

import java.util.ArrayList;

import physics.LineSegment;

public class Triangle {
	
	private int xPos1;
	private int xPos2;
	private int xPos3;
	
	private int yPos1;
	private int yPos2;
	private int yPos3;

	ArrayList<LineSegment> ls;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3){
		
		ls = new ArrayList();
		
		xPos1 = x1;
		xPos2 = x2;
		xPos3 = x3;
		
		yPos1=y1;
		yPos2=y2;
		yPos3=y3;
		
		ls.add(new LineSegment(x1, y1, x2, y2));
		ls.add(new LineSegment(x1, y1, x3, y3));
		ls.add(new LineSegment(x2, y2, x3, y3));
	}


private ArrayList<LineSegment> getLineSeg(){
	return ls;
}

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

}
