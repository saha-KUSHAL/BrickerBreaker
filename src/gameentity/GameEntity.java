package gameentity;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface GameEntity {   
    public void update();

    public void render(Graphics2D g);

    public Rectangle getHitbox();

    public void mouseClicked(MouseEvent e);

    public void mouseMoved(MouseEvent e);
}