package air_time_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer = new Timer(1000 / 60, this);
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int INSTRUCTION_STATE = 3;
	final int WIN_STATE = 4;
	int currentState = MENU_STATE;
	Plane plan = new Plane(25, 225, 50, 50);
	Font titleFont = new Font("Arial", Font.BOLD, 48);
	Font enterFont = new Font("Arial", Font.ITALIC, 24);
	Font instructionFont = new Font("Arial", Font.PLAIN, 20);
	Font overFont = new Font("Arial", Font.BOLD, 48);
	Font restartFont = new Font("Arial", Font.ITALIC, 24);
	Font awayFont = new Font("Arial", Font.PLAIN, 20);
	Font counterFont = new Font("Arial", Font.PLAIN, 20);
	Font hourFont = new Font("Arial", Font.BOLD, 20);
	Font winFont = new Font("Arial", Font.BOLD, 48);
	Font youFont = new Font("Arial", Font.ITALIC, 24);
	Font controlFont = new Font("Arial", Font.PLAIN, 24);
	Color sky = new Color(147, 221, 255);
	Color death = new Color(255, 96, 96);
	Color manual = new Color(195, 255, 183);
	Color dusk = new Color(255, 186, 102);
	Color night = new Color(119, 119, 255);
	ObjectManager manager = new ObjectManager(plan);
	public static BufferedImage planeImg;
	public static BufferedImage zombieImg;
	public static BufferedImage bulletImg;
	public static BufferedImage powerupImg;
	public static BufferedImage jalapenoImg;
	public static BufferedImage fireImg;
	boolean win = false;

	GamePanel() {
		try {
			planeImg = ImageIO.read(this.getClass().getResourceAsStream("plane.png"));
			zombieImg = ImageIO.read(this.getClass().getResourceAsStream("zombie.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("projectile.png"));
			powerupImg = ImageIO.read(this.getClass().getResourceAsStream("powerup.png"));
			jalapenoImg = ImageIO.read(this.getClass().getResourceAsStream("jalapeno.png"));
			fireImg = ImageIO.read(this.getClass().getResourceAsStream("fire.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void startGame() {
		timer.start();
		songPlay();
	}

	void songPlay() {
		if (currentState == GAME_STATE) {
			manager.gamersong.play();
		} else {
			manager.gamersong.stop();
		}

		if (currentState == MENU_STATE) {
			manager.main.play();
		} else {
			manager.main.stop();
		}

		if (currentState == END_STATE) {
			manager.over.play();
		} else {
			manager.over.stop();
		}
		if (currentState == WIN_STATE) {
			manager.winner.play();
		} else {
			manager.winner.stop();
		}
	}
	void endlessSong() {
		manager.main.stop();
		if(manager.endless) {
		if (System.currentTimeMillis() - manager.songTimer >= manager.songPlayTime) {
			manager.gamersong.play();
			manager.songTimer = System.currentTimeMillis();
		}
		}
	}
	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.purgeObjects();
		manager.checkCollision();
		manager.keepTime();
		if (!manager.mantis.isAlive) {
			currentState = END_STATE;
			songPlay();
		} else if (!manager.mantis.isAlive && !manager.endless && manager.hours == 0) {
			manager.gamersong.stop();
			manager.winner.play();
			currentState = WIN_STATE;
		}
		if(manager.endless) {
			endlessSong();
		}
	}

	void updateEndState() {
	}

	void updateInstructionState() {
	}

	void updateWinState() {

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
		if (win) {
			g.setFont(enterFont);
			g.setColor(Color.WHITE);
			enterFont.isItalic();
			g.drawString("You've unlocked endless mode! Press 0 to play!", 140, 300);
		}
	}

	void drawGameState(Graphics g) {
		if (!manager.endless) {
			if (manager.hours > 3) {
				g.setColor(sky);
			} else if (manager.hours < 4 && manager.hours >= 2) {
				g.setColor(dusk);
			} else if (manager.hours == 1) {
				g.setColor(night);
			}
		} else {
			g.setColor(night);
		}
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
		g.setColor(Color.RED);
		g.drawString("Projectiles: " + manager.projectileCounter, 360, 20);
		g.setFont(counterFont);
		g.setColor(Color.RED);
		if (!manager.endless) {
			g.drawString("Hours from destination: " + manager.hours, 110, 20);
		} else {
			g.drawString("Hours survived: " + (manager.hours - 1), 110, 20);
		}
		g.setFont(hourFont);
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
		if (!manager.endless) {
			g.drawString("You were " + manager.hours + " hours away from your destination.", 180, 250);
		} else {
			g.drawString("You survived for " + (manager.hours - 1) + " hours.", 220, 250);
			
		}
	}

	void drawInstructionState(Graphics g) {
		g.setColor(manual);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
		g.setFont(controlFont);
		g.setColor(Color.RED);
		g.drawString("Use the UP and DOWN arrow keys to navigate the lanes.", 85, 25);
		g.drawString("Press SPACE to shoot projectiles when you have them.", 85, 55);
		g.drawImage(zombieImg, 1, 75, null);
		g.drawString("Avoid the zombies!", 150, 175);
		g.drawImage(powerupImg, 45, 175, null);
		g.drawString("Grab these Power-Ups to shoot projectiles!", 200, 300);
		g.drawImage(jalapenoImg, 65, 325, 75, 150, null);
		g.drawString("When this shows up, your projectiles will be more powerful!", 125, 450);
	}

	void drawWinState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, AirTime.width, AirTime.height);
		g.setColor(Color.YELLOW);
		g.setFont(winFont);
		winFont.isBold();
		g.drawString("YOU WIN!", 100, 100);
		g.setColor(Color.YELLOW);
		g.setFont(youFont);
		youFont.isPlain();
		g.drawString("Press ENTER to play again!", 350, 400);
		System.out.println(win);
		g.drawString("You defeated " + manager.score + " zombies on your trip!", 180, 250);
		g.setColor(Color.YELLOW);
		g.setFont(instructionFont);
		instructionFont.isItalic();
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
				manager.endless = false;
				currentState = GAME_STATE;
				songPlay();
				manager.hours = 6;
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
				songPlay();
				manager = new ObjectManager(new Plane(25, 225, 50, 50));
			} else if (currentState == WIN_STATE) {
				win = true;
				currentState = MENU_STATE;
				songPlay();
				manager = new ObjectManager(new Plane(25, 225, 50, 50));

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
		} else if (e.getKeyCode() == (KeyEvent.VK_DOWN)) {
			if (manager.mantis.y == 25) {
				manager.mantis.y = 125;
			} else if (manager.mantis.y == 125) {
				manager.mantis.y = 225;
			} else if (manager.mantis.y == 225) {
				manager.mantis.y = 325;
			} else if (manager.mantis.y == 325) {
				manager.mantis.y = 425;
			} else if (manager.mantis.y == 425) {

			}
		} else if (e.getKeyCode() == (KeyEvent.VK_SPACE)) {
			if (currentState == GAME_STATE) {
				if (manager.projectileCounter > 0) {
					manager.addProjectile(new Projectile(manager.mantis.x + 21, manager.mantis.y + 10, 25, 25));
					manager.projectileCounter--;
					if (!ObjectManager.endless) {
						if (manager.hours > 2) {
							manager.fire.play();
						} else {
							manager.firefire.play();
						}
					} else {
						manager.firefire.play();
					}
				}
			}
		}
		if (currentState == MENU_STATE) {
			if (e.getKeyCode() == (KeyEvent.VK_SPACE)) {
				currentState = INSTRUCTION_STATE;
			}
		} else if (currentState == INSTRUCTION_STATE) {
			if (e.getKeyCode() == (KeyEvent.VK_SPACE)) {
				currentState = MENU_STATE;
			}
		}
		if (win) {
			if (e.getKeyCode() == (KeyEvent.VK_0)) {
				System.out.println("yes");
				if (currentState == MENU_STATE) {
					manager.endless = true;
					manager.hours = 0;
					currentState = GAME_STATE;
					endlessSong();
				}
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
			if (manager.hours == 0) {
				currentState = WIN_STATE;
				manager.gamersong.stop();
				manager.winner.play();
			}
		} else if (currentState == END_STATE) {
			updateEndState();
		} else if (currentState == INSTRUCTION_STATE) {
			updateInstructionState();
		} else if (currentState == WIN_STATE) {
			updateWinState();
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
		} else if (currentState == WIN_STATE) {
			drawWinState(g);
		}
	}

}
