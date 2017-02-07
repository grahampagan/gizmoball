package model;

import physics.*;

public class Ball {

	private Vect velo;
	private double radius;
	private double xpos;
	private double ypos;
	
	public Ball(double xPos, double yPos, double xVelo, double yVelo){
		xpos = xPos;
		ypos = yPos;
		velo = new Vect(xVelo, yVelo);
		radius = 10; 
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
}
