package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import physics.*;

public class Model extends Observable {
	
	private Ball b; 
	private ArrayList<Line> lines;
	private ArrayList<circle> circles;
	private Absorber absorber;
	private Walls walls; 
	private ArrayList<Square> squares;
	private ArrayList<Triangle> triangles;

	board gizmoBoard;


	public Model() throws IOException {
		b = new Ball(335, 25, 100, 100, "ball1");
		walls = new Walls(0, 0, 500, 500);
		lines = new ArrayList<Line>();
		circles = new ArrayList<circle>();
		squares = new ArrayList<Square>();
		triangles = new ArrayList<Triangle>();
		absorber = null;
		gizmoBoard = new board();	
		
		
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
//			b.applyGravity();
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
				//Time to collide with line segments
				for (Line line : lines) {
					LineSegment ls = line.getLineSeg();
					time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(ls, b.getVelo(), 1.0);
					}
					
					Circle x = line.getXCircle();
					time = Geometry.timeUntilCircleCollision(x, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(x.getCenter(), b.getCenter(), b.getVelo());
					}
					
					Circle y = line.getYCircle();
					time = Geometry.timeUntilCircleCollision(y, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(y.getCenter(), b.getCenter(), b.getVelo());
					}
				}
				//Time to collide with circle (slightly broken, detects it needs to but doesnt reflect)
				for (circle circle : circles) {
					Circle c = circle.getCircle();
					time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
					}
				}
				
				//time to collide with triangles
				for(Triangle triangle : triangles){
					for (LineSegment line : triangle.getLineSeg()) {
						time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectWall(line, b.getVelo(), 1.0);
					}
					for (Circle c : triangle.getCircles()){
						time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
						}
					}
				}
				}
				
//				LineSegment lineseg = absorber.getLineSeg();
//				time = Geometry.timeUntilWallCollision(lineseg, ballCircle, ballVelocity);
//				if (time < shortestTime) {
//					shortestTime = time;
//							newVelo = Geometry.reflectWall(lineseg, b.getVelo(), 1.0);
//						}
				
				// Time to collide with squares
				for(Square s : squares){
				for (LineSegment line : s.getLineSeg()) {
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectWall(line, b.getVelo(), 1.0);
					}
				}
				for (Circle c : s.getCircles()){
					time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
					}
				}
				}
				
				// Absorber collisions 
				if (absorber != null) {
					if (absorber.getAbsorbed()){
						b.setExactX(absorber.absorbCoor(b).getX());
						b.setExactY(absorber.absorbCoor(b).getY());
						b.setVelo(new Vect(0, 0));
						this.setChanged();
						this.notifyObservers();
					}

				for (LineSegment line : absorber.getLineSeg()) {
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if (time <= 0.05){
						shortestTime = time;
						absorber.setAbsorbed(true);					
					}
					else if (time < shortestTime){
						shortestTime = time;
					}
				}
				for (Circle c : absorber.getCircles()){
					time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
					if (time <= 0.05){
						shortestTime = time;
						absorber.setAbsorbed(true);					
					}
					else if (time < shortestTime){
						shortestTime = time;
					}
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
	
	public ArrayList<circle> getCircles() {
		return circles;
	}

	
	public void addLine(Line l) {
		lines.add(l);
	}
	
	public void addCircle(Circle c){
		circles.add(new circle(c));
	}
	
	public void addAbsorber(Absorber a){
		absorber = a;
	}
	
	public void addSquare(Square s) {
		squares.add(s);
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}


	
	public Absorber getAbsorber(){
		return absorber;
	}
	
	public void addTriangle(Triangle t){
		triangles.add(t);
	}
	
	public ArrayList<Triangle> getTriangles(){
		return triangles;
	}
	
	public board getBoard(){
		return gizmoBoard;
	}
	
	public boolean containsName(String id){
		for (circle circle : circles) {
			if (circle.getID().equals(id)){
				return true;
			}
		}
		
		for(Triangle triangle : triangles){
			if(triangle.getID().equals(id)){
				return true;
			}
		}
		
		for(Square s : squares){
			if(s.getID().equals(id)){
				return true;
			}
		}

		return false;

	}

	public boolean hasAtPosition(int parseInt, int parseInt2) {
		for (circle circle : circles) {
			if (circle.getPositionX()==parseInt && circle.getPositionY()==parseInt2){
				return true;
			}
		}
		
		for(Triangle triangle : triangles){
			if(triangle.getPositionX()==parseInt && triangle.getPositionY()==parseInt2){
				return true;
			}
		}
		
		for(Square s : squares){
			if(s.getPositionX()==parseInt && s.getPositionY()==parseInt2){
				return true;
			}
		}

		return false;

	}
	
	public void delete(String id){
		for (circle circle : circles) {
			if (circle.getID().equals(id)){
				circles.remove(circle);
			}
		}
		
		for(Triangle triangle : triangles){
			if(triangle.getID().equals(id)){
				triangles.remove(triangle);
			}
		}
		
		for(Square s : squares){
			if(s.getID().equals(id)){
				squares.remove(s);
			}
		}

	}
	
	
	}
	

