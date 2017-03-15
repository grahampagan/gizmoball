package controller;

import java.awt.event.*;
import java.util.ArrayList;

import model.*;

public class KeyboardListener implements KeyListener {
	
	private Model model;
	
	public KeyboardListener(Model m){
		model = m;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()==KeyEvent.VK_R){
            for(Absorber a : model.getAbsorbers()){
            	Ball b = model.getBall();
            	a.releaseBall(b);
            }
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}