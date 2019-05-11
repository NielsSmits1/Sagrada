package View;

import java.util.ArrayList;

import controller.ChallengesController;
import controller.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Challenge;

public class ChallengesPane extends ScrollPane {

	private ArrayList<ChallengesPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	private ChallengesController cc;
//	private Challenge challenge;

	public ChallengesPane() {
		playerLine = new ArrayList<ChallengesPlayerLinePane>();

		setChallenger();
		getChallengedPlayerName();

		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}

	public ChallengesPane(HomeController self) {
//		challenge = new Challenge();
		cc = new ChallengesController(self);
		playerLine = new ArrayList<ChallengesPlayerLinePane>();

		setChallenger();
		getChallengedPlayerName();

		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);
	}

	public void addChallengesLine(String name) {

		playerLine.add(new ChallengesPlayerLinePane(name));

	}

	public void getChallengedPlayerName() {
//		cc.checkChallengedPlayer();

	}

	private void setChallenger() {

		for (String name : cc.getChallengers()) {
			addChallengesLine(name);

//		for(ArrayList<Object> a: challenge.GetPlayerWithChallengedStatus()){
//			a.get(0);
//			addChallengesLine((String) a.get(0));			

		}

	}

	public void acceptChallenge(String name) {
		cc.acceptChallenge(name);

		
	}

	public void declineChallenge(String name) {
		cc.declineChallenge(name);

	}

}
