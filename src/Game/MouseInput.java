package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameState.GameState;

public class MouseInput implements MouseListener, MouseMotionListener{

	private GamePanel gamePanel;
	
	public MouseInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(GameState.state) {
		case MENU:
			break;
		case PLAYING: gamePanel.getPlaying().mouseMoved(e);
			break;
		case SETTING:
			break;
		case QUIT:
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(GameState.state) {
		case MENU:	gamePanel.getMenu().mouseClicked(e);
			break;
		case PLAYING: gamePanel.getPlaying().mouseClicked(e);
			break;
		case SETTING:
			break;
		case QUIT:
			break;
		default:
			break;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
