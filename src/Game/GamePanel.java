package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private  MouseInput mouseInput;
	private Menu menu;
	private Playing playing;

	public GamePanel() {
		setPreferredSize(new Dimension(Game.Width, Game.Hight));
		setDoubleBuffered(true);
		setBackground(new Color(43, 60, 78));
		
		mouseInput = new MouseInput(this);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		menu = new Menu();
		playing = new Playing();
	}

	protected void update() {
		switch(GameState.state) {
		case MENU:	menu.update();
			break;
		case PLAYING:	playing.update();
			break;
		case SETTING:
			break;
		case QUIT:
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ((Graphics2D) g);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		switch(GameState.state) {
		case MENU:	menu.render(g2);
			break;
		case PLAYING:	playing.render(g2);
			break;
		case SETTING:
			break;
		case QUIT:
		}
			g.dispose();
	}
	
	protected Menu getMenu() {
		return menu;
	}
	
	protected Playing getPlaying() {
		return playing;
	}
}
