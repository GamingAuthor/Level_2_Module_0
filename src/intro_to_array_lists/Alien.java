package intro_to_array_lists;

import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject{

	Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	void update() {
		super.update();
		y++;
		if(y>800) {
			isAlive = false;
		}
	}
	void draw(Graphics g) {
        g.drawImage(GamePanel.alienImg, x, y, width, height, null);
	}
}
