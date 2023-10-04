package snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Apple {
	
	private Circle apple;
	private HashMap<Double, List<Double>> validAppleCoordinates = new HashMap<>(), copyValidAppleCoordinates = new HashMap<>();
	
	public Apple() {
		apple = new Circle(0,0,Game.PIXELSIZE / 2);
		setValidAppleCoordinates();
		moveApple();
		Color red = Color.rgb(255,0,0);
		apple.setFill(red);
	}

	public void moveApple() { //flytter eple til et gyldig posisjon
		
		double x = (1+(int)(Math.random()*Game.GAMESIZE / Game.PIXELSIZE - 1)-0.5)*Game.PIXELSIZE;
		int yIndex = (int) (Math.random()*validAppleCoordinates.size());

		apple.setCenterX(x);
		apple.setCenterY(validAppleCoordinates.get(x).get(yIndex));
	}
	
	public boolean isEaten(double xSnake, double ySnake) {
		if (xSnake < -Game.PIXELSIZE || xSnake > Game.GAMESIZE || ySnake < -Game.PIXELSIZE || ySnake > Game.GAMESIZE) {
			ErrorMessenger.notifyObservers("Coordinates not valid");
		}
		if ((double)(xSnake) + (double)(Game.PIXELSIZE) / 2 == getX() && (double)(ySnake) + (double)(Game.PIXELSIZE) / 2 == getY()) {
			return true;
		}
		return false;
	}
	
	private void setValidAppleCoordinates() {
		List<Double> yValues = new ArrayList<>();
		for (int i = 0; i < Game.GAMESIZE / Game.PIXELSIZE; i++) {
			for (int j = 0; j < 20; j++) {
				yValues.add(j*Game.PIXELSIZE + (double) Game.PIXELSIZE / 2);
			}
			validAppleCoordinates.put(i*Game.PIXELSIZE + (double) Game.PIXELSIZE / 2, yValues);
		}
		copyValidAppleCoordinates = validAppleCoordinates;
	}
	public void resetValidAppleCoordinates() {
		validAppleCoordinates = copyValidAppleCoordinates;
	}
	
	public Circle getApple() {
		return apple;
	}
	public double getX() {
		return apple.getCenterX();
	}
	public double getY() {
		return apple.getCenterY();
	}
	public HashMap<Double,List<Double>> getValidAppleCoordinates() {
		return validAppleCoordinates;
	}
}


