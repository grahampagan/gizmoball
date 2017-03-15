package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	private boolean moving = false;
	private int fx;
	private int fy;
	private int absorberX;
	private int absorberY;
	private int absorberXEnd;
	private int absorberYEnd;
	private ArrayList<JButton> buildButtonsArray = new ArrayList<JButton>();
	private JTextField gravInp;
	private JTextField xFricIn;
	private JTextField yFricIn;

	private Board b;

	public GUI (Model m) {
		this.mod = m;

		String[] gizmos = {"Circle", "Square", "Triangle"};
		gizmo = new JComboBox(gizmos);
		gravInp = new JTextField();
		xFricIn = new JTextField();
		yFricIn = new JTextField();
		l = new RunListener(mod, gizmo, gravInp, xFricIn, yFricIn);
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

		JButton startButton = new JButton("Start");
		startButton.addActionListener(l);
		startButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(l);
		stopButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(stopButton);

		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(l);
		tickButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(tickButton);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(l);
		loadButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(loadButton);

		JButton reloadButton = new JButton("Reload");
		reloadButton.addActionListener(l);
		reloadButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(reloadButton);

		JButton buildModeButton = new JButton("Build Mode");
		buildModeButton.addActionListener(this);
		buildModeButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(buildModeButton);

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(l);
		quitButton.setMaximumSize(new Dimension(160, 160));
		runButtons.add(quitButton);

		buildButtons = new JPanel();
		buildButtons.setLayout(new GridLayout(12, 2));

		JButton addGizmoButton = new JButton("Add Gizmo: ");
		addGizmoButton.addActionListener(l);
		addGizmoButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(addGizmoButton);
		buildButtonsArray.add(addGizmoButton);

		gizmo.setSelectedIndex(0);
		gizmo.setMaximumSize(new Dimension(160, 160));
		gizmo.addActionListener(l);
		buildButtons.add(gizmo);

		JButton addBallButton = new JButton("Add Ball: ");
		addBallButton.addActionListener(l);
		addBallButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(addBallButton);
		buildButtonsArray.add(addBallButton);

		JPanel ballInput = new JPanel();
		ballInput.setLayout(new GridLayout(2, 1));
		JTextField xCoord = new JTextField();
		JTextField yCoord = new JTextField();
		ballInput.add(xCoord);
		ballInput.add(yCoord);
		buildButtons.add(ballInput);

		JButton addAbsorberButton = new JButton("Add Absorber: ");
		addAbsorberButton.addActionListener(this);
		addAbsorberButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(addAbsorberButton);
		buildButtonsArray.add(addAbsorberButton);

		JPanel empty = new JPanel();
		buildButtons.add(empty);

		JButton AddLeftFlipperButton = new JButton("Add Left Flipper");
		AddLeftFlipperButton.addActionListener(l);
		AddLeftFlipperButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(AddLeftFlipperButton);
		buildButtonsArray.add(AddLeftFlipperButton);

		JButton addRightFlipperButton = new JButton("Add Right Flipper");
		addRightFlipperButton.addActionListener(l);
		addRightFlipperButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(addRightFlipperButton);
		buildButtonsArray.add(addRightFlipperButton);

		JButton rotateButton = new JButton("Rotate");
		rotateButton.addActionListener(l);
		rotateButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(rotateButton);
		buildButtonsArray.add(rotateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(l);
		deleteButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(deleteButton);
		buildButtonsArray.add(deleteButton);

		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(this);
		moveButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(moveButton);
		buildButtonsArray.add(moveButton);

		JButton clearBoardButton = new JButton("Clear board");
		clearBoardButton.addActionListener(l);
		clearBoardButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(clearBoardButton);
		buildButtonsArray.add(clearBoardButton);

		JButton frictionButton = new JButton("Apply Friction: ");
		frictionButton.addActionListener(l);
		frictionButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(frictionButton);
		buildButtonsArray.add(frictionButton);

		JPanel fricInput = new JPanel();
		fricInput.setLayout(new GridLayout(2, 1));
		fricInput.add(xFricIn);
		fricInput.add(yFricIn);
		xFricIn.setText("0");
		yFricIn.setText("0");
		buildButtons.add(fricInput);

		JButton gravityButton = new JButton("Apply Gravity: ");
		gravityButton.addActionListener(l);
		gravityButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(gravityButton);
		buildButtonsArray.add(gravityButton);

		JPanel gravInput = new JPanel();
		gravInput.setLayout(new GridLayout(1, 1));
		gravInput.add(gravInp);
		gravInp.setText("0");
		buildButtons.add(gravInput);

		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(l);
		connectButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(connectButton);
		buildButtonsArray.add(connectButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(l);
		disconnectButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(disconnectButton);
		buildButtonsArray.add(disconnectButton);

		JButton keyConnectButton = new JButton("Key Connect");
		keyConnectButton.addActionListener(l);
		keyConnectButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(keyConnectButton);
		buildButtonsArray.add(keyConnectButton);

		JButton keyDisconnectButton = new JButton("Key Disconnect");
		keyDisconnectButton.addActionListener(l);
		keyDisconnectButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(keyDisconnectButton);
		buildButtonsArray.add(keyDisconnectButton);

		JButton loadModelButton = new JButton("Load Model");
		loadModelButton.addActionListener(l);
		loadModelButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(loadModelButton);
		buildButtonsArray.add(loadModelButton);

		JButton reloadModelButton = new JButton("Reload Model");
		reloadModelButton.addActionListener(l);
		reloadModelButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(reloadModelButton);
		buildButtonsArray.add(reloadModelButton);

		JButton quitBuildButton = new JButton("Quit");
		quitBuildButton.addActionListener(l);
		quitBuildButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(quitBuildButton);

		JButton runModeButton = new JButton("Run Mode");
		runModeButton.addActionListener(this);
		runModeButton.setMaximumSize(new Dimension(160, 160));
		buildButtons.add(runModeButton);
		buildButtonsArray.add(runModeButton);

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
					if (moving) {

						int xm = (int) Math.floor(e.getX() / 25);
						int ym = (int) Math.floor(e.getY() / 25);

						mod.move(fx, fy, xm, ym);
						mod.notifyObs();
						if(!mod.hasAtPosition(xm, ym)) {
							System.out.println("not moved");
						}
						moving = false;
					}
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

						System.out.println(absorberX + " " + absorberY + " " + width+ " " + height);

						Absorber a = new Absorber(absorberX, absorberY, width, height);
						mod.addAbsorber(a);

						addingAbsorber = false;
						for(JButton b: buildButtonsArray){
							b.setEnabled(true);
						}
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

		addGizmoButton.setFocusable(true);
		addBallButton.setFocusable(true);
		addAbsorberButton.setFocusable(true);
		AddLeftFlipperButton.setFocusable(true);
		addRightFlipperButton.setFocusable(true);
		rotateButton.setFocusable(false);
		deleteButton.setFocusable(false);
		startButton.setFocusable(false);
		stopButton.setFocusable(false);
		tickButton.setFocusable(false);
		loadButton.setFocusable(false);
		reloadButton.setFocusable(false);
		buildModeButton.setFocusable(false);
		quitButton.setFocusable(false);
		moveButton.setFocusable(false);
		clearBoardButton.setFocusable(false);
		frictionButton.setFocusable(false);
		gravityButton.setFocusable(false);
		connectButton.setFocusable(false);
		disconnectButton.setFocusable(false);
		keyConnectButton.setFocusable(false);
		keyDisconnectButton.setFocusable(false);
		loadModelButton.setFocusable(false);
		reloadModelButton.setFocusable(false);
		quitBuildButton.setFocusable(false);
		runModeButton.setFocusable(false);
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
				for(JButton b: buildButtonsArray){
					b.setEnabled(false);
				}
				break;
			case "Move":
				System.out.println("starting move gizmo");
				
				fx = mod.getGridHighlight().getPositionX();
				fy = mod.getGridHighlight().getPositionY();
				
				moving = true;
				break;
		}
	}



}
