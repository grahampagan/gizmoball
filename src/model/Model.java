package model;

import java.util.ArrayList;
import java.util.Observable;

import physics.*;

public class Model extends Observable {
	
	private Ball b; 
	private ArrayList<Line> lines;
	private Walls walls; 

	public Model() {
		b = new Ball(25, 25, 100, 100);
		walls = new Walls(0, 0, 500, 500);
		lines = new ArrayList<Line>();
	}
	
	private Ball moveBallForTime(Ball ball, double time){
		double newX = 0.0;
		double newY = 0.0;
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		newX = ball.getExactX() + (xVel * time);
		newY = ball.getExactY() + (yVel * time);
		ball.setExactX(newX);
		ball.setExactY(newY);
		return ball;
	}
	
	public void moveBall(){
		double moveTime = 0.05; // 0.05 = 20 times per second as per Gizmoball

		if (b != null && !b.stopped()) {

			Collisions cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if (tuc > moveTime) {
				// No collision ...
				b = moveBallForTime(b, moveTime);
			} else {
				// We've got a collision in tuc
				b = moveBallForTime(b, tuc);
				// Post collision velocity ...
				b.setVelo(cd.getVelo());
			}

			// Notify observers ... redraw updated view
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	private Collisions timeUntilCollision(){
		
				Circle ballCircle = b.getCircle();
				Vect ballVelocity = b.getVelo();
				Vect newVelo = new Vect(0, 0);
				
				double shortestTime = Double.MAX_VALUE;
				double time = 0.0;

				// Time to collide with 4 walls
				ArrayList<LineSegment> lss = walls.getLineSegments();
				for (LineSegment line : lss) {
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(line, b.getVelo(), 1.0);
					}
				}

				for (Line line : lines) {
					LineSegment ls = line.getLineSeg();
					time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(ls, b.getVelo(), 1.0);
					}
				}
				return new Collisions(shortestTime, newVelo);
	}
	
	public Ball getBall(){
		return b;
	}
	
	public void setBallSpeed(int x, int y){
		b.setVelo(new Vect(x, y));
	}
	
	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public void addLine(Line l) {
		lines.add(l);
	}
}
