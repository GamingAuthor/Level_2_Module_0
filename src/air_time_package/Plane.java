package air_time_package;

import java.awt.Color;
import java.awt.Graphics;

public class Plane extends GameObject{
	Plane(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	void update() {
		super.update();
	}
	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
	}

}
