package intro_to_array_lists;

import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders{
	JFrame frame = new JFrame();
	final static  int width = 500;
	final static int height = 800;
	GamePanel panel = new GamePanel();
	
	public static void main(String[] args) {
	LeagueInvaders hi = new LeagueInvaders();
	hi.setup();
}
	 LeagueInvaders(){

	 }
	 void setup() {
		 frame.add(panel);
		 frame.setSize(width,height);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().setPreferredSize(new Dimension(width,height));
		 frame.pack();
		 frame.addKeyListener(panel);
		 frame.setVisible(true);
		 panel.startGame();
	 }
}