import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocket;
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Random random = new Random();
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	public void addProjectile() {
		projectiles.add(new Projectile(rocket.x, rocket.y, 10, 10));
	}
	
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	public void update() {
		rocket.update();
		for (Alien a : aliens) {
			a.update();
		}
		for (Projectile p : projectiles) {
			p.update();
		}
	}
	
	public void draw(Graphics g) {
		rocket.draw(g);
		for (Alien a : aliens) {
			a.draw(g);
		}
		for (Projectile p : projectiles) {
			p.draw(g);
		}
	}
	
	public void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (!aliens.get(i).isActive) {
				aliens.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isActive) {
				projectiles.remove(i);
				i--;
			}
		}
	}
}
