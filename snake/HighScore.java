package snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HighScore implements FileHandling {
	
	public HighScore(int score, File file) {
		List<Integer> highScore = getHighScore(score,file);
		try {
			new FileWriter(file, false).close();
		} catch (IOException e) {
			ErrorMessenger.notifyObservers("Error when writing to file");
		}
		write(file, highScore);
		
	}
	
	private List<Integer> getHighScore(int score, File file) {//Returnerer sortert liste fra filen etter score er lagt til
		List<Integer> highScoreList = read(file);
		if (score < 0) {
			ErrorMessenger.notifyObservers("Score cannot be negative");
			return highScoreList;
		}
		highScoreList.add(score);
		Collections.sort( highScoreList, Collections.reverseOrder());
		if (highScoreList.size() > 5) {
			for (int i = 5; i < highScoreList.size(); i++) {
				highScoreList.remove(i);
			}
		}
		return highScoreList;
	}

	@Override
	public void write(File file, List<Integer> message) {
		if (message.size() > 5) {
			ErrorMessenger.notifyObservers("Highscorelist is too long");
			return;
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			for (int number : message) {
				writer.write(number + "\n");
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			ErrorMessenger.notifyObservers("Could not find file");
		}
	}

	@Override
	public List<Integer> read(File file) {//Returnerer fil som en liste
		List<Integer> highScoreList = new ArrayList<>();
		Scanner reader;
		try {
			reader = new Scanner(file);			
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				try {
					int i = Integer.parseInt(line);
					highScoreList.add(i);
				} catch (NumberFormatException e) {
					ErrorMessenger.notifyObservers("Line in file is string, line deleted");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			try {
				file.createNewFile();
				ErrorMessenger.notifyObservers("Could not find file, new file was created");
			} catch (IOException e1) {
				ErrorMessenger.notifyObservers("Could not find file, could not create new file");
			}
		}
		return highScoreList;
	}
	@Override
	public void deleteContent(File file) {
		try {
			new FileWriter(file,false).close();
		} catch (IOException e) {
			ErrorMessenger.notifyObservers("Could not reset highscore");
		}
	}
}
	
