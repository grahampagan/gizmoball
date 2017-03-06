package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import model.FileParser;
import model.Model;
import model.board;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private Model model;
	private FileParser f;
	private board bo;

	public RunListener(Model m) {
		model = m;
		timer = new Timer(50, this);
		f = new FileParser(model);
		bo = model.getBoard();
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
					bo.clearBoard();
					f.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Clear board":
				bo.clearBoard();
			}
	}
}
