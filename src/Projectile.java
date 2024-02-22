import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{

	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
			loadImage("bullet.png");
		}
	}
	
	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		}
		else {
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);
		}
		
	}
	
	public void update() {
		super.update();
		y -= speed;
		if (y < 0) {
			isActive = false;
		}
	}
}
