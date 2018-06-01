package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject {
	PowerUp(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
		if (!ObjectManager.endless) {
			if (ObjectManager.hours > 3) {
				x -= 3;
			}
			if (ObjectManager.hours <= 3) {
				x -= 4;
			}
			if (ObjectManager.hours == 1) {
				x -= 1;
			}
		} else {
			x -= 5;
		}
		if (x < -50) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		if (!ObjectManager.endless) {
			if (ObjectManager.hours >= 3) {
				g.drawImage(GamePanel.powerupImg, x, y, width, height, null);
			} else if (ObjectManager.hours < 3) {
				g.drawImage(GamePanel.jalapenoImg, x, y, width, height, null);
			}
		} else {
			g.drawImage(GamePanel.jalapenoImg, x, y, width, height, null);
		}
	}
}
