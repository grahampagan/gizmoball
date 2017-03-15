package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.FileParser;
import model.Model;
import model.Square;
import model.Triangle;
import model.circle;
import physics.Circle;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private FileParser f;
	private JComboBox<String> gizmo;
	private JTextField gravity;
	private JTextField xFric;
	private JTextField yFric;

	public RunListener(Model m, JComboBox<String> g, JTextField grav, JTextField xF, JTextField yF) {
		model = m;
		gizmo = g;
		gravity = grav;
		xFric = xF;
		yFric = yF;
		timer = new Timer(50, this);
		f = new FileParser(model);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
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
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Load Model":
				try {
					f.run();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case "Reload":
				try { 
					model.clearBoard();
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Reload Model":
				model.clearBoard();
				try {
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
						String id = "C" + xPos + yPos;
						circle c = new circle(x + 12.5, y + 12.5, 12.5, id, xPos, yPos);

						model.addCircle(c);
						System.out.println("added circle at " + xPos + " " + yPos);
					}
					else {
						System.out.println("could not add circle at " + xPos + " " + yPos);
					}
					break;
					
				case "Square":
					if (!model.hasAtPosition(xPos, yPos)) {
						String id2 = "S" + xPos + yPos;
						Square s = new Square(x, y, 25, 25, id2, xPos, yPos);

						model.addSquare(s);
						System.out.println("added square at " + xPos + " " + yPos);
					} else {
						System.out.println("could not add square at " + xPos + " " + yPos);
					}
					break;
					
				case "Triangle":
					if (!model.hasAtPosition(xPos, yPos)) {
						String id3 = "T" + xPos + yPos;
						Triangle t = new Triangle(x, y, x2, y, x, y3, id3, xPos, yPos);

						model.addTriangle(t);
						System.out.println("added triangle at " + xPos + " " + yPos);
					} else {
						System.out.println("could not add triangle at " + xPos + " " + yPos);
					}
					break;					
				}
			
			case "Apply Gravity: ":
				double grav = Double.parseDouble(gravity.getText());				
				model.setGravity(grav);
				break;
				
			case "Apply Friction: ":
				double xFr = Double.parseDouble(xFric.getText());
				double yFr = Double.parseDouble(yFric.getText());
				model.setMu(xFr);
				model.setMu2(yFr);
				break;
			}
		
	}
}
