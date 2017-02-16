package model;

import physics.Circle;

public class circleGizmo implements gizmo{
	
	Circle circle;
	int xChord;
	int yChord;
	
	public circleGizmo(Circle c, int x, int y){
		circle = c;
		xChord = x;
		yChord = y;
		
	}
	
	public Circle getSquare(){
		return circle;
	}
	
	public int getX(){
		return xChord;
	}
	
	public int getY(){
		return yChord;
	}
}
