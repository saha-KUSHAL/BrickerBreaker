package Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Game.Game;

public class Load {

	public static final String PlayButton = "PlayButton.png";
	public static final String SettingsButton = "Settings.png";
	public static final String Menubar = "MenuBar.png";
	public static final String LevelButton = "Level.png";
	public static BufferedImage LoadImage(String file) {
		BufferedImage img = null;
		InputStream link = Load.class.getResourceAsStream("/" + file);
		try {
			img = ImageIO.read(link); 
		}catch(IOException e) {
			if(Game.debug) {
				System.err.println("Can't read image: " + file);
				e.printStackTrace();
			}
		}
		return img;
	}
}
