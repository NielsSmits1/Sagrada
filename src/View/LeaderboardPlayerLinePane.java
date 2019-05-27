package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LeaderboardPlayerLinePane extends HBox{
	
	private Label playerName;
	private Label playerText;
	private Label playerWins;
	private Label playerGames;
	private Label gameId;
	private Label gameText;
	
	

	public LeaderboardPlayerLinePane (String name) {
		playerName = new Label();
		
		this.setPrefSize(300, 30);
		playerName.setText(name);
		
		this.getChildren().setAll(playerName);
	}
	public LeaderboardPlayerLinePane(int game) {
//		cp = new ChallengesPane();
		gameText = new Label();
		gameId = new Label();
	
	
		
		this.setPrefSize(300, 30);
		gameText.setText("Gamenummer: ");
		gameId.setText(""+ game +"");
		
		this.getChildren().setAll(gameText, gameId);
	}
	public LeaderboardPlayerLinePane (String name,String amountGames) {
		playerName = new Label();
		playerText = new Label();
		playerGames = new Label();
		
		
		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerText.setText(" heeft aantal keren gespeeld: ");
		playerGames.setText(amountGames);
		
		this.getChildren().setAll(playerName, playerText, playerGames);
	}

	public LeaderboardPlayerLinePane (String name,int amountWins) {
		playerName = new Label();
		playerText = new Label();
		playerWins = new Label();
		
		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerText.setText(" heeft aantal keren gewonnen: ");
		playerWins.setText(Integer.toString(amountWins));
		
		this.getChildren().setAll(playerName, playerText, playerWins);
	}
		

}