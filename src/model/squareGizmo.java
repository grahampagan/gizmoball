package model;

public class squareGizmo implements gizmo{
	
	Square square;
	int xChord;
	int yChord;
	
	public squareGizmo(Square s, int x, int y){
		square = s;
		xChord = x;
		yChord = y;
		
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
}
