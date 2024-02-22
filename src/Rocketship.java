import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject{
	
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	
	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
			loadImage("rocket.png");
		}
	}
	
	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		}
		else {
			g.setColor(Color.blue);
			g.fillRect(x, y, width, height);
		}
	}
	
	public void update() {
		super.update();
		if (up && y-speed >= 0) {
			y -= speed;
		}
		if (down && y + speed <= LeagueInvaders.HEIGHT - height) {
			y += speed;
		}
		if (left && x - speed >= 0) {
			x -= speed;
		}
		if (right && x + speed <= LeagueInvaders.WIDTH - width) {
			x += speed;
		}
	}
	
	public Projectile getProjectile() {
		return new Projectile(x+width/2, y, 10, 10);
	}
}
