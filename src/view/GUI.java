package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;
import controller.*;

public class GUI implements ActionListener {
	private Model mod;
	private JFrame f;
	private JFrame f2;
	private JPanel p;
	private JPanel p2;
	private ActionListener l;
	private KeyListener k;
	
	private Board b;
	
	public GUI (Model m) {
		this.mod = m;
		
		l = new RunListener(mod);
		k = new KeyboardListener(mod);
		b = new Board(500, 500, mod);
	}
	
	public void createGUI() {
		runModeGUI();
		buildModeGUI();
	}
	
	public void runModeGUI () {
		
		f = new JFrame("MW8 Gizmoball: Run Mode");
		p = new JPanel();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		c.add(p);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(7, 1));
		
		JButton button1 = new JButton("Start");
		button1.addActionListener(l);
		button1.setMaximumSize(new Dimension(160, 160));
		buttons.add(button1);
		
		JButton button2 = new JButton("Stop");
		button2.addActionListener(l);
		button2.setMaximumSize(new Dimension(160, 160));
		buttons.add(button2);
		
		JButton button3 = new JButton("Tick");
		button3.addActionListener(l);
		button3.setMaximumSize(new Dimension(160, 160));
		buttons.add(button3);
		
		JButton button4 = new JButton("Load");
		button4.addActionListener(l);
		button4.setMaximumSize(new Dimension(160, 160));
		buttons.add(button4);
		
		JButton button5 = new JButton("Reload");
		button5.addActionListener(l);
		button5.setMaximumSize(new Dimension(160, 160));
		buttons.add(button5);
		
		JButton button6 = new JButton("Build Mode");
		button6.addActionListener(this);
		button6.setMaximumSize(new Dimension(160, 160));
		buttons.add(button6);
		
		JButton button7 = new JButton("Quit");
		button7.addActionListener(l);
		button7.setMaximumSize(new Dimension(160, 160));
		buttons.add(button7);
		
		c.add(b, BorderLayout.WEST);
		c.add(buttons, BorderLayout.EAST);
		
		f.pack();
		f.setVisible(true);
		
