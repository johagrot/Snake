package snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HighScoreController implements Controller {

	@FXML
	private Label score, HighScore1, HighScore2, HighScore3, HighScore4, HighScore5;
	@FXML
	private Text errorMessage, newHighScore;
	@FXML
	private Pane pane;
	@FXML
	private GridPane grid;
	
	private HighScore highScore;
	private List<Integer> scores;
	
	private GameController gameController = new GameController();
	
	private File file = new File("HighScore.txt");
	
	
	@FXML
	void initialize() throws FileNotFoundException, IOException {//Henter highscore fra fil og gjør det grafisk
		ErrorMessenger.addObserver(this);
		highScore = new HighScore(Game.getScore(), gameController.getFile());
		scores = highScore.read(file);
		score.setText(String.valueOf(Game.getScore()));
		HighScore1.setText(String.valueOf(scores.get(0)));
		
		if (score.getText().equals(HighScore1.getText())) {
			newHighScore.setText("Congratulations! New highscore!");
		}
		
		if (scores.size() > 1) {
			HighScore2.setText(String.valueOf(scores.get(1)));
		}
		if (scores.size() > 2) {
			HighScore3.setText(String.valueOf(scores.get(2)));
		}
		if (scores.size() > 3) {
			HighScore4.setText(String.valueOf(scores.get(3)));
		}
		if (scores.size() > 4) {
			HighScore5.setText(String.valueOf(scores.get(4)));
		}
	}
	@FXML
	public void onQuit() {
		System.exit(0);
	}
	@FXML
	public void onReplay() {//Restarter spillet
		try {
			gameController.getGame().setup();
			GameApp.setScene(new Scene(FXMLLoader.load(getClass().getResource("Game.fxml"))));
			gameController.addKeyHandler(GameApp.getScene());
			GameApp.show();
			ErrorMessenger.removeObserver(this);
		} catch (Exception e) {
			ErrorMessenger.notifyObservers("Could not restart game");
		}
	}
	@FXML
	public void onReset() {
		highScore.deleteContent(gameController.getFile());
	}
	
	@FXML
	public void onClose() {
		pane.setVisible(false);
		grid.setVisible(true);
	}

	@Override
	public void notify(String message) {//Sender ut feilmelding dersom det skjer
		errorMessage.setText(message);
		grid.setVisible(false);
		pane.setVisible(true);
	}
	@Override
	public Text getErrorMessage() {
		return errorMessage;
	}
}