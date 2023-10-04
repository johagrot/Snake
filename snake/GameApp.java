package snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {

	private static Stage stage;
	private static Scene scene;
	
	
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		setScene(new Scene(FXMLLoader.load(getClass().getResource("Game.fxml"))));
		show();
	}
	
	public static void show() {
		stage.setScene(scene);
		stage.setTitle("Snake");
		stage.show();
	}
	
	public static Stage getStage() {
		return stage;
	}
	public static Scene getScene() {
		return scene;
	}
	public static void setScene(Scene Scene) {
		scene = Scene;
	}
	public static void main(String[] args) {
		launch(args);
	}
}

