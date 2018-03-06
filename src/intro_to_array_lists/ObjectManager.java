package intro_to_array_lists;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Rocketship dip;
	ArrayList<Projectile> project = new ArrayList<Projectile>();
	ObjectManager(Rocketship ship){
		this.dip = ship;
	}
	void update() {
		dip.update();
		for(Projectile p : project) {
			p.update();
		}
	}
	void draw(Graphics g) {
		dip.draw(g);		
		for(Projectile tile : project) {
		tile.draw(g);
		}
	}
	void addProjectile(Projectile pro) {
		project.add(pro);
		}
	}

