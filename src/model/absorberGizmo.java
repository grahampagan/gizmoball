package model;


public class absorberGizmo implements gizmo{
	
	Absorber absorber ;
	int xChord;
	int yChord;
	String id;
	
	public absorberGizmo(Absorber a, int x, int y, String name){
		absorber = a;
		xChord = x;
		yChord = y;
		id = name;
		
	}
	
	public Absorber getAbsorber(){
		return absorber;
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
