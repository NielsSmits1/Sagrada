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
import model.Player;
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
		game.setGameId(609);
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < 4; i++) {
			Player p = new Player("Speler " + i );
			p.setId(i + 991);
			p.setPatternCardId(p.getPatternIdFromDB());
			p.setPc();
			players.add(p);
		}
		game.insertPlayers(players);
//		}
		players.get(3).setSelf(true);
		for(Player p : game.getPlayers()) {
			// look elke speler in spel
			if(p.getSelf()) {
				boardcontroller.addBoard(p.getPc(), p.getUsername(), p.getSelf());
			}else {
				boardcontroller.addBoard(p.getPc(), p.getUsername(), false);
			}
			
		}
		
		gamePane = new GamePane(this);

	}
	public GameController(Game g) {
		this.game = g;
		game.setPlayableDices();
		boardcontroller = new BoardController(this);
		toolcardcontroller = new ToolcardController(this);
		for(Player p : game.getPlayers()) {
			// look elke speler in spel
			if(game.hasChosen()) {
				//check of game kan beginnen
				if(p.getSelf()) {
					//open kies patterncard
				}
			}
			p.setPc();
			boardcontroller.addBoard(p.getPc(), p.getUsername(), p.getSelf());
		}
		gamePane = new GamePane(this);
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
		//scene.builtNewGame();
		
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

	public void setToolcardOneActive() {
		gamePane.setToolCardOneActive();

	}
	
	public void setToolcardSixActive() {
		gamePane.setToolCardSixActive();
	}
	
	public void setToolcardTenActive() {
		gamePane.setToolCardTenActive();
	}
	
	public void setToolcardElevenActive() {
		gamePane.setToolCardElevenActive();
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
		boardcontroller.setPatternCard(id);
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

	public GamePane getGamepane() {
		return gamePane;
	}

	public ArrayList<ToolCardPane> getToolCards() {
		return toolcardcontroller.getToolCards();
	}

	public void updateEyes(int eyes, int dienumber, String color) {
		game.updateEyes(eyes, dienumber, color);
	}
	
	public void enableDiceMovement(int i) {
		boardcontroller.setAllowsMovement(i);
	}
	
	public void swapDice(int dienumber, String color, int value, int chosenvalue) {
		game.getDiceWithChosenValue(dienumber, color, value, chosenvalue);
		gamePane.addDice();
	}
	
	public int returnAmountOfOpponents() {
		return opponents.length;
	}
	
	public void setRandomCard() {
		boardcontroller.setRandomCard();
	}

	
	public int getGamemode() {
		return game.getGamemode();
	}
	
//	public int getDifficulty() {
//		return boardcontroller.getDifficulty();
//	}
	
	public void updateTokens(int difficulty) {
		game.updateTokenArrayList(difficulty);
	}

	public void setPlayerTokens(int minus) {
		boardcontroller.setPlayerTokens(minus);
	}
	
	public void setGameCard(int id) {
		game.addGametoolcard(id);
	}
	
	public void addOptions(ArrayList<Integer> randomIDS){
		game.addOptionsToDB(randomIDS);
	}
	
	public ArrayList<Integer> getOwnOptions(){
		return game.getOwnOptions();
	}
	
	public ArrayList<Integer> getChosenIds(){
		return game.getChosenIds();
	}
	
	public ArrayList<BoardPane> getBoards(){
		return boardcontroller.getBoards();
	}
	
	

}
