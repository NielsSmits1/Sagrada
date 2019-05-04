package View;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import controller.HomeController;
import controller.LeaderboardController;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class LeaderboardPane extends ScrollPane {
	private ArrayList<LeaderboardPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	private LeaderboardController lc;

	public LeaderboardPane(HomeController self) {
		lc = new LeaderboardController(self);
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();

		setPlayers3();

		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}

	public void addPlayerNameLine(String name) {
		playerLine.add(new LeaderboardPlayerLinePane(name));
	}

	public void addPlayerNameLineWithAmountOfGamesPlayed(String name, String amountPlayed) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountPlayed));
	}
	public void addPlayerNameLineWithAmountOfGamesWon(String name, int amountWon) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountWon));
	}

	public void setPlayers() {
		for (String playerName : lc.getPlayers()) {
			addPlayerNameLine(playerName);
		}

	}

	public void setPlayers2() {
		for (Map.Entry playerGamesPlayed : lc.getPlayersFilteredByAmountOfGames().entrySet()) {
			addPlayerNameLineWithAmountOfGamesPlayed(playerGamesPlayed.getKey().toString(),
					playerGamesPlayed.getValue().toString());
		}

	}
	public void setPlayers3() {
		for (Map.Entry playerGamesPlayedWon : lc.getPlayersFilteredByAmountOfGamesWon().entrySet()) {
			addPlayerNameLineWithAmountOfGamesWon(playerGamesPlayedWon.getKey().toString(),
					(int) playerGamesPlayedWon.getValue());
		}

	}
}
