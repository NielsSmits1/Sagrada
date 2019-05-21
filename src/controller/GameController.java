package controller;

import java.util.ArrayList;
import java.util.Optional;

import View.BoardPane;
import View.DicePane;
import View.GamePane;
import View.MyScene;
import View.PatterncardSelect;
import View.ToolCardPane;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Dice;
import model.Game;
import model.Opponent;
import model.PatternCard;
//import model.Round;
import model.Round;
import model.Space;

public class GameController {
	private Game game;
	private MyScene scene;
	private PatterncardSelect option;
	private GamePane gamePane;
	private BoardController boardcontroller;
	private ToolcardController toolcardcontroller;
	private Round round;
	
	
	private Button cancel;
	private Alert cancelGame;
	private String cancelText = "Sorry iemand heeft geweigerd, het spel kan dus niet doorgaan.";
	private Opponent[] opponents;
	private double playerScore;
	private Stage gameStage;

	public GameController(MyScene s) {

		scene = s;

		game = new Game();
		game.setPlayableDices();
		boardcontroller = new BoardController(this);
		toolcardcontroller = new ToolcardController(this);

	}
	
	public void addOpponets(Opponent op) {
		for(int x = 0; x<opponents.length; x++) {
			if(opponents[x] == null) {
				opponents[x] = op;
			}else {
				// alert spel is al vol;
			}
		}
	}
	
	public double updateScore() {
		return playerScore;
	}
	
	
	public void cancelGame() {
		
	}
	
	public void builtAlerBox() {
		cancelGame = new Alert(AlertType.CONFIRMATION, cancelText, ButtonType.OK);
		cancelGame.setHeaderText("");
		cancelGame.setTitle("Sorry!");
		Optional<ButtonType> action = cancelGame.showAndWait();
		if(action.get() == ButtonType.OK) {
//			closeGame();
		}
	}
	
	public void closeGame() {
//		this.gameStage.close();
	}
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void setToolcardActive() {
		gamePane.setToolCardActive();

	}

	public Parent showOptions() {
		option = new PatterncardSelect(this);
		return option;
	}

	public ArrayList<PatternCard> getPatternCardOptions() {
		return boardcontroller.getPatternCardOptions();
	}

	/// *
	// Gets all dices available in the game.
	/// **
	public ArrayList<Dice> getPlayableDices() {
		return game.getPlayableDices();
	}

	public DicePane getSelected() {
		return gamePane.getSelected();
	}

	public int getIdGame() {
		return game.getIdGame();
	}

	public int getTurns() {
		return round.calculateTurns(getIdGame());
	}

	public ArrayList<Space> getPatternCard() {
		return boardcontroller.getPatternCard();
	}

	public void setPatternCard(int id) {
		game.setOwnId(id);
		boardcontroller.setPatternCard(id);
	}

	public void setRootpane() {

		gamePane = new GamePane(this);
//		scene.setRoot(rootpane);
		this.scene.setRoot(gamePane);

	}

	public BoardPane returnBoardPane() {
		return boardcontroller.returnBoardPane();
	}

	public int getOwnId() {
		return game.getOwnId();
	}

	public ArrayList<BoardPane> getOpponentBoard() {
		return boardcontroller.getOpponentBoard();
	}

	public GamePane getRootpane() {
		return gamePane;
	}

	

	public ArrayList<ToolCardPane> getToolCards() {
		return toolcardcontroller.getToolCards();
	}

	public void updateEyes(int eyes, int dienumber, String color) {
		game.updateEyes(eyes, dienumber, color);
	}

	public void enableDiceMovement() {
		boardcontroller.setAllowsMovement();
	}
	
	

}
