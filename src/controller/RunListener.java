package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.Timer;

import model.FileParser;
import model.Model;
import model.Square;
import model.Triangle;
import model.circle;
import model.gizmo;
import physics.Circle;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private FileParser f;
	private JComboBox<String> gizmo;
	private List<gizmo> giz;
	private Square s;
	private circle c;
	private Triangle t;

	public RunListener(Model m, JComboBox<String> g) {
		model = m;
		gizmo = g;
		timer = new Timer(50, this);
		f = new FileParser(model);
		giz = new ArrayList<>();
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
//				System.out.println("not yet implemented");
				try {
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Reload":
//				System.out.println("not yet implemented");
				try { 
					model.clearBoard();
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Clear board":
				model.clearBoard();
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
					circle c = new circle(x + 12.5, y + 12.5, 12.5, "SOME ID", xPos, yPos);
					
					model.addCircle(c);
					System.out.println("added circle at " + xPos + " " + yPos);		
					break;
					
				case "Square":
					Square s = new Square(x, y, 25, 25, "SOME ID", xPos, yPos);
					
					model.addSquare(s);
					System.out.println("added square at " + xPos + " " + yPos);
					break;
					
				case "Triangle":
					Triangle t = new Triangle(x, y, x2, y, x, y3, "SOME ID", xPos, yPos);
					
					model.addTriangle(t);
					System.out.println("added triangle at " + xPos + " " + yPos);
					break;
				}
			}
	}
}
