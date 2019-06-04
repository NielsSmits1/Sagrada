package View;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LeaderboardPlayerLinePane extends HBox {
	private Label playerName;
	private Label playerText;
	private Label playerWins;
	private Label playerGames;
	private Label gameId;
	private Label gameText;
	private boolean highlight;

	public LeaderboardPlayerLinePane(String name) {
		playerName = new Label();

		this.setPrefSize(300, 30);
		playerName.setText(name);

		this.getChildren().setAll(playerName);
	}

	public LeaderboardPlayerLinePane(int game) {
		gameText = new Label();
		gameId = new Label();
		this.setPrefSize(300, 30);
		gameText.setText("Gamenummer: ");
		gameId.setText("" + game + "");

		this.getChildren().setAll(gameText, gameId);
	}

	public LeaderboardPlayerLinePane(int game, boolean self) {
		gameText = new Label();
		gameId = new Label();
		highlight = self;
		if (highlight == true) {
			this.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		}

		this.setPrefSize(300, 30);
		gameText.setText("Gamenummer: ");
		gameId.setText("" + game + "");

		this.getChildren().setAll(gameText, gameId);
	}

	public LeaderboardPlayerLinePane(String name, String amountGames) {
		playerName = new Label();
		playerText = new Label();
		playerGames = new Label();

		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerText.setText(" heeft aantal keren gespeeld: ");
		playerGames.setText(amountGames);

		this.getChildren().setAll(playerName, playerText, playerGames);
	}

	public LeaderboardPlayerLinePane(String name, int amountWins) {
		playerName = new Label();
		playerText = new Label();
		playerWins = new Label();

		this.setPrefSize(300, 30);
		playerName.setText(name);
		playerText.setText(" heeft aantal keren gewonnen: ");
		playerWins.setText(Integer.toString(amountWins));

		this.getChildren().setAll(playerName, playerText, playerWins);
	}

	public Label getPlayerName() {
		return playerName;
	}

	public void setPlayerName(Label playerName) {
		this.playerName = playerName;
	}

	public Label getPlayerText() {
		return playerText;
	}

	public void setPlayerText(Label playerText) {
		this.playerText = playerText;
	}

	public Label getPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(Label playerWins) {
		this.playerWins = playerWins;
	}

	public Label getPlayerGames() {
		return playerGames;
	}

	public void setPlayerGames(Label playerGames) {
		this.playerGames = playerGames;
	}

	public Label getGameId() {
		return gameId;
	}

	public void setGameId(Label gameId) {
		this.gameId = gameId;
	}

	public Label getGameText() {
		return gameText;
	}

	public void setGameText(Label gameText) {
		this.gameText = gameText;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

}