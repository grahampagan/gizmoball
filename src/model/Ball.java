package model;

import physics.*;
import java.awt.*;

public class Ball implements gizmo {

	private Vect velo;
	private double radius;
	private double xpos;
	private double ypos;
	private double originalX;
	private double originalY;
	private double originalXVelo;
	private double originalYVelo;
	private boolean stopped; 
	private Color colour;
	private String type;
	private String ID;
	
	public Ball(double xPos, double yPos, double xVelo, double yVelo, String id){
		xpos = xPos;
		ypos = yPos;
		originalX = xPos;
		originalY = yPos;
		originalXVelo = xVelo;
		originalYVelo = yVelo;
		velo = new Vect(xVelo, yVelo);
		radius = 5; 
		colour = Color.BLUE;
		type = "Ball";
		ID = id;
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
	
	public void applyGravity(double g, double time){
		double x = this.getVelo().getX();
		double y = (this.getVelo().getY() + g * time);
		Vect v = new Vect(x,y);
		this.setVelo(v);
	}
	
	public void applyGravity(double g){
		double x = this.getVelo().getX();
		double y = this.getVelo().getY() + g;
		Vect v = new Vect(x,y);
		this.setVelo(v);
	}
	
	public void applyFriction(double mu, double mu2, double time){
		double friction = 1 - mu * time - mu2 * Math.abs(this.getVelo().length()) * time;

		double x = this.getVelo().getX() * friction;
		double y = this.getVelo().getY() * friction;
		Vect v = new Vect(x, y);
		
		this.setVelo(v);
	}
	
	public void applyFriction(double mu, double mu2){
		double xFriction = 1 - (mu/1000);
		double yFriction = 1 - (mu2/1000);
		
		double x = this.getVelo().getX() * xFriction;
		double y = this.getVelo().getY() * yFriction;
		Vect v = new Vect(x, y);
		
		this.setVelo(v);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return ID;	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public double getPositionX(){
		return (xpos - 12.5)/25;
	}
	
	public double getPositionY(){
		return (ypos - 12.5)/25;
	}
	
	public void resetBall(){
		xpos = originalX;
		ypos = originalY;
		velo = new Vect(originalXVelo, originalYVelo);
	}
}
