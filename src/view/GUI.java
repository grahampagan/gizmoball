package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Absorber;
import model.Model;
import controller.*;

public class GUI implements ActionListener {
	private Model mod;
	private JFrame f;
	private JPanel p;
	private JPanel runButtons;
	private JPanel buildButtons;
	private ActionListener l;
	private KeyListener k;
	private JComboBox<String> gizmo;
	private boolean addingAbsorber = false;
	private int absorberX;
	private int absorberY;
	private int absorberXEnd;
	private int absorberYEnd;
	
	private Board b;
	
	public GUI (Model m) {
		this.mod = m;
		
		String[] gizmos = {"Circle", "Square", "Triangle"};
		gizmo = new JComboBox(gizmos);
		
		l = new RunListener(mod, gizmo);
		k = new KeyboardListener(mod);
		b = new Board(500, 500, mod);
	}
	
	public void createGUI() throws IOException {
		runModeGUI();
	}
	
	public void runModeGUI () throws IOException {
		
		f = new JFrame("MW8 Gizmoball");
		p = new JPanel();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		c.add(p);
		
		BufferedImage grid = ImageIO.read(new File("grid.png"));
		JLabel gridLabel = new JLabel(new ImageIcon(grid));
		p.add(gridLabel);
		
		runButtons = new JPanel();
		runButtons.setLayout(new GridLayout(7, 1));
		
		JButton button1 = new JButton("Start");
		button1.addActionListener(l);
		button1.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button1);
		
