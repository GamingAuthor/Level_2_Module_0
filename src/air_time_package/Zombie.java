package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject {

	Zombie(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
		if (!ObjectManager.endless) {
			if (ObjectManager.hours > 3) {
				x -= 2;
			}
			if (ObjectManager.hours <= 3) {
				x -= 3;
			}
			if (ObjectManager.hours == 1) {
				x -= 1;
			}
		} else {
			x -= 4;
		}
		if (x < -50) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawImage(GamePanel.zombieImg, x, y, width, height, null);
	}
}
