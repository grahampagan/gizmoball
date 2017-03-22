 package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.Absorber;
import model.FileParser;
import model.Model;
import model.Square;
import model.Triangle;
import model.circle;
import model.flipper2;
import model.flipper3;
import physics.Circle;

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private FileParser f;
	private JComboBox<String> gizmo;
	private JTextField gravity;
	private JTextField xFric;
	private JTextField yFric;
	private JFrame frame;
	private JPanel run;
	private JPanel build;

	public RunListener(Model m, JComboBox<String> g, JTextField grav, JTextField xF, JTextField yF, JFrame fr, JPanel rb, JPanel bb) {
		model = m;
		gizmo = g;
		gravity = grav;
		xFric = xF;
		yFric = yF;
		timer = new Timer(50, this);
		frame = fr;
		run = rb;
		build = bb;
		try {
			f = new FileParser(model);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				if(model.getBall()!=null){
					model.getBall().start();
				}
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				model.moveBall();
				break;
			case "Quit":
				System.exit(0);
				break;
			case "Load":
				try {
					f.setFileLoaded(false);
					f.run(false);
					f.setFileLoaded(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Load Model":
				try {
					f.setFileLoaded(false);
					f.run(false);
					f.setFileLoaded(true);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case "Reload":
				try { 
					f.run(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Reload Model":
				try {
					f.run(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Save Model":
				model.saveToFile();
				break;
			case "Clear board":
				model.clearBoard();
				break;
			case "Rotate":
				int xp12 = model.getGridHighlight().getPositionX();
				int yp12 = model.getGridHighlight().getPositionY();

				String s123 = model.getIdOfPosition(xp12, yp12);
				if (s123 != null) {
					model.rotate(s123);
					model.notifyObs();
					System.out.println("gizmo " + s123 + " rotated");
				}
				else {
					System.out.println("could not rotate gizmo");
				}
				break;
			case "Delete":
				int xp1 = model.getGridHighlight().getPositionX();
				int yp1 = model.getGridHighlight().getPositionY();

				String s12 = model.getIdOfPosition(xp1, yp1);
				if (s12 != null) {
					model.delete(s12);
					model.notifyObs();
					System.out.println("gizmo " + s12 + " deleted");
				}
				else {
					System.out.println("could not delete gizmo");
				}
				break;
			case "Add Gizmo: ":
				String g = gizmo.getSelectedItem().toString();
				
				int x = model.getGridHighlight().getX();
				int y = model.getGridHighlight().getY();
				
				int x2 = x + 25;
				int y3 = y + 25;
				
				int xPos = model.getGridHighlight().getPositionX();
				int yPos = model.getGridHighlight().getPositionY();
				
				switch(g){
				
				case "Circle":							
					if (!model.hasAtPosition(xPos, yPos)) {
						String id = "C" + xPos + "x" + yPos;
						if(model.containsName(id)) {
							id = id + "+" + model.getCircles().size();
						}
						circle c = new circle(x + 12.5, y + 12.5, 12.5, id, xPos, yPos);

						model.addCircle(c);
						System.out.println("added circle at " + xPos + " " + yPos);
					}
					else {
						JOptionPane.showMessageDialog(frame,
							    "Could not add circle as a gizmo already exists in this space.");
						System.out.println("could not add circle at " + xPos + " " + yPos);
					}
					break;
					
				case "Square":
					if (!model.hasAtPosition(xPos, yPos)) {
						String id2 = "S" + xPos + "x" + yPos;
						if(model.containsName(id2)) {
							id2 = id2 + "+" + model.getSquares().size();
						}
						Square s = new Square(x, y, 25, 25, id2, xPos, yPos);

						model.addSquare(s);
						System.out.println("added square at " + xPos + " " + yPos);
					} else {
						JOptionPane.showMessageDialog(frame,
							    "Could not add square as a gizmo already exists in this space.");
						System.out.println("could not add square at " + xPos + " " + yPos);
					}
					break;
					
				case "Triangle":
					if (!model.hasAtPosition(xPos, yPos)) {
						String id3 = "T" + xPos + "x" + yPos;
						if(model.containsName(id3)) {
							id3 = id3 + "+" + model.getTriangles().size();
						}
						Triangle t = new Triangle(x, y, x2, y, x, y3, id3, xPos, yPos);

						model.addTriangle(t);
						System.out.println("added triangle at " + xPos + " " + yPos);
					} else {
						JOptionPane.showMessageDialog(frame,
							    "Could not add triangle as a gizmo already exists in this space.");
						System.out.println("could not add triangle at " + xPos + " " + yPos);
					}
									
				}
				break;
			
			case "Apply Gravity: ":
				try {
				double grav = Double.parseDouble(gravity.getText());				
				model.setGravity(grav);
				System.out.println("Gravity set to: " + grav);
				} catch (NumberFormatException gravityNumException){
					JOptionPane.showMessageDialog(frame,
						    "Gravity must be a numeric value.");
				}
				break;
				
			case "Apply Friction: ":
				try{
				double xFr = Double.parseDouble(xFric.getText());
				double yFr = Double.parseDouble(yFric.getText());
				model.setMu(xFr);
				model.setMu2(yFr);
				System.out.println("Friction set to Mu: " + xFr + " Mu2: " + yFr);
				} catch (NumberFormatException fricNumException){
					JOptionPane.showMessageDialog(frame,
						    "Friction must be a numeric value.");
				}
				break;
			case "Build Mode":
				timer.stop();
				run.setVisible(false);
				build.setVisible(true);
				frame.pack();
				model.setBuildMode(true);
				if(model.getBall() != null){
					model.getBall().resetBall();
					for(Absorber a : model.getAbsorbers()){
						a.setAbsorbed(false);
					}
				}
				break;
			case "Run Mode":
				run.setVisible(true);
				build.setVisible(false);
				frame.pack();
				model.setBuildMode(false);
				break;
			case "Key Connect":
				model.getKeyPress();
				break;
				
			case "Connect":
				int x1 = model.getGridHighlight().getPositionX();
				int y1 = model.getGridHighlight().getPositionY();

				String string = model.getIdOfPosition(x1, y1);
				if (string != null) {
					System.out.println("connect "+x1+ " "+y1);
				}
				else {
					System.out.println("could not connect gizmo");
				}
				break;
				
			case "Add Left Flipper":		
				int xposition = model.getGridHighlight().getX();
				int yposition = model.getGridHighlight().getY();

				if (!model.hasAtPosition(xposition, yposition)) {
					String id = "LeftFlipper" + xposition+ "x" + yposition;
					if(model.containsName(id)) {
						id = id + "+" + model.getFlippers2().size();
					}
					flipper2 f = new flipper2(id,xposition,yposition);

					model.addFlipper2(f);
					System.out.println("added leftflipper at " + xposition + " " + yposition);
				}
				else {
					JOptionPane.showMessageDialog(frame,
						    "Could not add flipper as a gizmo already exists in this space.");
					System.out.println("could not add left flipper at " + xposition + " " + yposition);
				}
				break;

				
			case "Add Right Flipper":		
				xposition = model.getGridHighlight().getX();
				yposition = model.getGridHighlight().getY();

				if (!model.hasAtPosition(xposition, yposition)) {
					String id = "LeftFlipper" + xposition+ "x" + yposition;
					if(model.containsName(id)) {
						id = id + "+" + model.getFlippers3().size();
					}
					
					flipper3 f = new flipper3(id,xposition,yposition);

					model.addFlipper3(f);
					System.out.println("added rightflipper at " + xposition + " " + yposition);
				}
				else {
					JOptionPane.showMessageDialog(frame,
						    "Could not add flipper as a gizmo already exists in this space.");
					System.out.println("could not add right flipper at " + xposition + " " + yposition);
				}
				break;


			}
		
	}
}
