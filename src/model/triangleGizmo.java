package model;

public class triangleGizmo implements gizmo{
	
	Triangle triangle;
	int xChord;
	int yChord;
	
	public triangleGizmo(Triangle t, int x, int y){
		triangle = t;
		xChord = x;
		yChord = y;
		
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
}
