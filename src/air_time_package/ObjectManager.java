package air_time_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


public class ObjectManager {
	Plane mantis;
	ArrayList<Zombie> zombo = new ArrayList<Zombie>();
	ArrayList<PowerUp> power = new ArrayList<PowerUp>();
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	ObjectManager(Plane plane){
		this.mantis = plane;
	}
	void update(){
		mantis.update();
		for(Zombie z : zombo) {
			z.update();
		}
		for(PowerUp mario : power) {
			mario.update();
		}
	}
	
	void draw(Graphics g){
		mantis.draw(g);
		for(Zombie oey : zombo) {
			oey.draw(g);
		}
		for(PowerUp crews : power) {
			crews.draw(g);
		}
	}
	void addZombie(Zombie louis) {
		zombo.add(louis);
	}
	void addPowerUp(PowerUp palpatine) {
		power.add(palpatine);
	}
	
	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addZombie(new Zombie(800, (new Random().nextInt(5)*100)+25, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}
	void purgeObjects() {
		for (int i = 0; i<zombo.size();i++) {
			if(!zombo.get(i).isAlive) {
				zombo.remove(i);
			}
		}
	}
	void checkCollision() {
		for(Zombie bill : zombo) {
			if(mantis.collisionBox.intersects(bill.collisionBox)) {
				mantis.isAlive = false;
			} else {
				
			}
		}
	}
}
