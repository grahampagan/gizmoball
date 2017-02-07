package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Canvas;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Build {

	private JFrame frmGizmoball;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Build window = new Build();
					window.frmGizmoball.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Build() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGizmoball = new JFrame();
		frmGizmoball.setTitle("Gizmoball");
		frmGizmoball.setBounds(100, 100, 885, 666);
		frmGizmoball.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGizmoball.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.textHighlight);
		panel.setBounds(356, 11, 503, 579);
		frmGizmoball.getContentPane().add(panel);
		
		JToggleButton tglbtnRunMode = new JToggleButton("Build Mode");
		tglbtnRunMode.setSelected(true);
		tglbtnRunMode.setBounds(100, 27, 121, 23);
		frmGizmoball.getContentPane().add(tglbtnRunMode);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Absorber", "Left Flipper", "Right Flipper", "Ball", "Gizmo"}));
		comboBox.setBounds(108, 61, 192, 65);
		frmGizmoball.getContentPane().add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(9, 65, 89, 23);
		frmGizmoball.getContentPane().add(btnAdd);
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.setMinimum(-360);
		slider.setMaximum(360);
		slider.setBounds(86, 193, 200, 26);
		frmGizmoball.getContentPane().add(slider);
		
		JLabel lblRotate = new JLabel("Rotate:");
		lblRotate.setBounds(36, 193, 46, 14);
		frmGizmoball.getContentPane().add(lblRotate);
		
		JLabel lblGravity = new JLabel("Gravity:");
		lblGravity.setBounds(36, 262, 46, 14);
		frmGizmoball.getContentPane().add(lblGravity);
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(10);
		slider_1.setMinimum(-20);
		slider_1.setMaximum(20);
		slider_1.setBounds(102, 262, 200, 26);
		frmGizmoball.getContentPane().add(slider_1);
		
		JButton btnLoadModel = new JButton("Load Model");
		btnLoadModel.setBounds(29, 519, 89, 23);
		frmGizmoball.getContentPane().add(btnLoadModel);
		
		JButton btnReloadModel = new JButton("Reload Model");
		btnReloadModel.setBounds(166, 519, 103, 23);
		frmGizmoball.getContentPane().add(btnReloadModel);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 103, 88, 23);
		frmGizmoball.getContentPane().add(btnDelete);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(71, 366, 89, 23);
		frmGizmoball.getContentPane().add(btnConnect);
		
		JButton btnDiconnect = new JButton("Disconnect");
		btnDiconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDiconnect.setBounds(71, 333, 89, 23);
		frmGizmoball.getContentPane().add(btnDiconnect);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Key");
		chckbxNewCheckBox.setBounds(166, 352, 116, 23);
		frmGizmoball.getContentPane().add(chckbxNewCheckBox);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(100, 415, 200, 26);
		frmGizmoball.getContentPane().add(slider_2);
		
		JSlider slider_3 = new JSlider();
		slider_3.setBounds(100, 464, 200, 26);
		frmGizmoball.getContentPane().add(slider_3);
		
		JLabel lblFriction = new JLabel("Friction");
		lblFriction.setBounds(36, 426, 62, 44);
		frmGizmoball.getContentPane().add(lblFriction);
		
		JMenuBar menuBar = new JMenuBar();
		frmGizmoball.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
	}
}
