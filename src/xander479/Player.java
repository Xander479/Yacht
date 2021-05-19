package xander479;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Player {
	private static Map<String, Integer> scores;
	private String name;
	private int score;
	private boolean isFinished;
	
	public Player(String name) {
		this.name = name;
		synchronized(scores) {
			if(scores == null) {
				try {
					scores = getScoreMap();
				}
				catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public void setHighScore(int score) {
		scores.put(name,  score);
		try {
			saveScoreMap();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getHighScore() {
			return scores.get(name);
	}
	
		@SuppressWarnings("unchecked")
	private Map<String, Integer> getScoreMap() throws ClassNotFoundException, FileNotFoundException, IOException {
		File file = new File("scores.data");
		if(!file.exists()) throw new FileNotFoundException();
		
		Map<String, Integer> scoreMap = null;
		try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			scoreMap = (HashMap<String, Integer>) in.readObject();
		}
		if(scoreMap == null) {
			throw new ClassCastException();
		}
		scores = scoreMap;
		return scoreMap;
	}
	
	private void saveScoreMap() throws FileNotFoundException, IOException {
		File file = new File("scores.data");
		if(file.exists()) file.delete();
		file.createNewFile();
		try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(scores);
		}
	}
}
