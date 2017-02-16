package model;

public class board {

	gizmo[][] board = new gizmo[19][19];
	
	public board(){
		for(int x = 0; x<20; x++){
			for(int y = 0; y<20; y++){
				board[x][y]=null;
			}
		}
	}
	
	public gizmo[][] getBoard(){
		return board;
	}
	
	public gizmo getAtPosition(int x, int y){
		if(x>19 || y>19){
			return null;
		}else{
			return board[x][y];
		}
	}

	
	public void addGizmo(gizmo g, int x, int y){
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
}	

