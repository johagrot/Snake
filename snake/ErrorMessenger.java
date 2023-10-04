package snake;

import java.util.ArrayList;
import java.util.List;

public abstract class ErrorMessenger {

	private static List<Controller> observers = new ArrayList<>();
	
	public static void addObserver(Controller controller) {
		observers.add(controller);
	}
	public static void removeObserver(Controller controller) {
		observers.remove(controller);
	}
	
	public static void notifyObservers(String message) {//Varsler alle observatører om feil
		for (Controller controller : observers) {
			controller.notify(message);
		}
	}
	public static List<Controller> getObservers() {
		return observers;
	}
}
