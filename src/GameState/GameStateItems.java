package GameState;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class GameStateItems {
	protected Point mousePoint;
	protected boolean isMouseClicked;

	public void mouseClicked(MouseEvent e) {
		isMouseClicked = true;
		mousePoint = new Point(e.getX(), e.getY());
	}
}
