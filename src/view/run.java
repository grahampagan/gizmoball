package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class run {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					run window = new run();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public run() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 873, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(384, 11, 463, 542);
		frame.getContentPane().add(panel);
		
		JToggleButton tglbtnRunMode = new JToggleButton("Run Mode");
		tglbtnRunMode.setBounds(43, 111, 121, 61);
		frame.getContentPane().add(tglbtnRunMode);
		
		JToggleButton tglbtnStart = new JToggleButton("Start");
		tglbtnStart.setBounds(199, 111, 121, 61);
		frame.getContentPane().add(tglbtnStart);
		
		JButton btnNewButton = new JButton("Tick");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(140, 322, 110, 61);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLoadModel = new JButton("Load Model");
		btnLoadModel.setBounds(54, 226, 110, 61);
		frame.getContentPane().add(btnLoadModel);
		
		JButton btnReloadModel = new JButton("Reload Model");
		btnReloadModel.setBounds(211, 226, 109, 61);
		frame.getContentPane().add(btnReloadModel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
	}

}
