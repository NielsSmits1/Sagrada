package controller;

import java.util.ArrayList;
import View.SearchPlayerPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Challenge;
import model.Game;
import model.Player;

public class SearchPlayerController {
	private SearchPlayerPane spp;
	private HomeController hc;
	private Player player;
	private Player self;
	private Challenge ch;
	private ChallengerController cp;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private Game game = new Game();
	public SearchPlayerController(HomeController hc, ChallengerController cp) {
		this.cp = cp;
		this.hc = hc;
		spp = new SearchPlayerPane();
		spp.getSearch().setOnAction(e -> this.search());
		spp.getChallenge().setOnAction(e -> challengePlayer());
		spp.getStats().setOnAction(e -> showStats());
		self = this.hc.getSelf();
		this.RefreshChoiceBox();

	}


	private void search() {

		spp.setUsername(spp.getOnline().getText());
		player = new Player(spp.getUsername());
		if (!spp.getUsername().equals("")) {
			if(spp.getUsername().equals(self.getUsername())){
				spp.alert("Je kan jezelf niet uitdagen");
			}
			else {
				if (player.checkUsernameExists()) {
					spp.showPlayer(player.getUsername());

				} else {
					spp.alert("Niemand gevonden met deze gebruikersnaam");
					spp.getAlert();
				}
			}
		}
	}

	private void challengePlayer() {
		String[] choice = spp.getChoice().split("Voeg toe aan spel nummer: ");
		Challenge challenge = new Challenge();
		if(self.checkIfGame(player.getUsername())) {
			spp.alert("U zit al in een game met deze persoon");
			spp.getAlert();
		}else {
			Game g = new Game();
			if(spp.getChoice().equals("Nieuw spel")) {
				g.createNewGame();
			}else {
				int c =(Integer.parseInt(choice[1])); 
				g.setGameId(c);
			}
			if(!g.alreadyInGame(self)) {
				g.addPlayer(self, "Uitdager", g.getRandomColor(), 1,1);
				self.setId(g.getPlayerId(self.getUsername()));
				self.setStandardScore();
				g.startGame();
				challenge.generateRandomToolcards(g.getIdGame());
				challenge.generateRandomObjectcard(g.getIdGame());
				challenge.addOptions(self.getPlayerId(), g.getIdGame());
			}
			g.addPlayer(player, "Uitgedaagde", g.getRandomColor(), g.getHighestSeNumber() , 0);
			player.setId(g.getPlayerId(player.getUsername()));
			player.setStandardScore();
			challenge.addOptions(player.getPlayerId(), g.getIdGame());
			cp.refresh();
			RefreshChoiceBox();
		}

	}
	private void RefreshChoiceBox() {
		ArrayList<String> op = new ArrayList<String>();
		op.add("Nieuw spel");
		for(Integer g : game.availableGames(self.getUsername())) {
			op.add("Voeg toe aan spel nummer: " + g);
		}
		spp.setChoiceBox(op);
		
	}

	public void createChallenge() {
		ch = new Challenge();
		ch.addChallenger(self);
		ch.addChallengee(player);
	}

	public String getStatsPlayer() {
		String stats = "Aantal gewonnen en verloren potjes: " + player.getTimesWon() + " : " + player.getTimesLost()
				+ "\nHoogst behaalde score: " + player.getHighScore() + "\nMeest geplaatste dobbelsteenkleur: "
				+ player.getMostPlacedDiceColor() + "\nMeest geplaatste dobbelsteenwaarde: "
				+ player.getMostPlacedDiceEyes() + "\nAantal verschillende tegenstanders waartegen gespeeld is: "
				+ player.getAmountOfUniquePlayers();
		return stats;
	}

	private void showStats() {
		alert.setHeaderText(getStatsPlayer());
		alert.showAndWait();
	}

	public SearchPlayerPane getSearchPlayerPane() {
		SearchPlayerPane spp = this.spp;
		return spp;
	}
	
	
	
	

}
