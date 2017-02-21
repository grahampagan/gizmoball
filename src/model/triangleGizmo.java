package model;

public class triangleGizmo implements gizmo{
	
	String id;
	Triangle triangle;
	int xChord;
	int yChord;
	String type;
	
	public triangleGizmo(Triangle t, int x, int y, String name){
		triangle = t;
		xChord = x;
		yChord = y;
		id = name;
		type = "Triangle";
		
	}
	
	public Triangle getTriangle(){
		return triangle;
	}
	
	public int getX(){
		return xChord;
	}
	
	public int getY(){
		return yChord;
	}
	
	public String getID(){
		return id;
	}

	public String getType(){
		return type;
	}
	
}
