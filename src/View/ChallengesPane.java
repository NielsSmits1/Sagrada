package View;



import java.util.ArrayList;

import controller.ChallengesController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChallengesPane extends ScrollPane {
	
	private ArrayList<ChallengesPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	private ChallengesController cc;

	
	public ChallengesPane() {
		cc = new ChallengesController();
		playerLine = new ArrayList<ChallengesPlayerLinePane>();
		

		getChallengedPlayerName();
	
		
		
		addChallengesLine("Please");
		addChallengesLine("Send");
		addChallengesLine("Help");
		addChallengesLine("ASAP");
	

		
		
		
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);
	}
	
	public void addChallengesLine(String name) {
		
		playerLine.add(new ChallengesPlayerLinePane(name));
		
		
	}
	
	public void getChallengedPlayerName() {
		cc.checkChallengedPlayer();
		
		
		
	}

}
