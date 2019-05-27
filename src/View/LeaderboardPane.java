package View;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class LeaderboardPane extends ScrollPane {
	private ArrayList<LeaderboardPlayerLinePane> playerLine;
	private VBox playerColumn = new VBox();
//	private LeaderboardController lc;

	public LeaderboardPane() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
//		lc = new LeaderboardController(self);
//		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
		
//		setPlayers();


		setLayout();
	}

	public void addPlayerNameLine(String name) {
		playerLine.add(new LeaderboardPlayerLinePane(name));
	
	}
	public void addPlayerNameLine(int number) {
		playerLine.add(new LeaderboardPlayerLinePane(number));
	
	}

	public void addPlayerNameLineWithAmountOfGamesPlayed(String name, String amountPlayed) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountPlayed));
	}
	public void addPlayerNameLineWithAmountOfGamesWon(String name, int amountWon) {
		playerLine.add(new LeaderboardPlayerLinePane(name, amountWon));
	}

	public void setPlayersName() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
//		for (String playerName : lc.getPlayers()) {
//			addPlayerNameLine(playerName);
//		}
//	
//		playerColumn.getChildren().setAll(playerLine);
//		this.setContent(playerColumn);
//		this.setHbarPolicy(getHbarPolicy().NEVER);
//		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	
	

	public void setPlayersPlayed() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
//		for (Map.Entry<String, String> playerGamesPlayed : lc.getPlayersFilteredByAmountOfGames().entrySet()) {
//			addPlayerNameLineWithAmountOfGamesPlayed(playerGamesPlayed.getKey().toString(),
//					playerGamesPlayed.getValue().toString());
//		}
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	public void setPlayersWin() {
		playerLine = new ArrayList<LeaderboardPlayerLinePane>();
//		for (Map.Entry playerGamesPlayedWon : lc.getPlayersFilteredByAmountOfGamesWon().entrySet()) {
//			addPlayerNameLineWithAmountOfGamesWon(playerGamesPlayedWon.getKey().toString(),
//					(int) playerGamesPlayedWon.getValue());
			
//		}
		playerColumn.getChildren().setAll(playerLine);
		this.setContent(playerColumn);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);

	}
	public void setLayout() {
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
