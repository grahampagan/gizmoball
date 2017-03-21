package model;

import java.io.*;

import physics.Circle;

import javax.swing.*;

public class FileParser {
    Model Board;
    private boolean fileLoaded = false;
    private FileReader loadedFile = new FileReader("myFile");

    public FileParser(Model m) throws FileNotFoundException {
        Board = m;
    }

    public void setFileLoaded(boolean x) {
        fileLoaded = x;
    }

    public Model run() throws IOException {
//        FileReader input = new FileReader("myFile");
        String userdir = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(userdir + "\\SavedModels");
//        BufferedReader bufread = new BufferedReader(input);
        BufferedReader bufread = null;
        if (fileLoaded) {
        	Board.clearBoard();
            System.out.println("File reloaded");
            bufread = new BufferedReader(loadedFile);
        } else {
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	Board.clearBoard();
                File i = chooser.getSelectedFile();
                FileReader input2 = new FileReader(i);
                bufread = new BufferedReader(input2);
                loadedFile = new FileReader(i);
            } else if (returnVal == JFileChooser.CANCEL_OPTION){}
        }

            String myline = null;

            while ((myline = bufread.readLine()) != null) {

                String[] array1 = myline.split("\\s+");

                switch (array1[0]) {
                    case "Absorber":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            String id = array1[1];
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            int endX = Integer.parseInt(array1[4]);
                            int endY = Integer.parseInt(array1[5]);
                            int w = endX - x;
                            int h = endY - y;
                            Absorber a = new Absorber(x * 25, y * 25, w * 25, h * 25, id);    //creates a new square object for the underlying gizmo
                            Board.addAbsorber(a);    //adds a new gizmo (creating it with the label) and adding it to the board
                        }

                        break;

					case "Ball":
						if (Board.containsName(array1[1]) == true) {
							System.out.println("Tried to add a ball with a duplicate name");
						} else if (Board.hasAtPosition((int) Double.parseDouble(array1[2]),
								(int) Double.parseDouble(array1[3])) == true) {
							System.out.println("tried to add a ball to a Board position with a space already occupied");
						} else {
							String id = array1[1];
							double bx = Double.parseDouble(array1[2]);
							double by = Double.parseDouble(array1[3]);
							double xv = Double.parseDouble(array1[4]);
							double yv = Double.parseDouble(array1[5]);
		
							Ball b = new Ball(bx, by, xv, yv, id);
							Board.setBall(b);
						}
						
						break;

                    case "Rotate":
                        if (Board.containsName(array1[1]) == true) {
                            Board.rotate(array1[1]);
                            System.out.println("Rotated triangle: " + array1[1]);
                        } else {
                            System.out.println("tried to rotate a gizmo which doesn't exist");
                        }
                        break;

                    case "Delete":
                        if (Board.containsName(array1[1]) == true) {
                            Board.delete(array1[1]);
                        } else {
                            System.out.println("Tried to delete a gizmo that doesn't exist.");
                        }
                        ;

                    case "Move":
//				if(Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3]))==true){
//					System.out.print("tried to move to a position that already exists");
//				}else{
//					Board.move(array1[1],Integer.parseInt(array1[2]),Integer.parseInt(array1[3]));
//				}
                        ;

                    case "Connect":
                        ;

                    case "KeyConnect":
                        ;

                    case "Gravity":
                    	double grav = Double.parseDouble(array1[1]);
                    	Board.setGravity(grav);
                    	System.out.println("Gravity set to: " + grav);
                        break;

                    case "Friction":
                        double xfr = Double.parseDouble(array1[1]);
                        double yfr = Double.parseDouble(array1[2]);
                        Board.setMu(xfr);
                        Board.setMu2(yfr);
                        System.out.println("Friction set to Mu: " + xfr + " Mu2: " + yfr);
                    	break;

                    case "Square":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            Square a = new Square(x * 25, y * 25, 25, 25, array1[1], x, y);    //creates a new square object for the underlying gizmo
                            Board.addSquare(a);    //adds a new gizmo (creating it with the label) and adding it to the board
                            System.out.println("added gizmo at " + x + " " + y);
                        }
                        break;

                    case "Circle":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            Circle a = new Circle((x * 25) + 12.5, (y * 25) + 12.5, 12.5);    //creates a new circle object for the underlying gizmo
                            circle c = new circle((x * 25) + 12.5, (y * 25) + 12.5, 12.5, array1[1], x, y);
                            Board.addCircle(c);    //adds a new gizmo (creating it with the label) and adding it to the board
                            System.out.println("added gizmo at " + x + " " + y);
                        }
                        break;

                    case "Triangle":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            Triangle a = new Triangle(x * 25, y * 25, (x * 25) + 25, y * 25, x * 25, (y * 25) + 25, array1[1], x, y);    //creates a new triangle object for the underlying gizmo
                            Board.addTriangle(a);    //adds a new gizmo (creating it with the label) and adding it to the Board
                            System.out.println("added triangle gizmo at " + x + " " + y);
                        }

                        break;

                    case "RightFlipper":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            Board.addFlipper2(new flipper2(array1[1],x,y));    //adds a new gizmo (creating it with the label) and adding it to the board
                            System.out.println("added gizmo at " + x + " " + y);
                        }

                        ;

                    case "LeftFlipper":
                        if (Board.containsName(array1[1]) == true) {
                            System.out.println("Tried to add a gizmo with a duplicate name");
                        } else if (Board.hasAtPosition(Integer.parseInt(array1[2]), Integer.parseInt(array1[3])) == true) {
                            System.out.println("tried to add a gizmo to a Board position with a space already occupied");
                        } else {
                            int x = Integer.parseInt(array1[2]);    //converts the x array position to the co-ordinates
                            int y = Integer.parseInt(array1[3]);    //converts the y array position to the co-ordinates
                            Board.addFlipper2(new flipper2(array1[1],x,y));    //adds a new gizmo (creating it with the label) and adding it to the board
                            System.out.println("added flipper gizmo at " + x + " " + y);
                        }

                        ;

                    default:
                        ;

                }
            }

			return Board;
        }
    }