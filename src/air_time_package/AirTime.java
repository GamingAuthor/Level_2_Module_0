package air_time_package;

import java.awt.Dimension;

import javax.swing.JFrame;

public class AirTime {
	JFrame frame = new JFrame();
	final static int width = 800;
	final static int height = 500;
	GamePanel panel = new GamePanel();
	
	public static void main(String[] args) {
		AirTime hi = new AirTime();
		hi.ui();
	}
	void ui() {
		frame.add(panel);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(width,height));
		frame.pack();
		frame.addKeyListener(panel);
		frame.setVisible(true);
		panel.startGame();
	}
}
