package View;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import controller.ChallengesController;
import controller.HomeController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChallengerPane extends ScrollPane{
	private ArrayList<ChallengerPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	private ChallengesController cc;
	
	
	
	public ChallengerPane(HomeController self) {
		cc = new ChallengesController(self);
		playerLine = new ArrayList<ChallengerPlayerLinePane>();
		
		setChallenges();
		
		
		
		
		
		
		
		
		
		
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);
	}
	public void addStatusLine(String name, String status) {
		playerLine.add(new ChallengerPlayerLinePane(name, status));
	}
	
	private void setChallenges() {
		for (Map.Entry challenge : cc.getChallenged().entrySet()) {
			addStatusLine(challenge.getKey().toString(), challenge.getValue().toString());
		}
	}

}
