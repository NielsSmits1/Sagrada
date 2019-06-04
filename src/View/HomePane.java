package View;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class HomePane extends Pane{
	private LeaderboardPane leaderboard;
	private ChallengerPane challenger;
	private ChallengesPane challenges;
	private SearchPlayerPane search;
	private Button players;
	private Button playersPlayed;
	private Button playersWins;
	private Button allGames;
	private Button allGamesDate;
	private Label lb;
	private VBox boxie;
	private Font f = new Font(20);
	private int x = 50;   //250;
	
	// just to test the gametabs.
	private Button gametab;

	
	public void  createHomePane() {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set boundaries to visible bounds of the main screen
        this.setPrefWidth(primaryScreenBounds.getWidth());
        this.setPrefHeight(primaryScreenBounds.getHeight()*1.1);
        
		//sets background_image
		this.setBackground(new Background(new BackgroundImage(new Image("/Resources/gameBackground.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));
	
		setPanes("Uw uitdagingen", challenges);
		
		x += 400;
		
		setPanes("Wie u heeft uitgedaagd", challenger);
		
		x += 400;
		
		setPanes("Zoek een speler", search);

		x += 150;

	
		x += 100;
		
		setPanes("Spelers/Games", leaderboard);
		
		x += 300;
		
		setButtons("Filter");

	}
	

	public HomePane(SearchPlayerPane searchPlayerPane, ChallengerPane challengerPane, ChallengesPane challengesPane, LeaderboardPane leaderboardPane) {
		search = searchPlayerPane;
		challenges = challengesPane;
		challenger = challengerPane;
		leaderboard = leaderboardPane;

		createHomePane();
	}

	private void setPanes(String text, SearchPlayerPane scr) {
		lb = new Label(text);
		lb.setTextFill(Color.WHITE);
		boxie = new VBox();
		scr.setPrefSize(250, 400);
		
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(250, 500);
		lb.setFont(f);
		
		boxie.getChildren().addAll(lb,scr);
		this.getChildren().add(boxie);
		
	}
	
	private void setButtons(String text) {
		Label lb = new Label(text);
		lb.setTextFill(Color.WHITE);
		boxie = new VBox();
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setMinSize(100, 300);
		lb.setFont(f);
		
		players = new Button("Spelers");
		playersPlayed = new Button("Aantal gespeeld");
		playersWins = new Button("Aantal gewonnen");
		allGames = new Button("Alle spellen");
		allGamesDate = new Button("Spellen op datum");
		
		players.setPrefSize(150, 30);
		playersPlayed.setPrefSize(150, 30);
		playersWins.setPrefSize(150, 30);
		allGames.setPrefSize(150, 30);
		allGamesDate.setPrefSize(150, 30);

		boxie.getChildren().addAll(lb, players, playersPlayed, playersWins, allGames, allGamesDate);
		this.getChildren().add(boxie);
	}
	

	public Button getAllGames() {
		return allGames;
	}


	public void setAllGames(Button allGames) {
		this.allGames = allGames;
	}


	public Button getAllGamesDate() {
		return allGamesDate;
	}


	public void setAllGamesDate(Button allGamesDate) {
		this.allGamesDate = allGamesDate;
	}


	private void setPanes(String text, ScrollPane scr) {
		Label lb = new Label(text);
		lb.setTextFill(Color.WHITE);
		boxie = new VBox();
		scr.setPrefSize(250, 400);
		
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(300, 500);
		lb.setFont(f);
		
		boxie.getChildren().addAll(lb,scr);
		this.getChildren().addAll(boxie);
	}


	public SearchPlayerPane getSearch() {
		return search;
	}
	public Button getPlayers() {
		return players;
	}


	public Button getPlayersPlayed() {
		return playersPlayed;
	}


	public Button getPlayersWins() {
		return playersWins;
	}


	public void setPlayers(Button players) {
		this.players = players;
	}


	public void setPlayersPlayed(Button playersPlayed) {
		this.playersPlayed = playersPlayed;
	}


	public void setPlayersWins(Button playersWins) {
		this.playersWins = playersWins;
	}
	
	public Button getGameTab() {
		return gametab;
	}
}