		JButton button2 = new JButton("Stop");
		button2.addActionListener(l);
		button2.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button2);
		
		JButton button3 = new JButton("Tick");
		button3.addActionListener(l);
		button3.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button3);
		
		JButton button4 = new JButton("Load");
		button4.addActionListener(l);
		button4.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button4);
		
		JButton button5 = new JButton("Reload");
		button5.addActionListener(l);
		button5.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button5);
		
		JButton button6 = new JButton("Build Mode");
		button6.addActionListener(this);
		button6.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button6);
		
		JButton button7 = new JButton("Quit");
		button7.addActionListener(l);
		button7.setMaximumSize(new Dimension(160, 160));
		runButtons.add(button7);

		buildButtons = new JPanel();
		buildButtons.setLayout(new GridLayout(12, 2));

		JButton bButton1 = new JButton("Add Gizmo: ");
		bButton1.addActionListener(l);
		bButton1.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton1);

		gizmo.setSelectedIndex(0);
		gizmo.setMaximumSize(new Dimension(160, 160));
		gizmo.addActionListener(l);
		buildButtons.add(gizmo);

		JButton bButton2 = new JButton("Add Ball: ");
		bButton2.addActionListener(l);
		bButton2.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton2);

		JPanel ballInput = new JPanel();
		ballInput.setLayout(new GridLayout(2, 1));
		JTextField xCoord = new JTextField();
		JTextField yCoord = new JTextField();
		ballInput.add(xCoord);
		ballInput.add(yCoord);
		buildButtons.add(ballInput);

		JButton bButton3 = new JButton("Add Absorber: ");
		bButton3.addActionListener(this);
		bButton3.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton3);

		JPanel empty = new JPanel();
		buildButtons.add(empty);

		JButton bButton4 = new JButton("Add Left Flipper");
		bButton4.addActionListener(l);
		bButton4.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton4);

		JButton bButton5 = new JButton("Add Right Flipper");
		bButton5.addActionListener(l);
		bButton5.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton5);

		JButton bButton6 = new JButton("Rotate");
		bButton6.addActionListener(l);
		bButton6.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton6);

		JButton bButton7 = new JButton("Delete");
		bButton7.addActionListener(l);
		bButton7.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton7);

		JButton bButton8 = new JButton("Move");
		bButton8.addActionListener(l);
		bButton8.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton8);

		JButton bButton9 = new JButton("Clear board");
		bButton9.addActionListener(l);
		bButton9.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(bButton9);

		JButton button10 = new JButton("Friction: ");
		button10.addActionListener(l);
		button10.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button10);

		JPanel fricInput = new JPanel();
		fricInput.setLayout(new GridLayout(2, 1));
		JTextField xIn = new JTextField();
		JTextField yIn = new JTextField();
		fricInput.add(xIn);
		fricInput.add(yIn);
		buildButtons.add(fricInput);

		JButton button11 = new JButton("Gravity: ");
		button11.addActionListener(l);
		button11.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button11);

		JPanel gravInput = new JPanel();
		gravInput.setLayout(new GridLayout(1, 1));
		JTextField inp = new JTextField();
		gravInput.add(inp);
		buildButtons.add(gravInput);

		JButton button12 = new JButton("Connect");
		button12.addActionListener(l);
		button12.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button12);

		JButton button13 = new JButton("Disconnect");
		button13.addActionListener(l);
		button13.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button13);

		JButton button14 = new JButton("Key Connect");
		button14.addActionListener(l);
		button14.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button14);

		JButton button15 = new JButton("Key Disconnect");
		button15.addActionListener(l);
		button15.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button15);

		JButton button16 = new JButton("Load Model");
		button16.addActionListener(l);
		button16.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button16);

		JButton button17 = new JButton("Reload Model");
		button17.addActionListener(l);
		button17.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button17);

		JButton button18 = new JButton("Quit");
		button18.addActionListener(l);
		button18.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button18);

		JButton button19 = new JButton("Run Mode");
		button19.addActionListener(this);
		button19.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(button19);
		
		c.add(b, BorderLayout.CENTER);
		c.add(runButtons, BorderLayout.WEST);
		c.add(buildButtons, BorderLayout.EAST);
		
		b.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mod.getBuildMode()){
					double x = Math.floor(e.getX()/25);
					double y = Math.floor(e.getY()/25);
					System.out.println("x: " + x + " y: " + y);
					mod.setGridHighlight(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {		
			}

			@Override
			public void mouseExited(MouseEvent e) {				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(mod.getBuildMode()){
					double x = Math.floor(e.getX()/25);
					double y = Math.floor(e.getY()/25);
					if(addingAbsorber){
						System.out.println("Mouse dragged from x: " + x + " y: " + y);
						absorberX = ((int)x)*25;
						absorberY = ((int)y)*25;
					}
				}				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(mod.getBuildMode()){
					double x = Math.floor(e.getX()/25);
					double y = Math.floor(e.getY()/25);
					if(addingAbsorber){
						System.out.println("Mouse dragged to x: " + x + " y: " + y);
						absorberXEnd = (((int)x)*25) + 25;
						absorberYEnd = (((int)y)*25) + 25;
						
						if(absorberX > absorberXEnd){
							int oldX = absorberX;
							absorberX = absorberXEnd;
							absorberXEnd = oldX;
						}
						
						if(absorberY > absorberYEnd){
							int oldY = absorberY;
							absorberY = absorberYEnd;
							absorberYEnd = oldY;
						}
						
						int width = absorberXEnd - absorberX;
						int height = absorberYEnd - absorberY;
						
						if(width == 0){
							width = 25;
						}
						
						if(height == 0){
							height = 25;
						}
						
						System.out.println(absorberX + " " + absorberY + " " + width+ " " + height);
						
						Absorber a = new Absorber(absorberX, absorberY, width, height);
						mod.addAbsorber(a);
						
						addingAbsorber = false;
					}
				}						
			}
		});

		f.setVisible(true);
		runButtons.setVisible(true);
		buildButtons.setVisible(false);
		f.pack();
		
		p.addKeyListener(k);
		p.setFocusable(true);
		p.requestFocusInWindow();
		
		bButton1.setFocusable(true);
		bButton2.setFocusable(true);
		bButton3.setFocusable(true);
		bButton4.setFocusable(true);
		bButton5.setFocusable(true);
		bButton6.setFocusable(false);
		bButton7.setFocusable(false);
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);
		button7.setFocusable(false);
		bButton8.setFocusable(false);
		bButton9.setFocusable(false);
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
			runButtons.setVisible(false);
			buildButtons.setVisible(true);
			f.pack();
			mod.setBuildMode(true);
			break;
		
		case "Run Mode":
			runButtons.setVisible(true);
			buildButtons.setVisible(false);
			f.pack();
			mod.setBuildMode(false);
			break;
			
		case "Add Absorber: ":
			addingAbsorber = true;
			break;
		}
	}
	
	

}
