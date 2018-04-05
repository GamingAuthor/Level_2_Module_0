package air_time_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer = new Timer(1000 / 60, this);
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTION_STATE = 3;
	int currentState = MENU_STATE;
	Plane plan = new Plane(25, 225, 50, 50);
	Font titleFont = new Font("Arial", Font.BOLD, 48);
	Font enterFont = new Font("Arial", Font.ITALIC, 24);
	Font instructionFont = new Font("Arial", Font.PLAIN, 20);
	Font overFont = new Font("Arial", Font.BOLD, 48);
	Font restartFont = new Font("Arial", Font.ITALIC, 24);
	Font awayFont = new Font("Arial", Font.PLAIN, 20);
	Color sky = new Color(147, 221, 255);
	Color death = new Color(255, 96, 96);
	Color manual = new Color(195,255,183);
	ObjectManager manager = new ObjectManager(plan);

	void startGame() {
		timer.start();
	}

	void updateMenuState() {
	}

	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.purgeObjects();
		
		if(!manager.mantis.isAlive) {
			currentState=END_STATE;
		}
	}

	void updateEndState() {
	}
	void updateInstructionState() {
		
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		titleFont.isBold();
		g.drawString("AIR TIME", 100, 100);
		g.setFont(enterFont);
		g.setColor(Color.WHITE);
		enterFont.isItalic();
		g.drawString("Press ENTER to play!", 250, 250);
		g.setFont(instructionFont);
		g.setColor(Color.WHITE);
		g.drawString("Press SPACE for instructions.", 350, 400);
	}

	void drawGameState(Graphics g) {
		g.setColor(sky);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 100, 100);
		g.setColor(Color.BLACK);
		g.drawRect(0, 100, 100, 100);
		g.setColor(Color.BLACK);
		g.drawRect(0, 200, 100, 100);
		g.setColor(Color.BLACK);
		g.drawRect(0, 300, 100, 100);
		g.setColor(Color.BLACK);
		g.drawRect(0, 400, 100, 99);
		manager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(death);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
		g.setFont(overFont);
		g.setColor(Color.BLACK);
		overFont.isBold();
		g.drawString("GAME OVER", 100, 100);
		g.setFont(restartFont);
		g.setColor(Color.BLACK);
		restartFont.isItalic();
		g.drawString("Press ENTER to restart.", 350, 400);
		g.setFont(awayFont);
		g.setColor(Color.BLACK);
		g.drawString("You were 5 hours away from Canada.", 200, 250);
	}
	void drawInstructionState(Graphics g) {
		g.setColor(manual);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
			} else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
		} else if (e.getKeyCode() == (KeyEvent.VK_UP)) {
			if (manager.mantis.y == 25) {

			} else if (manager.mantis.y == 125) {
				manager.mantis.y = 25;
			} else if (manager.mantis.y == 225) {
				manager.mantis.y = 125;
			} else if (manager.mantis.y == 325) {
				manager.mantis.y = 225;
			} else if (manager.mantis.y == 425) {
				manager.mantis.y = 325;
			}
		} else if(e.getKeyCode() == (KeyEvent.VK_DOWN)) {
			if(manager.mantis.y==25) {
				manager.mantis.y=125;
			}else if(manager.mantis.y==125) {
				manager.mantis.y=225;
			}else if(manager.mantis.y==225) {
				manager.mantis.y=325;
			}else if(manager.mantis.y==325) {
				manager.mantis.y=425;
			}else if(manager.mantis.y==425) {
				
			}
		}
		if(currentState==MENU_STATE) {
			if(e.getKeyCode() == (KeyEvent.VK_SPACE)) {
				currentState=INSTRUCTION_STATE;
			}
		} else if(currentState==INSTRUCTION_STATE) {
			if(e.getKeyCode() == (KeyEvent.VK_SPACE)) {
				currentState=MENU_STATE;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		} else if (currentState == INSTRUCTION_STATE) {
			updateInstructionState();
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		} else if (currentState == INSTRUCTION_STATE) {
			drawInstructionState(g);
		}
	}

}
