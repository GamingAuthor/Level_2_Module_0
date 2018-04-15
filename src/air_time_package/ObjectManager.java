package air_time_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Plane mantis;
	ArrayList<Zombie> zombo = new ArrayList<Zombie>();
	ArrayList<PowerUp> power = new ArrayList<PowerUp>();
	ArrayList<Projectile> project = new ArrayList<Projectile>();
	static int hours;
	long enemyTimer = 0;
	int enemySpawnTime = 2000;
	long powerTimer = 0;
	int powerSpawnTime = 10000;
	long hourTimer = 0;
	int projectileCounter = 0;

	ObjectManager(Plane plane) {
		this.mantis = plane;
	}

	void update() {
		mantis.update();
		for (Zombie z : zombo) {
			z.update();
		}
		for (PowerUp mario : power) {
			mario.update();
		}
		for (Projectile pro : project) {
			pro.update();
		}
	}

	void draw(Graphics g) {
		mantis.draw(g);
		for (Zombie oey : zombo) {
			oey.draw(g);
		}
		for (PowerUp crews : power) {
			crews.draw(g);
		}
		for (Projectile ninja : project) {
			ninja.draw(g);
		}
	}

	void addZombie(Zombie louis) {
		zombo.add(louis);
	}

	void addPowerUp(PowerUp palpatine) {
		power.add(palpatine);
	}

	void addProjectile(Projectile muselk) {
		project.add(muselk);
	}

	public void manageEnemies() {
		if(hours == 5) {
			enemySpawnTime = 2000;
		} else if (hours == 4) {
			enemySpawnTime = 1000;
		} else if(hours == 2) {
			enemySpawnTime = 500;
			powerSpawnTime = 5000;
		}
		
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addZombie(new Zombie(800, (new Random().nextInt(5) * 100) + 32, 50, 25));
			enemyTimer = System.currentTimeMillis();
		}
		if(hours<5) {
		if (System.currentTimeMillis() - powerTimer >= powerSpawnTime) {
			if(hours>2) {
			addPowerUp(new PowerUp(800, (new Random().nextInt(5) * 100) + 25, 50, 50));
			} else if(hours<=2) {
			addPowerUp(new PowerUp(800, (new Random().nextInt(5) * 100) + 25, 35, 50));
			}
			powerTimer = System.currentTimeMillis();
		}
		}
	}
	void keepTime() {
		if(System.currentTimeMillis() - hourTimer >= 24000) {
			System.out.println("YAY!");
			hourTimer = System.currentTimeMillis();
			hours--;
		}
	}

	void purgeObjects() {
		for (int i = 0; i < zombo.size(); i++) {
			if (!zombo.get(i).isAlive) {
				zombo.remove(i);
			}
		}
		for (int n = 0; n < power.size(); n++) {
			if (!power.get(n).isAlive) {
				power.remove(n);
			}
		}
		for(int m = 0; m < project.size(); m++) {
			if(!project.get(m).isAlive) {
				project.remove(m);
			}
		}//MAKE JALAPENO BULLET
	}

	void checkCollision() {
		for (Zombie bill : zombo) {
			if (mantis.collisionBox.intersects(bill.collisionBox)) {
				mantis.isAlive = false;
			} else {
				for (Projectile alia : project) {
					if (alia.collisionBox.intersects(bill.collisionBox)) {
						if(hours>2) {
						alia.isAlive = false;
						}
						bill.isAlive = false;
					}
				}
			}
		}
		for (PowerUp yeet : power) {
			if (mantis.collisionBox.intersects(yeet.collisionBox)) {
				yeet.isAlive = false;
				projectileCounter++;
			}
		}
	}
}
