package air_time_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import javazoom.jl.player.advanced.AdvancedPlayer;

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
	Song gamersong = new Song("UltimateBattle.mp3");
	Song subaluwa = new Song("Subaluwa.mp3");
	Song oneup = new Song("1up.mp3");
	Song fire = new Song("Ignite.ogx");
	Song hit = new Song("Ignite.ogx");
	Song main = new Song("Canada.mp3");

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
			powerSpawnTime = 7500;
		} else if(hours == 1) {
			enemySpawnTime = 250;
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
				if(hours<=2) {
				subaluwa.play();
				} else {
				oneup.play();
				}
				yeet.isAlive = false;
				projectileCounter++;
			}
		}
	}
	class Song {

		private int duration;
		private String songAddress;
		private AdvancedPlayer mp3Player;
		private InputStream songStream;

		/**
		 * Songs can be constructed from files on your computer or Internet
		 * addresses.
		 * 
		 * Examples: <code> 
		 * 		new Song("everywhere.mp3"); 	//from default package 
		 * 		new Song("/Users/joonspoon/music/Vampire Weekend - Modern Vampires of the City/03 Step.mp3"); 
		 * 		new	Song("http://freedownloads.last.fm/download/569264057/Get%2BGot.mp3"); 
		 * </code>
		 */
		public Song(String songAddress) {
			this.songAddress = songAddress;
		}

		public void play() {
			loadFile();
			if (songStream != null) {
				loadPlayer();
				startSong();
			} else
				System.err.println("Unable to load file: " + songAddress);
		}

		public void setDuration(int seconds) {
			this.duration = seconds * 100;
		}

		public void stop() {
			if (mp3Player != null)
				mp3Player.close();
		}

		private void startSong() {
			Thread t = new Thread() {
				public void run() {
					try {
						if (duration > 0)
							mp3Player.play(duration);
						else
							mp3Player.play();
					} catch (Exception e) {
					}
				}
			};
			t.start();
		}

		private void loadPlayer() {
			try {
				this.mp3Player = new AdvancedPlayer(songStream);
			} catch (Exception e) {
			}
		}

		private void loadFile() {
			if (songAddress.contains("http"))
				this.songStream = loadStreamFromInternet();
			else
				this.songStream = loadStreamFromComputer();
		}

		private InputStream loadStreamFromInternet() {
			try {
				return new URL(songAddress).openStream();
			} catch (Exception e) {
				return null;
			}
		}

		private InputStream loadStreamFromComputer() {
			try {
				return new FileInputStream(songAddress);
			} catch (FileNotFoundException e) {
				return this.getClass().getResourceAsStream(songAddress);
			}
		}
	}

}
