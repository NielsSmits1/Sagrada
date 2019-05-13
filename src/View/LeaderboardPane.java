package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controller.HomeController;
import controller.LeaderboardController;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LeaderboardPane extends ScrollPane {
	private ArrayList<LeaderboardPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
	private LeaderboardController lc;

	public LeaderboardPane(HomeController self) {
		lc = new LeaderboardController(self);
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		
		setPlayers();


		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}

	public void addPlayerNameLine(String name) {
		playerLine.add(new LeaderboardPlayerLinePane(name));
	}

	public void addPlayerNameLineWithAmountOfGamesPlayed(String name, String amountPlayed) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountPlayed));
	}
	public void addPlayerNameLineWithAmountOfGamesWon(String name, int amountWon) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountWon));
	}

	public void setPlayers() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		for (String playerName : lc.getPlayers()) {
			addPlayerNameLine(playerName);
		}
	
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}

	public void setPlayers2() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		for (Map.Entry<String, String> playerGamesPlayed : lc.getPlayersFilteredByAmountOfGames().entrySet()) {
			addPlayerNameLineWithAmountOfGamesPlayed(playerGamesPlayed.getKey().toString(),
					playerGamesPlayed.getValue().toString());
		}
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	public void setPlayers3() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		for (Map.Entry playerGamesPlayedWon : lc.getPlayersFilteredByAmountOfGamesWon().entrySet()) {
			addPlayerNameLineWithAmountOfGamesWon(playerGamesPlayedWon.getKey().toString(),
					(int) playerGamesPlayedWon.getValue());
			
		}
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	
//	public ObservableList<Object> leaderboardList(){
//		TableColumn<String, String> nameColumn = new TableColumn<>("name");
//		nameColumn.setMinWidth(200);
//		
//	}
//	public void getPlayerGamesPlayed() {
//		TableColumn<Map, String> firstDataColumn = new TableColumn<>("Naam");
//		TableColumn<Map, String> secondDataColumn = new TableColumn<>("Naam");
//		
//		firstDataColumn.setCellFactory(new MapValueFactory("A"));
//		firstDataColumn.setMinWidth(75);
//		secondDataColumn.setCellFactory(new MapValueFactory("A"));
//		secondDataColumn.setMinWidth(75);
//		
//		TableView tableView = new TableView<>(generateDataInMap());
//		
//		tableView.setEditable(true);
//		tableView.getSelectionModel().setCellSelectionEnabled(true);
//		tableView.getColumns().setAll(firstDataColumn, secondDataColumn);
//		
//	}
//	public ObservableList<Map> generateDataInMap(){
//		int i = 300;
//		String a;
//		String b;
//		ObservableList<Map> allDataList = FXCollections.observableArrayList();
//		for (HashMap<String, String> a = lc.getPlayersFilteredByAmountOfGames()){
//
//			
//		}
//		return allDataList;
//	}
}
