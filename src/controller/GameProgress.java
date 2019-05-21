package controller;

import java.util.Optional;

import View.MyScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Opponent;

public class GameProgress {

	private Opponent[] opponents;
	
	private double playerScore;
	
	private Alert cancelGame;
	private String cancelText = "Sorry iemand heeft geweigerd, het spel kan dus niet doorgaan.";
	private Button cancel;
	
	private Stage gameStage;
	
	private MyScene scene;
	
	public GameProgress() {
//		this.x = opponents;
//		this.opponents = new Opponent[x];
//		playerScore = 0;
	}
	
	public void addOpponets(Opponent op) {
		for(int x = 0; x < opponents.length; x++) {
			if(opponents[x] == null) {
				opponents[x] = op;
			} else {
				// alert spel is al vol.
			}
		}
	}
	
	public double update() {
		// haal info op uit game class.
		// haal info op uit patterncard
		//haal info op uit toolcard
		// haal info op uit objectivecard.
		return playerScore;
	}
	
	public void cancelGame() {
		// alert als iemand weigerd en geef optie om te sluiten.
	}
	
	public void builtAlertbox() {
		cancelGame = new Alert(AlertType.CONFIRMATION, cancelText, ButtonType.OK);
		cancelGame.setHeaderText("");
		cancelGame.setTitle("Sorry");
		Optional<ButtonType> action = cancelGame.showAndWait();
		if(action.get() == ButtonType.OK) {
//			closeGame();
		}
		
	}
	
//	public void closeGame() {
//		this.gameStage.close();
//	}
//	
	public void builtGameStage() {
		scene = new MyScene();
		scene.builtNewGame();
		
		gameStage = new Stage();
		gameStage.setTitle("Sagrada");
		gameStage.setScene(scene);
		gameStage.setFullScreen(true);		
		gameStage.show();
	}

	public MyScene getScene() {
		builtGameStage();
		return scene;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
