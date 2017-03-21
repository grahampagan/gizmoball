package model;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.JFileChooser;

import physics.*;

public class Model extends Observable {
	
	private Ball b; 
	private ArrayList<Line> lines;
	private ArrayList<circle> circles;
	private ArrayList<Absorber> absorbers;
	private Walls walls; 
	private ArrayList<Square> squares;
	private ArrayList<Triangle> triangles;
	private double gravity;
	private double mu, mu2;
	private Square gridHighlight;
	private boolean buildMode;
	private Color gridHighlightColour;
	private Color boardBackgroundColour;

	//private ArrayList<Flipper> flippers;
	private ArrayList<flipper2> flippers2;


	//**********************************
	//used to be a throws IOException here
	//but had to remove to do testing 
	//will replace later on
	public Model(){
//		b = new Ball(335, 25, 100, 100, "ball1");

		walls = new Walls(0, 0, 500, 500);
		lines = new ArrayList<Line>();
		circles = new ArrayList<circle>();
		squares = new ArrayList<Square>();
		triangles = new ArrayList<Triangle>();
		absorbers = new ArrayList<Absorber>();
		gridHighlight = new Square(0, 0, 25, 25, "GH", 0, 0);
//		gravity = 25;							//These are the values that 
//		mu = 0.025;								//the game should be played at, but it doesn't work well.
//		mu2 = 0.025;	
		gravity = 0;
		mu = 0;
		mu2 = 0;
		buildMode = false;
		gridHighlightColour = Color.RED;
		boardBackgroundColour = Color.GRAY;
		
//		flippers=new ArrayList<Flipper>();
		flippers2 = new ArrayList<flipper2>();
		
		flippers2.add(new flipper2("id",1,1));

		
	}

	public Color getGridHighlightColour() { return gridHighlightColour; }

	public Color getBoardBackgroundColour() { return boardBackgroundColour; }
	
