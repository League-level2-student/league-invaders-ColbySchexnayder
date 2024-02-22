import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	
	int currentState = MENU;
	
	Font titleFont = new Font("Arial", Font.BOLD, 48);
	Font font = new Font("Arial", Font.PLAIN, 24);
	
	Timer frameDraw;
	Timer alienSpawn;
	
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager objectManager = new ObjectManager(rocket);
	
	BufferedImage background;
	boolean needImage = true;
	boolean gotImage = false;
	
	public GamePanel() {
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
				
		if (needImage) {
			loadImage("space.png");
		}
	}
	
	public void startGame() {
		
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
	}
	
	public void updateMenuState() {
		
	}
	
	public void updateGameState() {
		objectManager.update();
		
		if (!rocket.isActive) {
			currentState = END;
		}
	}
	
	public void updateEndState() {
		
	}
	
	public void drawMenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString("League Invaders", 50, 150);
		g.setFont(font);
		g.drawString("Press ENTER to start", 100, 400);
		g.drawString("Press SPACE for instructions", 70, 600);
	}
	
	public void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(background, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		}
		else {
			g.setColor(Color.black);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		objectManager.draw(g);
		
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("Score: "+objectManager.score, 20, 20);
	}
	
	public void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString("Game Over", 100, 400);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		}
		else if (currentState == GAME) {
			drawGameState(g);
		}
		else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (currentState == MENU) {
			updateMenuState();
		}
		else if (currentState == GAME) {
			updateGameState();
		}
		else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				rocket = new Rocketship(250, 700, 50, 50);
				objectManager = new ObjectManager(rocket);
				currentState = MENU;
			}
			else if (currentState == MENU) {
				currentState++;
				startGame();
			}
			else if (currentState == GAME) {
				currentState++;
			}
		}
		
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				rocket.up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				rocket.down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				rocket.left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rocket.right = true;
			}
			
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				objectManager.addProjectile(rocket.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				rocket.up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				rocket.down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				rocket.left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rocket.right = false;
			}
		}
	}
	
	public void loadImage(String imageFile) {
		if (needImage) {
			try {
				background = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				System.out.println("File not recognized");
			}
			
			needImage = false;
		}
	}
}











