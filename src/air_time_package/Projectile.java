package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
		if(ObjectManager.hours >2) {
		x += 4;
		}
		if(ObjectManager.hours<=2) {
		x += 6;
		}
		if (x > 810) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		if (ObjectManager.hours >= 3) {
			g.drawImage(GamePanel.bulletImg, x, y, width, height, null);
		} else if(ObjectManager.hours<=2){
			g.drawImage(GamePanel.fireImg, x, y, width, height, null);
		}
	}
}
