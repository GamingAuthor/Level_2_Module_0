package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject{

	Zombie(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		super.update();
		x--;
		if(x<-50) {
		isAlive = false;
		}
	}
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
