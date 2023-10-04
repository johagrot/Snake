package snake;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Background {
	
	private Color grey = Color.rgb(200,200,200), lightGrey = Color.rgb(227, 227, 227);
	private ArrayList<Rectangle> background = new ArrayList<>();
	private Rectangle greyRectangle, lightGreyRectangle;
	
	
	public Background() {
		for (int i = 0; i < Game.GAMESIZE; i += Game.PIXELSIZE) {
			if (i % (2*Game.PIXELSIZE) == 0) {
				for (int j = 0; j < Game.GAMESIZE; j += Game.PIXELSIZE) {
					if (j % (2*Game.PIXELSIZE) == 0) {
						greyRectangle = new Rectangle(i,j,Game.PIXELSIZE,Game.PIXELSIZE);
						greyRectangle.setFill(grey);
						background.add(greyRectangle);
					}
					else {
						lightGreyRectangle = new Rectangle(i,j, Game.PIXELSIZE,Game.PIXELSIZE);
						lightGreyRectangle.setFill(lightGrey);
						background.add(lightGreyRectangle);
					}
				}
			}
			else {
				for (int j = 0; j < Game.GAMESIZE; j += Game.PIXELSIZE) {
					if (j % (2*Game.PIXELSIZE) == 0) {
						lightGreyRectangle = new Rectangle(i,j,Game.PIXELSIZE,Game.PIXELSIZE);
						lightGreyRectangle.setFill(lightGrey);
						background.add(lightGreyRectangle);
					}
					else {
						greyRectangle = new Rectangle(i,j, Game.PIXELSIZE,Game.PIXELSIZE);
						greyRectangle.setFill(grey);
						background.add(greyRectangle);
					}
				}
			}
		}	
	}
	public ArrayList<Rectangle> getBackground() {
		return background;
	}
}
