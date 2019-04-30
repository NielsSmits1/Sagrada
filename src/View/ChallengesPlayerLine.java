package View;

import controller.ChallengesController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChallengesPlayerLine extends HBox {
	private Label playerName = new Label();
	private Button accept = new Button("Accepteer");
	private Button decline= new Button("Weiger");
	private ChallengesController cc = new ChallengesController(); 

	public ChallengesPlayerLine() {

		this.setPrefSize(300, 30);
		playerName.setText("playername");
		playerName.setPrefSize(100, 30);
		accept.setPrefSize(100, 30);
		decline.setPrefSize(100, 30);
		this.getChildren().setAll(playerName, accept, decline);
		accept.setOnAction(e -> cc.acceptChallenge());
		decline.setOnAction(e -> cc.declineChallenge());

	
	}

}
