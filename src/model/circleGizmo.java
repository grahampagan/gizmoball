package model;

import physics.Circle;

public class circleGizmo implements gizmo{
	
	Circle circle;
	int xChord;
	int yChord;
	String id;
	String type;
	
	public circleGizmo(Circle c, int x, int y, String name){
		circle = c;
		xChord = x;
		yChord = y;
		id = name;
		type = "Circle";
		
	}
	
	public Circle getCircle(){
		return circle;
	}
	
	public int getX(){
		return xChord;
	}
	
	public int getY(){
		return yChord;
	}
	
	public String getID(){
		return id;
	}
	
	public String getType(){
		return type;
	}
}
