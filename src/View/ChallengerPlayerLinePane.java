package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChallengerPlayerLinePane extends HBox{
	private Label playerName;
	private Label playerStatus;
	private Label extraText = new Label(" zijn spelerstatus is: ");
	
	public ChallengerPlayerLinePane (String name,String status) {
		playerName = new Label();
		playerStatus = new Label();
		
		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerStatus.setText(status);
		
		this.getChildren().setAll(playerName, extraText, playerStatus);
	}
		

}
