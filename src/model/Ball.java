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
	private double gravity; 
	
	public Ball(double xPos, double yPos, double xVelo, double yVelo){
		xpos = xPos;
		ypos = yPos;
		velo = new Vect(xVelo, yVelo);
		radius = 10; 
		colour = Color.BLUE;
		gravity = -10;
	}
	
	public Vect getVelo() {
		return velo;
	}

	public void setVelo(Vect v) {
		velo = v;
	}

	public double getRadius() {
		return radius;
	}

	public Circle getCircle() {
		return new Circle(xpos, ypos, radius);

	}

	// Ball specific methods that deal with double precision.
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

	public void stop() {
		stopped = true;
	}

	public void start() {
		stopped = false;
	}

	public boolean stopped() {
		return stopped;
	}

	public Color getColour() {
		return colour;
	}
	
	public Vect getCenter() {
		return new Vect(xpos, ypos);
	}
	
	public void setGravity(double g){
		gravity = g;
	}
	
	public double getGraivty(){
		return gravity;
	}
	
	public void applyGravity(){
		double x = this.getVelo().getX();
		double y = (this.getVelo().getY() - this.getGraivty());
		Vect v = new Vect(x,y);
		this.setVelo(v);
	}
}
