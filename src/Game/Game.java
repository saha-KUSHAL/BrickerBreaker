package Game;

import java.sql.BatchUpdateException;

public class Game implements Runnable {

	public static int Width = 1080;
	public static int Hight = 720;

	private static final int UPS = 200;
	private static final int FPS = 120;

	public GameWindow gameWindow;
	private Thread gameThread;
	private GamePanel gamePanel;
	public static boolean debug;
	public Game(boolean debugState) {
		debug =debugState;
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gameLoop();
	}

	private void update() {
		gamePanel.update();
	}
	
	private void render() {
		gamePanel.repaint();
	}

	// GameLoop
	@Override
	public void run() {

		long currentTime, prevTime;

		prevTime = System.nanoTime();

		double deltaU = 0, deltaF = 0;
		double timePerUpdate = 1000000000 / UPS;
		double timePerFrame = 1000000000 / FPS;
		double lastFPSCheck = System.currentTimeMillis();
		int ups_count = 0;
		int fps_count = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			deltaF += (currentTime - prevTime) / timePerFrame;
			deltaU += (currentTime - prevTime) / timePerUpdate;

			prevTime = currentTime;
			if (deltaF >= 1) {
				fps_count++;
				render();
				deltaF--;
			}
			if (deltaU >= 1) {
				ups_count++;
				update();
				deltaU--;
			}
			if(debug)
			if (System.currentTimeMillis() - lastFPSCheck >= 1000) {
				lastFPSCheck = System.currentTimeMillis();
				System.out.println("FPS: " + fps_count + " | UPS: " + ups_count);
				fps_count = 0;
				ups_count = 0;
			}
		}

	}
	private void gameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
}
