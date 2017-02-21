package model;

public class board {

	static gizmo[][] board = new gizmo[19][19];
	
	public board(){
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
}	

