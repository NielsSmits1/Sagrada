package View;

import controller.ChallengesController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChallengesPlayerLinePane extends HBox {
	private Label playerName;
	private Button accept;
	private Button decline;
	private ChallengesController cc = new ChallengesController(); 

	public ChallengesPlayerLinePane(String name) {
		playerName = new Label();
		accept = new Button("Accepteer");
		decline= new Button("Weiger");
		
		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerName.setPrefSize(100, 30);
		accept.setPrefSize(100, 30);
		decline.setPrefSize(100, 30);
		this.getChildren().setAll(playerName, accept, decline);
		accept.setOnAction(e -> cc.acceptChallenge());
		decline.setOnAction(e -> cc.declineChallenge());

	
	}

}
