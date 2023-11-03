package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import GameState.GameState;
import GameState.Level;
import GameState.Menu;
import GameState.Playing;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MouseInput mouseInput;
	private Menu menu;
	private Playing playing;
	private Level level;
	public GamePanel() {
		setPreferredSize(new Dimension(Game.Width, Game.Hight));
		setDoubleBuffered(true);
		setBackground(new Color(43, 60, 78));
		setFocusable(true);
		mouseInput = new MouseInput(this);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		menu = new Menu();
		playing = new Playing();
		level = new Level();
	}

	protected void update() {
		switch (GameState.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:{
			playing.update();
		}
			break;
		case SETTING:
			break;
		case QUIT:
		case LEVEL:
			level.update();
			break;
		default:
			break;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = ((Graphics2D) g);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		switch (GameState.state) {
		case MENU:
			menu.render(g2);
			break;
		case PLAYING:
			playing.render(g2);
			break;
		case SETTING:
			break;
		case QUIT:
		case LEVEL:
			level.render(g2);
			break;
		default:
			break;
		}
		g.dispose();
	}

	protected Menu getMenu() {
		return menu;
	}

	protected Playing getPlaying() {
		return playing;
	}
	protected Level getLevel() {
		return level;
	}
	
}
