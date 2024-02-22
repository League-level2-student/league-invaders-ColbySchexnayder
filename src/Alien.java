import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject{

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		if (needImage) {
			loadImage("alien.png");
		}
	}

	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		}
		else {
			g.setColor(Color.yellow);
			g.fillRect(x, y, width, height);
		}
	}
	
	public void update() {
		super.update();
		y += speed;
		if (y > LeagueInvaders.HEIGHT) {
			isActive = false;
		}
	}
}
