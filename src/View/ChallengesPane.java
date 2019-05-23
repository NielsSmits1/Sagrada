package View;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChallengesPane extends ScrollPane {

	private ArrayList<ChallengesPlayerLinePane> playerLine = new ArrayList<ChallengesPlayerLinePane>();
	private VBox playerColumn = new VBox();

	public void copyArraylist(ArrayList<ChallengesPlayerLinePane> playerArray) {
		this.playerLine = playerArray;
	}

	public void addChallengesLine(String name) {

		playerLine.add(new ChallengesPlayerLinePane(name));

	}
	public void setLayout() {
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	public ArrayList<ChallengesPlayerLinePane> getPlayerLine() {
		return playerLine;
	}

	public void setPlayerLine(ArrayList<ChallengesPlayerLinePane> playerLine) {
		this.playerLine = playerLine;
	}
}

