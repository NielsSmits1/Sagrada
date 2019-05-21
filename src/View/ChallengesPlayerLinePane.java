package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChallengesPlayerLinePane extends HBox {
	
	private String playerName;
	private Label differentPlayer;
	private Button accept;
	private Button decline;
	private Label playerLabel;
	private Label playerStatus;
	private Label extraText = new Label(" zijn spelerstatus is: ");
//	private ChallengesPane cp;
//	private ChallengesController cc = new ChallengesController(); 

	public ChallengesPlayerLinePane(String name) {
//		cp = new ChallengesPane();
		
		differentPlayer = new Label();
		accept = new Button("Accepteer");
		decline= new Button("Weiger");
		this.playerName = name;
		
		this.setPrefSize(300, 30);
		differentPlayer.setText(playerName);
		differentPlayer.setPrefSize(100, 30);
		accept.setPrefSize(100, 30);
		decline.setPrefSize(100, 30);
		this.getChildren().setAll(differentPlayer, accept, decline);
//		accept.setOnAction(e -> this.acceptChallengeLine());
//		decline.setOnAction(e -> this.declineChallengeLine());
//		accept.setOnAction(e -> cp.acceptChallenge(playerName));
//		decline.setOnAction(e -> cp.declineChallenge(playerName));
	
	}
	public ChallengesPlayerLinePane (String name,String status) {
		playerLabel = new Label();
		playerStatus = new Label();
		
		this.setPrefSize(300, 30);
		playerLabel.setText(name);
		playerStatus.setText(status);
		
		this.getChildren().setAll(playerLabel, extraText, playerStatus);
	}
		
	public String getPlayerName() {
		return playerName;
	}

	public Button getAccept() {
		return accept;
	}

	public Button getDecline() {
		return decline;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setAccept(Button accept) {
		this.accept = accept;
	}

	public void setDecline(Button decline) {
		this.decline = decline;
	}


//	private void declineChallengeLine() {
//		cc.declineChallenge(playerName);
//		cp.declineChallenge(playerName);
//		
//		
//		
//		
//	}
//
//	private void acceptChallengeLine() {
//		cc.acceptChallenge(playerName);
//		cp.acceptChallenge(playerName);
//	
//	}

}
