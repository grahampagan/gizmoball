package model;

import java.util.ArrayList;

public class board {

	static gizmo[][] board = new gizmo[19][19];
	static Ball b;
	
	public board(){
		b=null;
		for(int x = 0; x<19; x++){
			for(int y = 0; y<19; y++){
				board[x][y]=null;
			}
		}
	}
	
	public gizmo[][] getBoard(){
		return board;
	}
	
	public static gizmo getAtPosition(int x, int y){
		if((x>19 || y>19) || (x<0 || y<0)){
			return null;
		}else{
			return board[x][y];
		}
	}

	
	public static void addGizmo(gizmo g, int x, int y){
		if(getAtPosition(x,y) == null){
			
		}else{
			board[x][y] = g;
		}
	}
	
	public boolean removeGizmo(int x, int y){
		
		if(board[x][y] == null){
			return true;
		}else{
			board[x][y]=null;
			return true;
		}
	}
	
	public static boolean containsName(String s ){
		for(int x=0;x<19;x++){
			for(int y=0;y<19;y++){
				if(board[x][y].getID().equals(s)==true){
					return true;
				}

			}
		}
		return false;
	}
	
	public static gizmo getGizmo(String id){
		
		for(int x=0;x<19;x++){
			for(int y =0;y<19;y++){
				if(board[x][y].getID().equals(id)){
					return board[x][y];
				}
			}
		}

		return null;
	}
	
	public ArrayList<gizmo> getGizmos(){
		ArrayList<gizmo> g = new ArrayList();
		
		for(int x=0;x<19;x++){
			for(int y =0;y<19;y++){
				if(board[x][y]!=null){
					g.add(board[x][y]);
				}
			}
		}
		
		return g;
	}
	
	public static boolean rotate(String id){
	
		if(containsName(id)==false){
			System.out.println("tried to rotate a gizmo that doesn't exist :/");
			return false;
		}else if(getGizmo(id).getType().equals("Triangle")){
			triangleGizmo t = (triangleGizmo)getGizmo(id);
			Triangle triangle = t.getTriangle();
			triangle.rotate();
			return true;
		}else{
			return false;
		}
	}
	
	public static void delete(String id){
		if(containsName(id)==true){
			for(int x=0;x<19;x++){
				for(int y =0;y<19;y++){
					if(board[x][y].getID().equals(id)){
						board[x][y]=null;
					}
				}
			}

		}
	}
	
	public static void addBall(Ball ball){
		b = ball;
	}
	
	public static boolean existsAtPosition(int x, int y){
		if((x>19 || y>19) || (x<0 || y<0)){
			return false;
		}else if(board[x][y]!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public static void move(String id, int x, int y){
		
		board[x][y]=getGizmo(id);
	}

}	

