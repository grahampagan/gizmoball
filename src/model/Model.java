package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
	private double gravity;
	private double mu, mu2;
	private Square gridHighlight;
	private boolean buildMode;

	private ArrayList<Flipper> flippers;


	public Model() throws IOException {
		b = new Ball(335, 25, 100, 100, "ball1");
		walls = new Walls(0, 0, 500, 500);
		lines = new ArrayList<Line>();
		circles = new ArrayList<circle>();
		squares = new ArrayList<Square>();
		triangles = new ArrayList<Triangle>();
		absorber = null;
		gridHighlight = new Square(0, 0, 25, 25, "GH", 0, 0);
//		gravity = 25;							//These are the values that 
//		mu = 0.025;								//the game should be played at, but it doesn't work well.
//		mu2 = 0.025;	
		gravity = 100;
		mu = 0.001;
		mu2 = 0.001;
		buildMode = false;
		
		flippers=new ArrayList<Flipper>();
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
//				b.applyFriction(mu, mu2, moveTime);
//				b.applyGravity(gravity, moveTime);
			} else {
				// We've got a collision in tuc
				b = moveBallForTime(b, tuc);
				// Post collision velocity ...
				b.setVelo(cd.getVelo());
//				b.applyFriction(mu, mu2, tuc);
//				b.applyGravity(gravity, tuc);
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
	
	public void addCircle(circle c){
		circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addAbsorber(Absorber a){
		absorber = a;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addSquare(Square s) {
		squares.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public Absorber getAbsorber(){
		return absorber;
	}
	
	public void addTriangle(Triangle t){
		triangles.add(t);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Triangle> getTriangles(){
		return triangles;
	}
	
	
	public void addFlipper(Flipper f){
		flippers.add(f);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Flipper> getFLippers(){
		return flippers;
	}
	
	public boolean containsName(String id){
		System.out.println(circles.isEmpty());
		for (circle c : circles) {
			System.out.println(c.getID());
			if (c.getID().equals(id)){
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
		
		for(Flipper f: flippers){
			if(f.getID().equals(id)){
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
		
		Iterator<circle> circleIter = circles.iterator();
		while(circleIter.hasNext()){
			circle c = circleIter.next();
		      if(c.getID().equals(id)) {
		        circleIter.remove();
		      }
		}
		
		Iterator<Triangle> triangleIter = triangles.iterator();
		while(triangleIter.hasNext()){
			Triangle t = triangleIter.next();
		      if(t.getID().equals(id)) {
		        triangleIter.remove();
		      }
		}
		
		Iterator<Square> squareIter = squares.iterator();
		while(squareIter.hasNext()){
			Square s = squareIter.next();
		      if(s.getID().equals(id)) {
		        squareIter.remove();
		      }
		}
		
//		for (circle circle : circles) {
//			if (circle.getID().equals(id)){
//				circles.remove(circle);
//			}
//		}
//		
//		for(Triangle triangle : triangles){
//			if(triangle.getID().equals(id)){
//				triangles.remove(triangle);
//			}
//		}
//		
//		for(Square s : squares){
//			if(s.getID().equals(id)){
//				squares.remove(s);
//			}
//		}

	}
	
	public void rotate(String id){

		for(Triangle t: triangles){
			if(t.getID().equals(id)){
				
				t.incrementRotations();
				int triRotations = t.getRotations();
				
				int oldX1 = t.getx1();
				int oldX2 = t.getx2();
				int oldX3 = t.getx3();
				int oldY1 = t.gety1();
				int oldY2 = t.gety2();
				int oldY3 = t.gety3();
				
				int newX1 = 0;
				int newX2 = 0;
				int newX3 = 0;
				int newY1 = 0;
				int newY2 = 0;
				int newY3 = 0;
								
				if((triRotations+4) % 4 == 0){
					newX1 = oldX1;
					newX2 = oldX2 + 25;
					newX3 = oldX3 - 25;
					newY1 = oldY1 - 25;
					newY2 = oldY2;
					newY3 = oldY3;
				}
				
				else if ((triRotations+4) % 4 == 1){
					newX1 = oldX1 + 25;
					newX2 = oldX2;
					newX3 = oldX3;
					newY1 = oldY1;
					newY2 = oldY2 + 25;
					newY3 = oldY3 - 25;
				}
				
				else if ((triRotations+4) % 4 == 2){
					newX1 = oldX1;
					newX2 = oldX2 - 25;
					newX3 = oldX3 + 25;
					newY1 = oldY1 + 25;
					newY2 = oldY2;
					newY3 = oldY3;
				}
				
				else if ((triRotations+4) % 4 == 3){
					newX1 = oldX1 - 25;
					newX2 = oldX2;
					newX3 = oldX3;
					newY1 = oldY1;
					newY2 = oldY2 - 25;
					newY3 = oldY3 + 25;
				}
				
				
				t.rotate(newX1, newX2, newX3, newY1, newY2, newY3);
						
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
	
	
	public void setGravity(double g){
		gravity = g;
	}
	
	public double getGraivty(){
		return gravity;
	}
	
	public void setMu(double i){
		mu = i;
	}
	
	public double getMu(){
		return mu;
	}
	
	public void setMu2(double i){
		mu2 = i;
	}
	
	public double getMu2(){
		return mu2;
	}
	
	public Square getGridHighlight(){
		return gridHighlight;
	}

	public void setGridHighlight(double xD, double yD) {
		int x = (int)xD;
		int y = (int)yD;
		gridHighlight = new Square (x*25, y*25, 25, 25, "GS", x, y);
		this.setChanged();
		this.notifyObservers();		
	}

	public String getIdOfPosition(int x, int y) {
		String r = null;
		if (hasAtPosition(x, y)) {
			for (circle c : circles) {
				if (c.getPositionX() == x && c.getPositionY() == y){
					r = c.getID();
				}
			}

			for(Triangle triangle : triangles){
				if(triangle.getPositionX() == x && triangle.getPositionY() == y){
					r = triangle.getID();
				}
			}

			for(Square s : squares){
				if(s.getPositionX() == x && s.getPositionY() == y){
					r = s.getID();
				}
			}
		}
		return r;
	}
	
	public void move(int fx, int fy, int x, int y) {
		int fromX = fx;
		int fromY = fy;

		if(hasAtPosition(fromX, fromY)) {
			System.out.println("from x: " + fromX + " y: " + fromY);
			if(!hasAtPosition(x, y)) {
				System.out.println("to x: " + x + " y: " + y);
				// not implemented for circle yet				
				
				Iterator<circle> cirIte = circles.iterator();
				ArrayList<circle> newCircles = new ArrayList<>();
				ArrayList<circle> oldCircles = new ArrayList<>();

				while (cirIte.hasNext()) {
					circle c = cirIte.next();
					if (c.getPositionX() == fromX && c.getPositionY() == fromY) {
						int ox = c.getPositionX();
						int oy = c.getPositionY();

						circle c1 = new circle((x * 25) + 12.5, (y * 25) + 12.5, 12.5, c.getID(), x, y);
						// delete(s.getID());
						// addSquare(s1);
						// squIte.remove();
						oldCircles.add(c);
						newCircles.add(c1);

						System.out.println(
								"ox: " + ox + " oy: " + oy + " cx: " + c.getPositionX() + " cy: " + c.getPositionY());

					}
				}
				for (circle c : oldCircles) {
					delete(c.getID());
				}
				for (circle c : newCircles) {
					addCircle(c);
				}


//				for(Triangle triangle : triangles){
//					if(triangle.getPositionX() == fromX && triangle.getPositionY() == fromY){
//						triangle.setPositionX(x);
//						triangle.setPositionY(y);
//						System.out.println("moved triangle");
//					}
//				}

				
				Iterator<Square> squIte = squares.iterator();
				ArrayList<Square> newSquares = new ArrayList<>();
				ArrayList<Square> oldSquares = new ArrayList<>();
				
				while (squIte.hasNext()) {
					Square s = squIte.next();
					if (s.getPositionX() == fromX && s.getPositionY() == fromY) {
						int ox = s.getPositionX();
						int oy = s.getPositionY();
						
						Square s1 = new Square(x * 25, y * 25, s.getWidth(), s.getHeight(), s.getID(), x, y);
//						delete(s.getID());
//						addSquare(s1);
//						squIte.remove();
						oldSquares.add(s);
						newSquares.add(s1);
						
						System.out.println("ox: " + ox + " oy: " + oy + 
								" cx: " + s.getPositionX() + " cy: " + s.getPositionY());
						
						
					}
				}
				for (Square s : oldSquares) {
					delete(s.getID());
				}
				for (Square s : newSquares) {
					addSquare(s);
				}
			}
			else {
				System.out.println("could not move gizmo, "
						+ "position x: " + x + " y:" + y + " is not empty");
			}
		}
		else {
			System.out.println("could not move gizmo, "
					+ "position x: " + x + " y:" + y + " is not a gizmo");
		}
	}

	public void notifyObs() {
		this.setChanged();
		this.notifyObservers();
	}

	public boolean getBuildMode() {
		return buildMode;
	}

	public void setBuildMode(boolean c) {
		buildMode = c;
	}
	
	public void clearBoard(){
		b = null;
		absorber = null;
		squares.clear();
		circles.clear();
		triangles.clear();
		this.setChanged();
		this.notifyObservers();
	}
	}

	

