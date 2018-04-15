package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject{
	PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		super.update();
		x-=2;
		if(ObjectManager.hours<=2) {
		x-=3;
		}
		if(x<-50) {
		isAlive = false;
		}
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		if(ObjectManager.hours>=3) {

		g.drawImage(GamePanel.powerupImg, x, y, width, height, null);
		} else if(ObjectManager.hours<=2){
		g.drawImage(GamePanel.jalapenoImg, x, y, width, height, null);
		}
	}
}
