package snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Body {
	
	private int bodySize = Game.PIXELSIZE;
	private int x,y;
	private List<Rectangle> body = new ArrayList<>();
	
	private Rectangle head, bodypart, tail;
	
	private Color darkGreen = Color.rgb(0,150,0);
	private Color green = Color.rgb(0,255,0);
	
	private final String[] validMoves = {"right", "left", "up", "down", "pause"};
	
	public Body() {
		head = new Rectangle(2*bodySize,Game.GAMESIZE / 2,bodySize,bodySize);
		head.setFill(darkGreen);
		body.add(head);
		for (int i = 1; i < 3; i++ ) {
			bodypart = new Rectangle(2*bodySize - i*bodySize,Game.GAMESIZE / 2,bodySize,bodySize);
			bodypart.setFill(green);
			body.add(bodypart);
		}
		x = (int) getTail().getX();
		y = (int) getTail().getY();
	}
	public Rectangle addBodypart() {
		
		int secondLastX = (int) body.get(body.size()-2).getX();
		int secondLastY = (int) body.get(body.size()-2).getY();
		
		if (x < secondLastX) { // bevegelse mot hyre i hale
			x -= bodySize;
		}
		else if (x > secondLastX) { //venstre
			x += bodySize;
		}
		else if (y > secondLastY) { //opp
			y += bodySize;
		} 
		else if (y < secondLastY) { //ned
			y -= bodySize;
		} 
		tail = new Rectangle(x,y,bodySize,bodySize);
		body.add(tail);
		tail.setFill(green);
		return tail;
	}
	
	public boolean isGameOver() {
		if (getHead().getX() > Game.GAMESIZE - bodySize || getHead().getX() < 0 || getHead().getY() > Game.GAMESIZE - bodySize || getHead().getY() < 0 ) {
			return true;
		}
		else {
			for (int i = 1; i < body.size(); i++) {
				if (getHead().getX() == body.get(i).getX() && getHead().getY() == body.get(i).getY()) {
					return true;
				}
			}
			return false;
		}
	}
	public void moveDirection(String direction) {//Beveger slange på brettet ved å fjerne siste element og legge til først i lista
		
		if (!Arrays.asList(validMoves).contains(direction)) {
			ErrorMessenger.notifyObservers("Move not valid");
			return;
		}
		
		Rectangle moveRectangle = getTail();
		getHead().setFill(green);
		getTail().setFill(darkGreen);
		
		if (direction == "right") {
			moveRectangle.setX(getHead().getX() + (double) bodySize);
			moveRectangle.setY(getHead().getY());
		}
		else if (direction == "left") {
			moveRectangle.setX(getHead().getX() - (double) bodySize);
			moveRectangle.setY(getHead().getY());
		}
		else if (direction == "up") {
			moveRectangle.setX(getHead().getX());
			moveRectangle.setY(getHead().getY() - (double) bodySize);
		}
		else if (direction == "down") {
			moveRectangle.setX(getHead().getX());
			moveRectangle.setY(getHead().getY() + (double) bodySize);
		}
		else if (direction == "pause"){
			return;
		}
		body.remove(body.size()-1);
		body.add(0, moveRectangle);
		x = (int) getTail().getX();
		y = (int) getTail().getY();
	}
	
	public Rectangle getHead() {
		return body.get(0);
	}
	public Rectangle getTail() {
		return body.get(body.size()-1);
	}
	public List<Rectangle> getBody() {
		return body;
	}
	public String[] getValidMoves() {
		return validMoves;
	}
}
