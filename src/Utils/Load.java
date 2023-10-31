package Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import Game.Game;

public class Load {

	public static final String PlayButton = "/icons/PlayButton.png";
	public static final String SettingsButton = "/icons/Settings.png";
	public static final String Menubar = "/icons/MenuBar.png";
	public static final String LevelButton = "/icons/Level.png";
	public static final String Logo = "/icons/Logo.png";
	
	public static final String Level1 = "/levels/1.png";
	public static final String Level2 = "/levels/2.png";
	public static final String Level3 = "/levels/3.png";
	public static final String Level4 = "/levels/4.png";
	public static final String Level5 = "/levels/5.png";

	public static BufferedImage LoadImage(String file) {
		BufferedImage img = null;
		InputStream link = Load.class.getResourceAsStream(file);
		try {
			img = ImageIO.read(link);
		} catch (IOException e) {
			if (Game.debug) {
				System.err.println("Can't read image: " + file);
				e.printStackTrace();
			}
		}
		return img;
	}

}
