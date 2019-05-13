package View;

import controller.HomeController;
import controller.PlayerController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomePane extends Pane{
	private LeaderboardPane leaderboard;
	private ChallengerPane challenger;
	private ChallengesPane challenges;
	private SearchPlayerPane search;
	private Button players;
	private Button playersPlayed;
	private Button playersWins;
	private Label lb;
	private VBox boxie;
	private Font f = new Font(20);
	private int x = 250;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private PlayerController self;
	private MyScene scene;
	
	private HomeController home;
	
	public void  createHomePane(PlayerController self, MyScene scene) {
		home = new HomeController(self);
		this.self = self;
		this.scene = scene;
		
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		home = new HomeController(self);
		leaderboard = new LeaderboardPane(home);
		
		setPanes("Uw uitdagingen",challenges);
		
		x += 400;
		
		setPanes("Wie u heeft uitgedaagt", challenger);
		
		x += 400;
		
		setPanes("Zoek een speler", search);

		x += 150;

	
		x += 100;
		
		setPanes("Spelers", leaderboard);
		
		x += 300;
		setButtons("Filter");
		
		
		


	}
	

	public HomePane(SearchPlayerPane searchPlayerPane, ChallengerPane challengerPane, ChallengesPane challengesPane) {
		search = searchPlayerPane;
		challenges = challengesPane;
		challenger = challengerPane;

		createHomePane(self, scene);
	}



	private void setPanes(String text, SearchPlayerPane scr) {
		lb = new Label(text);
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
		boxie = new VBox();
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(100, 200);
		lb.setFont(f);
		

		players = new Button("Spelers");
		playersPlayed = new Button("Aantal gespeeld");
		playersWins = new Button("Aantal gewonnen");
		
		
		players.setPrefSize(200, 30);
		playersPlayed.setPrefSize(200, 30);
		playersWins.setPrefSize(200, 30);
		players.setOnAction(e -> leaderboard.setPlayers());
		playersPlayed.setOnAction(e -> leaderboard.setPlayers2());
		playersWins.setOnAction(e -> leaderboard.setPlayers3());
		boxie.getChildren().addAll(lb, players, playersPlayed, playersWins);
		this.getChildren().add(boxie);
	}
	

	private void setPanes(String text, ScrollPane scr) {
		Label lb = new Label(text);
		boxie = new VBox();
		scr.setPrefSize(250, 400);
		
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(300, 500);
		lb.setFont(f);
		
		boxie.getChildren().addAll(lb,scr);
		this.getChildren().add(boxie);
	}
	
	

}
