package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public class flipper3 implements KeyListener{

	private int xpos1;	//the top left of the flipper 
	private int ypos1;
	private static int xpos2;	//the top left of the flipper 
	private static int ypos2;
	
	private static int x;
	private static int y;

	private Square rectangle;

	private circle cornerCircle;	//circle for the corner, which is stationary
	private circle movingCircle;	//circle for the moving circle, which traverses a curve throughout its bounding box to a limit
	private String ID;				
	private String connectedID;
	private boolean triggered;
	private boolean flipped;
	
	
	public flipper3(String id, int j, int k){
		xpos1 = j;
		ypos1 = k;
		xpos2 = xpos1+1;
		ypos2 = ypos1+1;
		
		ID = id;
		
		
        rectangle = new Square((xpos1 * 25)+37, ypos1 * 25, 12, 50, id, xpos1,ypos1);    //creates a new square object for the underlying gizmo

        flipped = false;
		
        cornerCircle = new circle(new Circle((xpos1*25)+43,(ypos1*25)+8,5));
        movingCircle = new circle(new Circle((xpos1*25)+43,(ypos1*25)+46,5));

	}
	


	@Override
	public void keyPressed(KeyEvent k) {
		int code=k.getKeyCode();
		if(code== KeyEvent.VK_UP){

//		setRectangle();
		
		if(flipped==true){
			flipped = false;
		}else if(flipped == false){
			flipped = true;
		}
		
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getID() {
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

	public static int getPositionX() {
		// TODO Auto-generated method stub
		return x;
	}

	public static int getPositionY() {
		// TODO Auto-generated method stub
		return y;
	}

	public circle getCornerCircle(){
		return cornerCircle;
	}
	
	public circle getMovingCircle(){
		return movingCircle;
	}
	
	public Square getSquare(){
		return rectangle;
	}

	public int getXPos(){
		return xpos1;
	}
	
	public int getYPos(){
		return ypos1;
	}
	
	public void setRectangle1(){
		if(flipped == false){
	        rectangle = new Square(xpos1*25, ypos1*25, 50, 12, ID, xpos1, ypos1);    //creates a new square object for the underlying gizmo
	        movingCircle = new circle(new Circle((xpos1*25)+7,(ypos1*25)+5,5));
	        flipped = true;
		}
	}
	
	public void setRectangle2(){
		if(flipped == true){
	        rectangle = new Square((xpos1*25)+37, ypos1*25, 12, 50, ID, xpos1,ypos1);    //creates a new square object for the underlying gizmo
	        movingCircle = new circle(new Circle((xpos1*25)+43,(ypos1*25)+46,5));
			flipped = false;

		}
	}

	public boolean chordCheck(int x, int y){

		if((x == xpos1 && y == ypos1) || (x == xpos1+1 && y == ypos1) || (x == xpos1 && y == ypos1+1) || (x == xpos1+1 && y == ypos1+1)){
			return false;
		}else{
			return true;
		}
	}

	

}
