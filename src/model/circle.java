package model;

import java.awt.*;
import java.util.ArrayList;

import physics.*;

public class circle {
	
	private Circle circle;
	private String ID;
	int x;
	int y;
	private String connectedID;
	private boolean triggered;
	private Color colour;

	public circle(double x, double y, double r, String i, int xpos, int ypos) {
		ID = i;
		circle=new Circle(x,y,r);
		this.x = xpos;
		this.y = ypos;
		triggered=false;
		connectedID = "";
		colour = new Color(219, 107, 2);
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

	public Color getColour() { return colour; }
	
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
