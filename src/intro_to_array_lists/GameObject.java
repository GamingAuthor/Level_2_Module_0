package intro_to_array_lists;

import java.awt.Graphics;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	void update() {
	x+=20;
	y+=20;
	}
	void draw(Graphics g) {
		g.fillRect(x, y, 100, 100);
	}
}
