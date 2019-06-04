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
	private Label gameId;

	public ChallengesPlayerLinePane(String name) {
		differentPlayer = new Label();
		accept = new Button("Accepteer");
		decline = new Button("Weiger");
		this.playerName = name;
		this.setPrefSize(300, 30);
		differentPlayer.setText(playerName);
		differentPlayer.setPrefSize(100, 30);
		accept.setPrefSize(100, 30);
		decline.setPrefSize(100, 30);
		this.getChildren().setAll(differentPlayer, accept, decline);
	}

	public ChallengesPlayerLinePane(String name, String status, int i) {
		playerLabel = new Label();
		playerStatus = new Label();
		gameId = new Label();
		this.setPrefSize(300, 30);
		playerLabel.setText(name);

		gameId.setText(Integer.toString(i) + ": ");
		playerStatus.setText(status);

		this.getChildren().setAll(gameId, playerLabel, extraText, playerStatus);
	}

	public ChallengesPlayerLinePane(String name, String status) {
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

}
