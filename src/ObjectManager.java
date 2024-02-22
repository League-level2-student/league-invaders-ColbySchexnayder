import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Random random = new Random();
	
	int score = 0;
	
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
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
		
		checkCollision();
		purgeObjects();
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
	
	public void checkCollision() {
		for (Alien alien : aliens) {
			if (rocket.collisionBox.intersects(alien.collisionBox)) {
				rocket.isActive = false;
				alien.isActive = false;
			}
			
			for (Projectile projectile : projectiles) {
				if (alien.collisionBox.intersects(projectile.collisionBox)) {
					alien.isActive = false;
					projectile.isActive = false;
					score+=100;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
		
	}
}
