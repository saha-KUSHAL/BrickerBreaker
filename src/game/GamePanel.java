package game;

import gamestate.*;
import gamestate.Menu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final MouseInput mouseInput;
    private final Menu menu;
    private final Playing playing;
    private final Level level;

    private final Settings settings;
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
        settings = new Settings();
    }

    protected void update() {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING: {
                playing.update();
            }
            break;
            case SETTING:
                settings.update();
                break;
            case LEVEL:
                level.update();
                break;
            case QUIT:
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
                settings.render(g);
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

    public Settings getSettings() {
        return settings;
    }
}
