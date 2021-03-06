package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

import physics.*;

public class Absorber extends Observable  {
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	ArrayList<LineSegment> ls;
	ArrayList<Circle> cs;
	private boolean absorbed;
	private String type;
	private String ID;
	private String connectedID;
	private Color colour;
	
	public Absorber(int x, int y, int w, int h, String id) {
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		ls = new ArrayList();
		cs = new ArrayList();
		absorbed = false; 
		ID = id;
		colour = new Color(152, 2, 252);
		
		ls.add(new LineSegment(x, y + h, x + w, y + h));	//bottom
		ls.add(new LineSegment(x, y, x, y + h));			//left
		ls.add(new LineSegment(x, y, x + w, y));			//top
		ls.add(new LineSegment(x + w, y, x + w, y + h));	//right
		
		cs.add(new Circle(x, y ,0));			//top left
		cs.add(new Circle(x + w, y, 0));		//top right
		cs.add(new Circle(x, y + h, 0));		//bottom left
		cs.add(new Circle(x + w, y + h, 0));	//bottom right
		type = "Absorber";
	}

	public ArrayList<LineSegment> getLineSeg() {
		return ls;
	}
	
	public ArrayList<Circle> getCircles() {
		return cs;
	}

	public Color getColour() { return colour; }

	public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight(){
		return height;
	}	
	
	public Vect absorbVelo(){
		return new Vect(0, -400);
	}
	
	public Vect absorbCoor(Ball b){
		double x = (this.getWidth() - b.getRadius()) + this.getX();
		double y = this.getY() + b.getRadius();
		return new Vect(x, y);		
	}
	
	public void releaseBall(Ball b){
		if (this.getAbsorbed()){
			b.setVelo(this.absorbVelo());
			b.setExactY(this.getY() - b.getRadius());
			this.setChanged();
			this.notifyObservers();
			this.setAbsorbed(false);
		}
		
	}
	
	public boolean getAbsorbed(){
		return absorbed;
	}
	
	public void setAbsorbed(boolean a){
		absorbed = a;
	}
	
	public String getID(){
		return ID;
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

	public int getPositionX(){
		return xpos/25;
	}
	
	public int getPositionY(){
		return ypos/25;
	}
	
	public int getEndPositionX(){
		return (xpos + width)/25;
	}
	
	public int getEndPositionY(){
		return (ypos + height)/25;
	}

}


