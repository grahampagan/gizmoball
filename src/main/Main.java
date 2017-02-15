package main;

import javax.swing.UIManager;

import model.Model;
import model.Square;
import model.Absorber;
import model.Line;
import view.RunGui;
import physics.*; 

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {


	public static void main(String[] args) {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		Model model = new Model();

		model.setBallSpeed(200, 200);

		// Vertical line at (100,100), width 300
//		model.addLine(new Line(100, 100, 300));
//		model.addLine(new Line(100, 200, 300));
//		model.addLine(new Line(100, 300, 300));
//		model.addLine(new Line(100, 400, 300));
//		model.addSquare(new Square(40, 500, 300,400 ));
//		model.addAbsorber(new Absorber(10, 10, 100, 300));
//		model.addSquare(new Square (10, 10, 300, 300));
//		model.addCircle(new Circle(480, 10, 5));
		




		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}
}
