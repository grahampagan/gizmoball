package model;

import java.util.ArrayList;

import physics.*;

public class circle {
	
	private Circle circle;
	private String ID;
	int x;
	int y;
	private String connectedID;

	public circle(double x, double y, double r, String i, int xpos, int ypos) {
		ID = i;
		circle=new Circle(x,y,r);
		this.x = xpos;
		this.y = ypos;
	}

	public circle(Circle c){
		circle = c;
	}
	public Circle getCircle() {
		return circle;
	}

	
	public String getID(){
		return ID;
	}
	
	public int getPositionX(){
		return x;
	}
	
	public int getPositionY(){
		return y;
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

}
