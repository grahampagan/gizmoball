package model;

public class squareGizmo implements gizmo{
	
	String id;
	Square square;
	int xChord;
	int yChord;
	
	public squareGizmo(Square s, int x, int y, String name){
		square = s;
		xChord = x;
		yChord = y;
		id=name;
	}
	
	public Square getSquare(){
		return square;
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

}
