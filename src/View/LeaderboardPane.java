package View;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class LeaderboardPane extends ScrollPane {
	private ArrayList<LeaderboardPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	public LeaderboardPane() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		setLayout();
	}
	

	public void addPlayerNameLine(String name) {
		playerLine.add(new LeaderboardPlayerLinePane(name));
	
	}
	public void addPlayerNameLine(int number) {
		playerLine.add(new LeaderboardPlayerLinePane(number));
	
	}

	public void addPlayerNameLineWithAmountOfGamesPlayed(String name, String amountPlayed) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountPlayed));
	}
	public void addPlayerNameLineWithAmountOfGamesWon(String name, int amountWon) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountWon));
	}

	public void setPlayersName(ArrayList<LeaderboardPlayerLinePane> lplp) {
		playerLine = lplp;
		setLayout();
	}
	public void setLayout() {
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
}
