package intro_to_array_lists;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship dip;
	ArrayList<Projectile> project = new ArrayList<Projectile>();
	ArrayList<Alien> al = new ArrayList<Alien>();
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	ObjectManager(Rocketship ship) {
		this.dip = ship;
	}	
	void update() {
		dip.update();
		for (Projectile p : project) {
			p.update();
		}
		for (Alien a : al) {
			a.update();
		}
	}

	void draw(Graphics g) {
		dip.draw(g);
		for (Projectile tile : project) {
			tile.draw(g);
		}
		for (Alien l : al) {
			l.draw(g);
		}
	}

	void addProjectile(Projectile pro) {
		project.add(pro);
	}

	void addAlien(Alien toy) {
		al.add(toy);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.width), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	void purgeObjects() {
		for (int i = 0; i < project.size(); i++) {
			if (!project.get(i).isAlive) {
				project.remove(i);
			}
		}
		for (int n = 0; n < al.size(); n++) {
			if (!al.get(n).isAlive) {
				al.remove(n);
			}
		}
	}

	void checkCollision() {
		for (Alien barn : al) {
			if (dip.collisionBox.intersects(barn.collisionBox)) {
				dip.isAlive = false;
			} else {
				for (Projectile till : project) {
					if (till.collisionBox.intersects(barn.collisionBox)) {
						till.isAlive = false;
						barn.isAlive = false;
						score++;
					}
				}
			}
		}
	}
	int getScore(){
		return score;
	}
}
