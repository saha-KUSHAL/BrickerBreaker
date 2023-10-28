package GameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public interface GameEntity {
	public void update();

	public void render(Graphics2D g);

	public Rectangle getHitbox();

	public void mouseClicked(MouseEvent e);

	public void mouseMoved(MouseEvent e);
}