package controller;

import View.SearchPlayerPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Player;

public class SearchPlayerController {
	private SearchPlayerPane searchPlayerPane;
	private HomeController hc;
	private Player player;
	private Alert alert = new Alert(AlertType.INFORMATION);

	public SearchPlayerController(HomeController hc) {
		this.hc = hc;
		searchPlayerPane = new SearchPlayerPane();
		searchPlayerPane.getSearch().setOnAction(e -> this.search());
		searchPlayerPane.getChallenge().setOnAction(e -> challengePlayer());
		searchPlayerPane.getStats().setOnAction(e -> showStats());
//		player = new Player(username);
	}

	private void search() {

		searchPlayerPane.setUsername(searchPlayerPane.getOnline().getText());
		System.out.println(searchPlayerPane.getUsername());
		if (!searchPlayerPane.getUsername().equals("")) {
			if (hc.usernameExist(searchPlayerPane.getUsername())) {
				searchPlayerPane.showPlayer(searchPlayerPane.getUsername());

			} else {
//				searchPlayerPane.setAlert(alert);
				searchPlayerPane.alert("Niemand gevonden met deze gebruikersnaam");
				searchPlayerPane.getAlert();
			}
		}
	}

	private void challengePlayer() {
		if (!hc.getPlayer().checkGameSize()) { // true = game less then 4 players
			hc.getPlayer().createNewGame();
		}
		if (hc.getSelf().checkSelf()) { // true = already in game false = not in game
			hc.getPlayer().addChallenger();
		} else {
			hc.getSelf().addSelf();
			hc.getPlayer().addChallenger();
		}

	}

//	public void showPlayerInformation() {
//	
//
//		searchPlayerPane.getChallenge().setOnAction(e -> System.out.println("ch"));
//		searchPlayerPane.getStats().setOnAction(e -> System.out.println("stats"));
//		
//
//		
//	}
	private void showStats() {
		alert.setHeaderText(hc.getStatsPlayer());
		// test
		alert.showAndWait();
	}

	public SearchPlayerPane getSearchPlayerPane() {
		SearchPlayerPane spp = searchPlayerPane;
		return spp;
	}

}
