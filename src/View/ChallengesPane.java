package View;



import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChallengesPane extends ScrollPane {
	
	private ChallengesPlayerLine playerLine;
	private VBox playerColumn = new VBox();
//	private HBox playerLine = new HBox();
//	private Label playerName = new Label();
//	private Button accept = new Button("Accepteer");
//	private Button decline = new Button("Weiger");

	
	public ChallengesPane() {
		
		this.setHbarPolicy(getHbarPolicy().AS_NEEDED); // Set NEVER when done
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

		addChallengesLine();
		addChallengesLine();
		
	
	
//		playerLine.setPrefSize(300,30);
//		playerName.setText("playername");
//		playerName.setPrefSize(100, 30);
//		accept.setPrefSize(100, 30);
//		decline.setPrefSize(100, 30);
//		playerLine.getChildren().setAll(playerName,accept,decline);
		
//		this.setContent(playerLine2);
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
	}
	
	public void addChallengesLine() {
		playerLine = new ChallengesPlayerLine();
		
	}

}
