package snake;

import java.io.File;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameController implements Controller {
	
	private Game game = new Game();
	private File file = new File("HighScore.txt");
	private Background background;

	@FXML
	private Button start;
	@FXML
	private MenuButton speed;
	@FXML 
	private Pane root, errorPane;
	@FXML
	private Text errorMessage;
	@FXML
	private Label scoreLabel;
	
	
	@FXML
	public void initialize() {
		game.setup();
		ErrorMessenger.addObserver(this);
		background = new Background();
		
		for (Rectangle backgroundPart : background.getBackground()) {
			root.getChildren().add(backgroundPart);
		}
		for (Rectangle bodypart : game.getSnake().getBody()) {
			root.getChildren().add(bodypart);
		}
		root.getChildren().add(game.getApple().getApple());
	}
	
	
	
	public void run() {//Kjører main i Game "speed" antall ganger i sekundet, og loader highScore etter spillet er over
		AnimationTimer timer = new AnimationTimer() {
			private long lastUpdate = 0;
			@Override
			public void handle(long now) {
				if (!game.getSnake().isGameOver() && now - lastUpdate > 1000000000/game.getSpeed() && game.getMove() != "pause") {
					game.main();
					onAppleIsEaten();
					lastUpdate = now;
				}
				else if (game.getSnake().isGameOver()) {
					this.stop();
					addHighScore();
					GameApp.show();
				}
			}
		};
		timer.start();
	}
	
	private void onAppleIsEaten() {//Oppdaterer score og legger til kroppsdel grafisk når eplet er spist
		if (game.getAppleIsEaten()) {
			scoreLabel.setText("Score: " + String.valueOf(Game.getScore()));
			root.getChildren().add(game.getSnake().addBodypart());
			game.setAppleIsEaten(false);
		}
	}
	private void addHighScore() {
		try {
			GameApp.setScene(new Scene(FXMLLoader.load(getClass().getResource("HighScore.fxml"))));
			ErrorMessenger.removeObserver(this);
		} catch (IOException e) {
			ErrorMessenger.notifyObservers("Could not load 'HighScore.fxml'");
		}
	}
	
	public void addKeyHandler(Scene scene) {//Lager kobling mellom tastaturinput og scene
		scene.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			game.keyAssistant(keyCode);
		});
	}
	@FXML
	public void onStart() {
		try {
			addKeyHandler(GameApp.getScene());
			run();
			start.setVisible(false);
			speed.setVisible(false);
			
		} catch (Exception e) {
			ErrorMessenger.notifyObservers("Could not start game");
		}
	}
	@FXML
	public void onFast() {
		game.setSpeed(20);
	}
	@FXML
	public void onMedium() {
		game.setSpeed(10);
	}
	@FXML
	public void onSlow() {
		game.setSpeed(5);
	}
	@Override
	public void notify(String message) {//Sender ut opp feilmelding i spillet dersom det skjer
		errorMessage.setText(message);
		root.setVisible(false);
		errorPane.setVisible(true);
	}
	public File getFile() {
		return file;
	}
	public Game getGame() {
		return game;
	}

	@Override
	public Text getErrorMessage() {
		return errorMessage;
	}
}
