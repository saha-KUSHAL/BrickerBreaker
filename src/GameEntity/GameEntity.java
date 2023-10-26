package GameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface GameEntity {
		public void update();
		public void render(Graphics2D g);
		public Rectangle getHitbox();
}