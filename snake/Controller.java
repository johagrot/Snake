package snake;

import javafx.scene.text.Text;

public interface Controller {

	void notify(String message);
	
	Text getErrorMessage();
}

