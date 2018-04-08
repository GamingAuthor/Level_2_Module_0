package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		super.update();
		x+=4;
		if(x>810) {
		isAlive = false;
		}
	}
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	}
