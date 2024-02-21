import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame = new JFrame();
	GamePanel panel = new GamePanel();
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	public LeagueInvaders() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(panel);
		frame.addKeyListener(panel);
	}
	
	public static void main(String[] args) {
		LeagueInvaders invaders = new LeagueInvaders();
	}
}
