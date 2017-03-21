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
		
		if(k.getKeyCode()==KeyEvent.VK_UP){
            for(flipper2 f : model.getFlippers2()){
            	f.setRectangle();
            }
		}	

	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyCode()==KeyEvent.VK_UP){
            for(flipper2 f : model.getFlippers2()){
            	f.setRectangle();
            }
		}	

		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}