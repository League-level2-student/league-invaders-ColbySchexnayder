import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed = 0;
	boolean isActive = true;
	
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;
	
	Rectangle collisionBox;
	
	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		collisionBox = new Rectangle(x, y, width, height);
	}
	
	public void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				System.out.println("File not recognized");
			}
			
			needImage = false;
		}
	}
}
