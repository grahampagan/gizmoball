package model;

import physics.*;
import java.awt.*;

public class Ball {

	private Vect velo;
	private double radius;
	private double xpos;
	private double ypos;
	private boolean stopped; 
	private Color colour; 
	
	public Ball(double xPos, double yPos, double xVelo, double yVelo){
		xpos = xPos;
		ypos = yPos;
		velo = new Vect(xVelo, yVelo);
		radius = 10; 
		colour = Color.BLUE;
	}
	
	public Vect getVelo() {
		return velo;
	}
	
	public void setVelo(Vect v){
		velo = v;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public double getXPos(){
		return xpos;
	}
	
	public double getYPos(){
		return ypos; 
	}
	
	public boolean stopped() {
		return stopped;
	}
	
	public double getExactX() {
		return xpos;
	}

	public double getExactY() {
		return ypos;
	}
	
	public void setExactX(double x) {
		xpos = x;
	}

	public void setExactY(double y) {
		ypos = y;
	}
	
	public Circle getCircle() {
		return new Circle(xpos, ypos, radius);

	}
	
	public Color getColour() {
		return colour;
	}
}
