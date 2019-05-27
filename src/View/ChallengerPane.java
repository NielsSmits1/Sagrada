package View;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChallengerPane extends ScrollPane{
	private ArrayList<ChallengesPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	
	
	
	public ChallengerPane() {
		//cc = new ChallengesController(self);
		playerLine = new ArrayList<ChallengesPlayerLinePane>();
		
		setLayout();
	}
	public void addStatusLine(String name, String status) {
		playerLine.add(new ChallengesPlayerLinePane(name, status));
		System.out.println(name + status);
	}
	public void setLayout() {
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	public void setPlayerLines(ArrayList<ChallengesPlayerLinePane> challengesPL) {
		this.playerLine = challengesPL;
		this.setLayout();
	}

}
