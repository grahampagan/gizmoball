package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {
	board Board;
	
	public FileParser(board b){
		Board = b;
	}
	
	public void run() throws IOException{
		FileReader input = new FileReader("myFile");
		BufferedReader bufread = new BufferedReader(input);
		String myline = null;
		
		while( (myline = bufread.readLine()) != null){
			String[] array1 = myline.split("\\s+");
		
			switch(array1[0]){
			case "Absorber":
				;
				
			case "Ball":
				;
				
			case "Rotate":
				;
		
			case "Delete":
				;
			
			case "Move":
				;
				
			case "Connect":
				;
				
			case "KeyConnect":
				;
			
			case "Gravity":
				;
				
			case "Friction":
				;
			
			default:
				;
			
			}
			}
		}
	}