		p.addKeyListener(k);
		p.setFocusable(true);
		p.requestFocusInWindow();
		
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);
		button7.setFocusable(false);
	}
	
	public void buildModeGUI() {
		f2 = new JFrame("MW8 Gizmoball: Build Mode");
		p2 = new JPanel();
		f2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container c = f2.getContentPane();
		c.add(p2);
		
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(12, 2));
		
		JButton button1 = new JButton("Add Gizmo: ");
		button1.addActionListener(l);
		button1.setMaximumSize(new Dimension(160, 160));
		options.add(button1);
		
		String[] gizmos = {"Circle", "Square", "Triangle"};
		JComboBox gizmo = new JComboBox(gizmos);
		gizmo.setSelectedIndex(0);
		gizmo.setMaximumSize(new Dimension(160, 160));
		gizmo.addActionListener(l);
		options.add(gizmo);
		
		JButton button2 = new JButton("Add Ball: ");
		button2.addActionListener(l);
		button2.setMaximumSize(new Dimension(160, 160));
		options.add(button2);
		
		JPanel ballInput = new JPanel();
		ballInput.setLayout(new GridLayout(2, 1));
		JTextField xCoord = new JTextField();
		JTextField yCoord = new JTextField();
		ballInput.add(xCoord);
		ballInput.add(yCoord);
		options.add(ballInput);
		
		JButton button3 = new JButton("Add Absorber: ");
		button3.addActionListener(l);
		button3.setMaximumSize(new Dimension(160, 160));
		options.add(button3);
		
		JPanel empty = new JPanel();
		options.add(empty);
		
		JButton button4 = new JButton("Add Left Flipper");
		button4.addActionListener(l);
		button4.setMaximumSize(new Dimension(160, 160));
		options.add(button4);
		
		JButton button5 = new JButton("Add Right Flipper");
		button5.addActionListener(l);
		button5.setMaximumSize(new Dimension(160, 160));
		options.add(button5);
		
		JButton button6 = new JButton("Rotate");
		button6.addActionListener(l);
		button6.setMaximumSize(new Dimension(160, 160));
		options.add(button6);
		
		JButton button7 = new JButton("Delete");
		button7.addActionListener(l);
		button7.setMaximumSize(new Dimension(160, 160));
		options.add(button7);
		
		JButton button8 = new JButton("Move");
		button8.addActionListener(l);
		button8.setMaximumSize(new Dimension(160, 160));
		options.add(button8);
		
		JButton button9 = new JButton("Clear board");
		button9.addActionListener(l);
		button9.setMaximumSize(new Dimension(160, 160));
		options.add(button9);
		
		JButton button10 = new JButton("Friction: ");
		button10.addActionListener(l);
		button10.setMaximumSize(new Dimension(160, 160));
		options.add(button10);
		
		JPanel fricInput = new JPanel();
		fricInput.setLayout(new GridLayout(2, 1));
		JTextField xIn = new JTextField();
		JTextField yIn = new JTextField();
		fricInput.add(xIn);
		fricInput.add(yIn);
		options.add(fricInput);
		
		JButton button11 = new JButton("Gravity: ");
		button11.addActionListener(l);
		button11.setMaximumSize(new Dimension(160, 160));
		options.add(button11);
		
		JPanel gravInput = new JPanel();
		gravInput.setLayout(new GridLayout(1, 1));
		JTextField inp = new JTextField();
		gravInput.add(inp);
		options.add(gravInput);
		
		JButton button12 = new JButton("Connect");
		button12.addActionListener(l);
		button12.setMaximumSize(new Dimension(160, 160));
		options.add(button12);
		
		JButton button13 = new JButton("Disconnect");
		button13.addActionListener(l);
		button13.setMaximumSize(new Dimension(160, 160));
		options.add(button13);
		
		JButton button14 = new JButton("Key Connect");
		button14.addActionListener(l);
		button14.setMaximumSize(new Dimension(160, 160));
		options.add(button14);
		
		JButton button15 = new JButton("Key Disconnect");
		button15.addActionListener(l);
		button15.setMaximumSize(new Dimension(160, 160));
		options.add(button15);
		
		JButton button16 = new JButton("Load Model");
		button16.addActionListener(l);
		button16.setMaximumSize(new Dimension(160, 160));
		options.add(button16);
		
		JButton button17 = new JButton("Reload Model");
		button17.addActionListener(l);
		button17.setMaximumSize(new Dimension(160, 160));
		options.add(button17);
		
		JButton button18 = new JButton("Quit");
		button18.addActionListener(l);
		button18.setMaximumSize(new Dimension(160, 160));
		options.add(button18);
		
		JButton button19 = new JButton("Run Mode");
		button19.addActionListener(this);
		button19.setMaximumSize(new Dimension(160, 160));
		options.add(button19);
		
		c.add(b, BorderLayout.WEST);
		c.add(options, BorderLayout.EAST);
		
		f2.pack();
		f2.setVisible(false);
		
		p2.addKeyListener(k);
		p2.setFocusable(true);
//		p2.requestFocusInWindow();
		
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);
		button7.setFocusable(false);
		button8.setFocusable(false);
		button9.setFocusable(false);
		button10.setFocusable(false);
		button11.setFocusable(false);
		button12.setFocusable(false);
		button13.setFocusable(false);
		button14.setFocusable(false);
		button15.setFocusable(false);
		button16.setFocusable(false);
		button17.setFocusable(false);
		button18.setFocusable(false);
		button19.setFocusable(false);
		
		empty.setFocusable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Build Mode":
			f.setVisible(false);
			f2.setVisible(true);
			break;
		case "Run Mode":
			f2.setVisible(false);
			f.setVisible(true);
			break;
		}
	}
	
	

}
