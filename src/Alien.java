import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject{

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}
	
	public void update() {
		y += speed;
		if (y > LeagueInvaders.HEIGHT) {
			isActive = false;
		}
	}
}
