package funktionen;

import java.util.Vector;

public class Spieler {

	private String playerName;
	private int points;
	
	public Spieler (String playerName, int points){
		this.playerName = playerName;
		this.points = points;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
