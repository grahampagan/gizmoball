package main;

import java.io.IOException;

import javax.swing.UIManager;

import model.Model;
import model.Square;
import model.Absorber;
import model.FileParser;
import model.Line;
import model.Triangle;
import model.squareGizmo;
import view.GUI;
import view.RunGui;
import physics.*; 

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {


	public static void main(String[] args) throws IOException {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();
		FileParser f = new FileParser(model);
//		
//		model = f.run();

		model.setBallSpeed(200, 200);

		// Vertical line at (100,100), width 300
//		model.addLine(new Line(100, 100, 300));
//		model.addLine(new Line(100, 200, 300));
//		model.addLine(new Line(100, 300, 300));
//		model.addLine(new Line(100, 400, 300));
//		model.addSquare(new Square(40, 500, 300,400 ));
		model.addAbsorber(new Absorber(0, 450, 500, 50));
//		model.addSquare(new Square (10, 10, 300, 300));
		model.addCircle(new Circle(480, 10, 5));
//		model.addSquare(new Square (250, 250, 100, 100));
		model.addCircle(new Circle (250, 250, 50));
//		model.addTriangle(new Triangle(500, 30, 500, 20, 470, 20));




		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
		
//		new gui, not fully working
//		GUI g = new GUI(model);
//		g.createGUI();
	}
}
