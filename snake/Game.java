package snake;

import java.util.Arrays;
import java.util.Iterator;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Game {
	
	public static final int GAMESIZE = 500;
	public static final int PIXELSIZE = 25;

	private Body snake = new Body();
	private Apple apple = new Apple();
	
	private String move;
	private String lastMove;
	private String nextMove = "right";
	
	private int speed = 10;
	private static int score;
	
	private boolean appleIsEaten;

	
	public void main() {
		snake.moveDirection(move);
		setLastMove(move);
		
		if (apple.isEaten(snake.getHead().getX(),snake.getHead().getY())) {
			score += 1;
			appleSpawn();
		}
	}
	private void appleSpawn() {//Flytter eplet når det blir spist og hindrer eplet i å bli plassert på slangen - og evt plasseres på samme koordinat to ganger.
		setAppleIsEaten(true);
		apple.moveApple();
		Iterator<Rectangle> it = snake.getBody().iterator();
		while (it.hasNext()) {
			Rectangle bodypart = it.next();
			if (apple.getX() == bodypart.getX() + (double) (PIXELSIZE) / 2 && apple.getY() == bodypart.getY() + (double) (PIXELSIZE) / 2) {
				apple.getValidAppleCoordinates().get(apple.getX()).remove(apple.getY());
				if (apple.getValidAppleCoordinates().get(apple.getX()).size() == 0) {
						apple.getValidAppleCoordinates().remove(apple.getX());
				}
				appleSpawn();
			}
		}
		apple.resetValidAppleCoordinates();
	}
	
	public void setup() {//Klargjør diverse til start av spill
		setScore(0);
		snake = new Body();
		apple = new Apple();
		appleSpawn();
		setAppleIsEaten(false);
		setMove("right");
	}
	
	public void keyAssistant(KeyCode keyCode) {//Kobler hva input fra keyBoard skal gjøre med bevegelse
		if (keyCode == KeyCode.RIGHT && lastMove != "left" && move != "pause") {
			setMove("right");
		}
		else if (keyCode == KeyCode.LEFT && lastMove != "right" && move != "pause") {
			setMove("left");
		}
		else if (keyCode == KeyCode.UP && lastMove != "down" && move != "pause") {
			setMove("up");
		}
		else if (keyCode == KeyCode.DOWN && lastMove != "up" && move != "pause") {
			setMove("down");
		}
		else if (keyCode == KeyCode.SPACE && move != "pause" ) {
			setNextMove(move);
			setMove("pause");
		} 
		else if (keyCode == KeyCode.SPACE && move == "pause") {
			setMove(nextMove);
		}
	}
	
	public String getMove() {
		return move;
	}
	public String getLastMove() {
		return lastMove;
	}
	public String getNextMove() {
		return nextMove;
	}
	public void setMove(String move) {
		if (!Arrays.asList(snake.getValidMoves()).contains(move)) {
			ErrorMessenger.notifyObservers("Move not valid");
			return;
		}
		this.move = move;
	}
	public void setNextMove(String nextMove) {
		if (!Arrays.asList(snake.getValidMoves()).contains(nextMove)) {
			ErrorMessenger.notifyObservers("Next move not valid");
			return;
		}
		this.nextMove = nextMove;
	}
	public void setLastMove(String lastMove) {
		if (!Arrays.asList(snake.getValidMoves()).contains(lastMove)) {
			ErrorMessenger.notifyObservers("Next move not valid");
			return;
		}
		this.lastMove = lastMove;
	}
	
	public Apple getApple() {
		return apple;
	}
	public Body getSnake() {
		return snake;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		if (!(speed == 5 || speed == 10 || speed == 20)) {
			ErrorMessenger.notifyObservers("Speed must be 5, 10 or 20");
			return;
		}
		this.speed = speed;
	}
	public static int getScore() {
		return score;
	}
	public void setScore(int Score) {
		if (score < 0) {
			ErrorMessenger.notifyObservers("Score should be positive");
			return;
		}
		score = Score;
	}
	
	public void setAppleIsEaten(boolean state) {
		appleIsEaten = state;
	}
	public boolean getAppleIsEaten() {
		return appleIsEaten;
	}
}