	public Ball moveBallForTime(Ball ball, double time){
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
				b.applyGravity(gravity);
				b.applyFriction(mu, mu2);
			} else {
				// We've got a collision in tuc
				b = moveBallForTime(b, tuc);
				// Post collision velocity ...
				b.setVelo(cd.getVelo());
//				b.applyFriction(mu, mu2, tuc);
//				b.applyGravity(gravity, tuc);
//				b.applyGravity(gravity);
//				b.applyFriction(mu, mu2);
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
						if(circle.isConnected()){
							triggerCircle(circle.getConnected());
						}
					}
				}
				
				//time to collide with triangles
				for(Triangle triangle : triangles){
					for (LineSegment line : triangle.getLineSeg()) {
						time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectWall(line, b.getVelo(), 1.0);
							if(triangle.isConnected()){
								triggerTriangle(triangle.getConnected());
							}
					}
					for (Circle c : triangle.getCircles()){
						time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
							if(triangle.isConnected()){
								triggerTriangle(triangle.getConnected());
							}
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
						if(s.isConnected()){
							triggerSquare(s.getConnected());
						}

					}
				}
				for (Circle c : s.getCircles()){
					time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
					if (time < shortestTime) {
						shortestTime = time;
						newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
						if(s.isConnected()){
							triggerSquare(s.getConnected());
						}
					}
				}
				}
				
				//flipper collisions
				for(flipper2 f : flippers2){
					
					Square s = f.getSquare();
					for (LineSegment line : s.getLineSeg()) {
						time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
						if (time < shortestTime) {
							System.out.println("collision flippers");
							shortestTime = time;
							newVelo = Geometry.reflectWall(line, b.getVelo(), 1.0);
							if(s.isConnected()){
								triggerSquare(s.getConnected());
							}

						}
					}
					for (Circle c : s.getCircles()){
						time = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
						if (time < shortestTime) {
							shortestTime = time;
							newVelo = Geometry.reflectCircle(c.getCenter(), b.getCenter(), b.getVelo());
							if(s.isConnected()){
								triggerSquare(s.getConnected());
							}
						}
					}


				}
				// Absorber collisions 
				for (Absorber absorber: absorbers) {
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

	public void setBall(Ball b) {this.b = b; }
	
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
		if (l.getX()<=18 && l.getY()<=18){
		lines.add(l);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}
	
	public void addCircle(circle c){
		if (c.getPositionX()<=19 && c.getPositionY()<=19){
		circles.add(c);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}
	
	public void addAbsorber(Absorber a){
		if (a.getPositionX()<=19 && a.getPositionY()<=19){
	
		absorbers.add(a);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}
	
	public void addSquare(Square s) {
		if (s.getPositionX()<=19 && s.getPositionY()<=19){
		squares.add(s);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public ArrayList<Absorber> getAbsorbers(){
		return absorbers;
	}
	
	public void addTriangle(Triangle t){
		if (t.getPositionX()<=19 && t.getPositionY()<=19){
		triangles.add(t);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}
	
	public ArrayList<Triangle> getTriangles(){
		return triangles;
	}
	
	
//	public void addFlipper(Flipper f){
//		if (f.getPositionX()<=19 && f.getPositionY()<=19){
//		flippers.add(f);
//		this.setChanged();
//		this.notifyObservers();
//		}
//		else{
//			System.out.println("Object out of bounds");
//		}
//	}
	
	public void addFlipper2(flipper2 f){
		if (f.getPositionX()<=19 && f.getPositionY()<=19){
		flippers2.add(f);
		this.setChanged();
		this.notifyObservers();
		}
		else{
			System.out.println("Object out of bounds");
		}
	}

	
//	public ArrayList<Flipper> getFLippers(){
//		return flippers;
//	}
//	
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
		
		for(flipper2 f: flippers2){
			if(f.getID().equals(id)){
				return true;
			}
		}
		
		for(flipper2 f : flippers2){ 
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
		
//		for (Flipper f : flippers) {
//			if (Flipper.getPositionX()==parseInt && Flipper.getPositionY()==parseInt2){
//				return true;
//			}
//		}
		
		for(Absorber a: absorbers){
			int x = a.getX()/25;
			int y = a.getY()/25;
			int xEnd = x + a.getWidth()/25;
			int yEnd = y + a.getHeight()/25;
			
			if(x <= parseInt && xEnd> parseInt && y<= parseInt2 && yEnd> parseInt2){
				return true;
			}
		}
		
		if(b!= null){
			if(b.getPositionX() == parseInt && b.getPositionY() == parseInt2){
				return true;
			}
		}

		return false;

	}
	
	public boolean hasInArea(int x, int y, int xEnd, int yEnd){
		
		int xPos = x/25;
		int yPos = y/25;
		int xEndPos = xEnd/25;
		int yEndPos = yEnd/25;
		
		for (circle circle : circles) {
			if (circle.getPositionX() >= xPos && circle.getPositionX() < xEndPos && circle.getPositionY() >= yPos && circle.getPositionY() < yEndPos){
				return true;
			}
		}
		
		for(Triangle triangle : triangles){
			if(triangle.getPositionX() >= xPos && triangle.getPositionX() < xEndPos && triangle.getPositionY() >= yPos && triangle.getPositionY() < yEndPos){
				return true;
			}
		}
		
		for(Square s : squares){
			if(s.getPositionX() >= xPos && s.getPositionX() < xEndPos && s.getPositionY() >= yPos && s.getPositionY() < yEndPos){
				return true;
			}
		}
		
		for(Absorber a : absorbers){
			int endX = a.getEndPositionX();
			int endY = a.getEndPositionY();

			for(int currentX = a.getPositionX(); currentX < endX; currentX++){
				for(int currentY = a.getPositionY(); currentY < endY; currentY++)
					if(currentX >= xPos && currentX < xEndPos && currentY >= yPos && currentY < yEndPos){
						return true;
				}
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
		
		Iterator<Absorber> absorberIter = absorbers.iterator();
		while(absorberIter.hasNext()){
			Absorber a = absorberIter.next();
		      if(a.getID().equals(id)) {
		        absorberIter.remove();
		      }
		}
		
		if(b!=null){
			if(b.getID().equals(id)){
				System.out.println("Ball deleted.");
				b = null;
				this.setChanged();
				this.notifyObservers();
			}
		}

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
			
			for (Absorber a : absorbers) {
				int xStart = a.getX()/25;
				int yStart = a.getY()/25;
				int xEnd = x + a.getWidth()/25;
				int yEnd = y + a.getHeight()/25;
				
				if(xStart <= x && xEnd> x && yStart<= y && yEnd> y){
					r = a.getID();
				}
			}
			
			if(b!=null){
				if(b.getPositionX() == x && b.getPositionY() == y){
					r = b.getID();
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
				
				Iterator<circle> cirIte = circles.iterator();
				ArrayList<circle> newCircles = new ArrayList<>();
				ArrayList<circle> oldCircles = new ArrayList<>();

				while (cirIte.hasNext()) {
					circle c = cirIte.next();
					if (c.getPositionX() == fromX && c.getPositionY() == fromY) {
						int ox = c.getPositionX();
						int oy = c.getPositionY();

						circle c1 = new circle((x * 25) + 12.5, (y * 25) + 12.5, 12.5, c.getID(), x, y);

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
				
				Iterator<Triangle> triIte = triangles.iterator();
				ArrayList<Triangle> newTriangles = new ArrayList<>();
				ArrayList<Triangle> oldTriangles = new ArrayList<>();
				
				while (triIte.hasNext()) {
					Triangle t = triIte.next();
					if (t.getPositionX() == fromX && t.getPositionY() == fromY) {
						int ox = t.getPositionX();
						int oy = t.getPositionY();
						
						Triangle t1 = new Triangle(t.getx1() + ((x - fromX) * 25), 
								t.gety1() + ((y - fromY) * 25), 
								t.getx2() + ((x - fromX) * 25), 
								t.gety2() + ((y - fromY) * 25), 
								t.getx3() + ((x - fromX) * 25), 
								t.gety3() + ((y - fromY) * 25), 
								t.getID(), 
								x, 
								y);

						oldTriangles.add(t);
						newTriangles.add(t1);
						
						System.out.println("ox: " + ox + " oy: " + oy + 
								" cx: " + t.getPositionX() + " cy: " + t.getPositionY());
						
						
					}
				}
				for (Triangle t : oldTriangles) {
					delete(t.getID());
				}
				for (Triangle t : newTriangles) {
					addTriangle(t);
				}

				
				Iterator<Square> squIte = squares.iterator();
				ArrayList<Square> newSquares = new ArrayList<>();
				ArrayList<Square> oldSquares = new ArrayList<>();
				
				while (squIte.hasNext()) {
					Square s = squIte.next();
					if (s.getPositionX() == fromX && s.getPositionY() == fromY) {
						int ox = s.getPositionX();
						int oy = s.getPositionY();
						
						Square s1 = new Square(x * 25, y * 25, s.getWidth(), s.getHeight(), s.getID(), x, y);

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
		absorbers.clear();
		squares.clear();
		circles.clear();
		triangles.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	public boolean triggerTriangle(String id){
		for(Triangle triangle : triangles){
			if(triangle.getID().equals(id)){
				triangle.trigger();
				return true;
			}
		}
		return false;
	}
	
	public boolean triggerSquare(String id){
		for(Square square: squares){
			if(square.getID().equals(id)){
				square.trigger();
				return true;
			}
		}
		return false;
	}
	
	public boolean triggerCircle(String id){
		for(circle circle: circles){
			if(circle.getID().equals(id)){
				circle.trigger();
				return true;
			}
		}
		return false;
	}
	
	public void saveToFile(){
		StringBuilder output = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		
		if(b!=null){
			output.append("Ball " + b.getID() + " " + b.getExactX() + " " + b.getExactY() + " " + b.getVelo().getX() + " " + b.getVelo().getY() + newLine);
		}
		
		for(Square s : squares){
			output.append("Square " + s.getID() + " " + s.getPositionX() + " " + s.getPositionY() + newLine);
		}
		
		for(circle c : circles){
			output.append("Circle " + c.getID() + " " + c.getPositionX() + " " + c.getPositionY() + newLine);
		}
		
		for(Triangle t : triangles){
			output.append("Triangle " + t.getID() + " " + t.getPositionX() + " " + t.getPositionY() + newLine);
			if(t.getRotations() > 0){
				for(int i = 1; i <= t.getRotations(); i++){
					output.append("Rotate " + t.getID() + newLine);
				}
			}
		}
		
		for(Absorber a : absorbers){
			output.append("Absorber " + a.getID() + " " + a.getPositionX() + " " + a.getPositionY() + " " + a.getEndPositionX() + " " + a.getEndPositionY() + newLine);
		}
		
		String userdir = System.getProperty("user.dir");
	    JFileChooser chooser = new JFileChooser(userdir +"\\SavedModels");
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	    	try (FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {
	    	    fw.write(output.toString());
	    	} catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	public ArrayList<flipper2> getFlippers2() {
		// TODO Auto-generated method stub
		return flippers2;
	}
	
	public void getKeyPress(){	
		String key;
		System.out.println("Please enter a key to connect the gizmo to");
		
		Scanner sc = new Scanner(System.in);
		key = sc.nextLine();
		System.out.println(key);
		sc.close();
	}

	}
