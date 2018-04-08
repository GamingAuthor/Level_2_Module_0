package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject {
	ObjectManager manger = new ObjectManager(null);

	Zombie(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
		if (manger.hours > 2) {
			x--;
		}
		if (manger.hours == 2) {
			x -= 2;
		}
		if (manger.hours == 1) {
			x -= 3;
		}
		if (x < -50) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
