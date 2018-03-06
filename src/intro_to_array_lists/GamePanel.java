package intro_to_array_lists;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener, KeyListener{
Timer timer = new Timer(1000/60, this);
final int MENU_STATE = 0;
final int GAME_STATE = 1;
final int END_STATE = 2;
int currentState = MENU_STATE;
Font titleFont = new Font("Arial",Font.BOLD,48);
Font enterFont = new Font("Arial", Font.ITALIC,24);
Font instructionFont = new Font("Arial", Font.PLAIN, 24);
Font overFont = new Font("Arial",Font.BOLD,48);
Font killFont = new Font("Arial",Font.ITALIC,24);
Font restartFont = new Font("Arial",Font.PLAIN,24);
Rocketship shippo = new Rocketship(250,700,50,50);
ObjectManager manager = new ObjectManager(shippo);
void startGame() {	
	timer.start();
}
 GamePanel(){

 }
void updateMenuState() {
	
}
void updateGameState() {
	manager.update();
}
void updateEndState() {
	
}
void drawMenuState(Graphics g) {
	g.setColor(Color.BLUE);
	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
	g.setFont(titleFont);
	g.setColor(Color.YELLOW);
	titleFont.isBold();
	g.drawString("LEAGUE INVADERS", 20, 100);
	g.setFont(enterFont);
	g.setColor(Color.YELLOW);
	enterFont.isItalic();
	g.drawString("Press ENTER to play!", 125, 400);
	g.setFont(instructionFont);
	g.setColor(Color.YELLOW);
	g.drawString("Press SPACE for instructions", 90, 700);
}
void drawGameState(Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
	manager.draw(g);

}
void drawEndState(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
	g.setFont(overFont);
	g.setColor(Color.GREEN);
	g.drawString("GAME OVER!", 85, 100);
	g.setFont(killFont);
	g.setColor(Color.GREEN);
	g.drawString("You killed 0 enemies", 125, 400);
	g.setFont(restartFont);
	g.setColor(Color.GREEN);
	g.drawString("Press ENTER to restart", 110, 700);
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
if(currentState == MENU_STATE){
        updateMenuState();
}else if(currentState == GAME_STATE){
        updateGameState();
}else if(currentState == END_STATE){
        updateEndState();
}
	repaint();
}
@Override
public void paintComponent(Graphics g) {
	if(currentState == MENU_STATE){
        drawMenuState(g);
}else if(currentState == GAME_STATE){
        drawGameState(g);
}else if(currentState == END_STATE){
        drawEndState(g);
}
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	System.out.println(e.getKeyChar());
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode()==(KeyEvent.VK_ENTER)) {
		if(currentState==MENU_STATE) {
			currentState=GAME_STATE;
		}else if(currentState==GAME_STATE) {
			currentState=END_STATE;
		}else if(currentState==END_STATE) {
			currentState = MENU_STATE;
		}
	}
	if(e.getKeyCode()==(KeyEvent.VK_RIGHT)) {
		shippo.x+=shippo.speed;
	}
	if(e.getKeyCode()==(KeyEvent.VK_UP)) {
		shippo.y-=shippo.speed;
	}
	if(e.getKeyCode()==(KeyEvent.VK_DOWN)) {
		shippo.y+=shippo.speed;
	}
	if(e.getKeyCode()==(KeyEvent.VK_LEFT)){
		shippo.x-=shippo.speed;
	}
	if(e.getKeyCode()==(KeyEvent.VK_SPACE)) {
		manager.addProjectile(new Projectile(shippo.x+21, shippo.y, 10, 10));
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}