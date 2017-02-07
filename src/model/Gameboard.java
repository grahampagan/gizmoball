package model;

import physics.*;

public class Gameboard {
	
	private Ball b; 

	public Gameboard() {
		b = new Ball(25, 25, 100, 100);
	}
	
	public void moveBall(){
		
	}
	
	private Ball moveBallForTime(Ball ball, double time){
		
	}
	
	private Collisions timeUntilCollision(){
		
	}
	
	public Ball getBall(){
		return b;
	}
	
	public void setBallSpeed(int x, int y){
		b.setVelo(new Vect(x, y));
	}
}
