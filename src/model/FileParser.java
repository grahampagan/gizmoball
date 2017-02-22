package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import physics.Circle;

public class FileParser {
	board Board;
	
	public FileParser(){
		Board=new board();
	}
	
	public board run() throws IOException{
		FileReader input = new FileReader("myFile");
		BufferedReader bufread = new BufferedReader(input);
		String myline = null;
		
		while((myline = bufread.readLine()) != null){
			
			String[] array1 = myline.split("\\s+");
		
			switch(array1[0]){
			case "Absorber":
				if(Board.containsName(array1[1])==true){
					System.out.println("Tried to add a gizmo with a duplicate name");
				}else if(Board.getAtPosition(Integer.parseInt(array1[2]),Integer.parseInt(array1[3]))!=null){
					System.out.println("tried to add a gizmo to a Board position with a space already occupied");
				}else{
					int x = Integer.parseInt(array1[2]);	//converts the x array position to the co-ordinates
					int y = Integer.parseInt(array1[3]);	//converts the y array position to the co-ordinates
					Absorber a = new Absorber(x*25, y*25, 25, 25);	//creates a new square object for the underlying gizmo
					Board.addGizmo(new absorberGizmo(a,x,y,array1[1]),x,y);	//adds a new gizmo (creating it with the label) and adding it to the board
				}

				;
				
			case "Ball":
				if(Board.containsName(array1[1])==true){
					System.out.println("Tried to add a ball with a duplicate name");
				}else if(Board.getAtPosition(Integer.parseInt(array1[2]),Integer.parseInt(array1[3]))!=null){
					System.out.println("tried to add a ball to a Board position with a space already occupied");
				}else{
					Board.addBall(new Ball(Double.parseDouble(array1[2]),Double.parseDouble(array1[3]),Double.parseDouble(array1[4]),Double.parseDouble(array1[5]), array1[1]));
				}

				;
				
			case "Rotate":
				if(Board.containsName(array1[1])==true){
					Board.rotate(array1[1]);
				}else{
					System.out.println("tried to rotate a gizmo which doesn't exist");
				}
		
			case "Delete":
				if(Board.containsName(array1[1])==true){
					Board.delete(array1[1]);
				}
				;
			
			case "Move":
				if(Board.existsAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3]))==true){
					System.out.print("tried to move to a position that already exists");
				}else{
					Board.move(array1[1],Integer.parseInt(array1[2]),Integer.parseInt(array1[3]));
				}
				;
				
			case "Connect":
				;
				
			case "KeyConnect":
				;
			
			case "Gravity":
				;
				
			case "Friction":
				;
				
			case "Square":
				if(Board.containsName(array1[1])==true){
					System.out.println("Tried to add a gizmo with a duplicate name");
				}else if(Board.getAtPosition(Integer.parseInt(array1[2]),Integer.parseInt(array1[3]))!=null){
					System.out.println("tried to add a gizmo to a Board position with a space already occupied");
				}else{
					int x = Integer.parseInt(array1[2]);	//converts the x array position to the co-ordinates
					int y = Integer.parseInt(array1[3]);	//converts the y array position to the co-ordinates
					Square a = new Square(x*25, y*25, 25, 25);	//creates a new square object for the underlying gizmo
					Board.addGizmo(new squareGizmo(a,x,y,array1[1]),x,y);	//adds a new gizmo (creating it with the label) and adding it to the board
					System.out.println("added gizmo at " + x + " " + y);
				}
				;	
			
			case "Circle":
				if(Board.containsName(array1[1])==true){
					System.out.println("Tried to add a gizmo with a duplicate name");
				}else if(Board.getAtPosition(Integer.parseInt(array1[2]),Integer.parseInt(array1[3]))!=null){
					System.out.println("tried to add a gizmo to a Board position with a space already occupied");
				}else{
					int x = Integer.parseInt(array1[2]);	//converts the x array position to the co-ordinates
					int y = Integer.parseInt(array1[3]);	//converts the y array position to the co-ordinates
					Circle a = new Circle((x*25)-15, (y*25)-12.5, 12.5);	//creates a new circle object for the underlying gizmo
					Board.addGizmo(new circleGizmo(a,x,y,array1[1]),x,y);	//adds a new gizmo (creating it with the label) and adding it to the board
					System.out.println("added gizmo at " + x + " " + y);

				}

				;
			
			case "Triangle":
				if(Board.containsName(array1[1])==true){
					System.out.println("Tried to add a gizmo with a duplicate name");
				}else if(Board.atPosition(Integer.parseInt(array1[2]),Integer.parseInt(array1[3]))==true){
					System.out.println("tried to add a gizmo to a Board position with a space already occupied");
				}else{
					int x = Integer.parseInt(array1[2]);	//converts the x array position to the co-ordinates
					int y = Integer.parseInt(array1[3]);	//converts the y array position to the co-ordinates
					Triangle a = new Triangle(x*25, y*25, (x*25)+25, y*25, x*25, (y*25)+25);	//creates a new triangle object for the underlying gizmo
					Board.addGizmo(new triangleGizmo(a,x,y,array1[1]),x,y);	//adds a new gizmo (creating it with the label) and adding it to the Board
					System.out.println("added triangle gizmo at " + x + " " + y);
				}

				;
			
			case "RightFlipper":
				;
				
			case "LeftFlipper":
				;
			
			default:
				;
			
			}
			}
		
		return Board;
		}
	}

