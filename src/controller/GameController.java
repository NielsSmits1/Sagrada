package controller;

import java.util.ArrayList;
import java.util.Optional;

import View.BoardPane;
import View.DicePane;
import View.GamePane;
import View.MyScene;
import View.ObjectiveCardPane;
import View.PatterncardSelect;
import View.RoundPane;
import View.RoundTrack;
import View.ToolCardPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
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
	private ChatBoxController chatBox;
	private BoardController boardcontroller;
	private CardController cardcontroller;
	private RoundPane rp;
	private RoundTrack roundTrack;
	private int round;
	private Button cancel;
	private Alert cancelGame;
	private String cancelText = "Sorry iemand heeft geweigerd, het spel kan dus niet doorgaan.";
	private Opponent[] opponents;
	private double playerScore;
	private Stage gameStage;
	private Timeline timeline;

	public GameController(Game g) {
		this.game = g;
		chatBox = new ChatBoxController(0,0);
		boardcontroller = new BoardController(this);
		cardcontroller = new CardController(this);
		game.setController(this);
		timeline = new Timeline();
	}

	public void buildGame() {
		for (Player p : game.getPlayers()) {
			p.setPc();
			p.setTokenAmount();
			boardcontroller.addBoard(p.getPc(), p);

		}
		cardcontroller.setToolcards();
		cardcontroller.setObjectiveCards();
		round = 0;
		game.setPlayableDices();
		gamePane = new GamePane(this);
		gamePane.getTurnSave().setOnAction(E -> endTurn());
		startTimeline();
	}

	public void startTimeline() {
		timeline.setCycleCount(timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5999), e -> refreshGame()));
		timeline.play();
	}
	public void stopTimeline() {
		this.timeline.stop();
	}

	private void refreshGame() {
		//refresh alles uit den game
		/*
		 * Beurt/rondes
		 * Boards
		 * Dices
		 * Score
		 */
	
		game.refreshCurrentPlayer();
		gamePane.changeInfo(this.shoutCurrentPlayer());
		this.refreshBoards();
		cardcontroller.updatePriceTag();
		game.setPlayableDices();
		gamePane.addDice();
		if(round != game.getRoundNumber()) {
			round = game.getRoundNumber();
			setDicesTrack(); // shows current RoundTrack
			for(int i = 0; i<game.getPlayers().size(); i++) {
				boardcontroller.getBoards().get(i).setScore(game.getPlayers().get(i).calculateScore(game.getIdGame()));
			}
		}
	}

	private void refreshBoards() {
		for (int i = 0; i < game.getPlayers().size(); i++) {
            boardcontroller.getBoards().get(i).setBoard();
            game.getPlayers().get(i).getPc().setPlacedDice();
            boardcontroller.getBoards().get(i).addPlacedDice(game.getPlayers().get(i).getDiceField());;
            game.getPlayers().get(i).setTokenAmount();
			boardcontroller.getBoards().get(i).changeTokenAmount(game.getPlayers().get(i).getTokenAmount());
		}
		
	}
	
	public String shoutCurrentPlayer() {
		return "ronde: " + game.getRoundNumber() + " - beurt: " + game.getTurn() + " - speler: " + game.getTurnPlayer().getUsername();

	}

	public PatterncardSelect buildPatterncardoptions() {
		if(!game.checkIfFilled()) {
			game.startGame();
			boardcontroller.setOptions();
			cardcontroller.insertCards();
		}
		boardcontroller.setOwnOptions();
		option = new PatterncardSelect(this);
		return option;
	}

	public Game getGame() {
		return this.game;
	}
	
	public String getPrivateCardColor() {
		for(Player p : game.getPlayers()) {
			if(p.getSelf()) {
				return p.getPrivateCardColor();
			}
		}
		return null;
	}

	public void addOpponets(Opponent op) {
		for (int x = 0; x < opponents.length; x++) {
			if (opponents[x] == null) {
				opponents[x] = op;
			} else {
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
		if (action.get() == ButtonType.OK) {
			// closeGame();
		}
	}

	public void closeGame() {
		// this.gameStage.close();
	}

	public void builtGameStage() {
		scene = new MyScene();
		// scene.builtNewGame();

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

	public ArrayList<ObjectiveCardPane> getObjectiveCardPanes() {
		return cardcontroller.getObjectiveCards();
	}
	
	public void setTokenAmount(int price, int toolcardid) {
		for(Player p : game.getPlayers()) {
			if(p.getSelf()) {
				for(BoardPane bp : boardcontroller.getBoards()) {
					if(bp.getSelf()) {
						bp.setToolcardActiveTrue();
						bp.changeTokenAmount(p.getTokenAmount());
						game.addTokensToGametoolcard(price, toolcardid, p.getPlayerId());
					}
				}
			}
		}
	}

	public void setToolcardOneActive() {
		gamePane.setToolCardOneActive();

	}

	public void setToolcardSixActive() {
		gamePane.setToolCardSixActive();
	}
	
	public void setToolcardSevenActive() {
		game.reDraw();
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

	public ArrayList<Space> getPatternCard() {
		return boardcontroller.getPatternCard();
	}

	public void setPatternCard(int id) {
		game.insertChosenID(id);
		game.assignTokensToPlayer();
		boardcontroller.setPatternCard(id);
	}
	
	public void setRandomCard() {
		boardcontroller.setRandomCard();
		game.insertChosenID();
		game.assignTokensToPlayer();
	}

	

	public int getOwnId() {
		return game.getOwnId();
	}

	public ArrayList<ToolCardPane> getToolCards() {
		return cardcontroller.getToolCards();
	}

	public void updateEyes(int eyes, int dienumber, String color) {
		game.updateEyes(eyes, dienumber, color);
	}

	public void enableDiceMovement(int i) {
		boardcontroller.setAllowsMovement(i);
	}

	public void swapDice(int dienumber, String color, int chosenvalue) {
		game.getDiceWithChosenValue(dienumber, color, chosenvalue);
	}

	public int returnAmountOfOpponents() {
		return opponents.length;
	}

	

	public int getGamemode() {
		return game.getGamemode();
	}

	// public int getDifficulty() {
	// return boardcontroller.getDifficulty();
	// }

	public void updateTokens(int difficulty) {
		game.updateTokenArrayList(difficulty);
	}



//	public void setGameCard(int id) {
//		game.addGametoolcard(id);
//	}

	public void addOptions(ArrayList<Integer> randomIDS) {
		game.addOptionsToDB(randomIDS);
	}

	public ArrayList<Integer> getOwnOptions() {
		return game.getOwnOptions();
	}

	public ArrayList<Integer> getChosenIds() {
		return game.getChosenIds();
	}

	public RoundTrack getRoundTrack() {
		return roundTrack;
	}

	public ArrayList<BoardPane> getBoards() {
		return boardcontroller.getBoards();
	}

	

	public void getOwnPlayerId() {
		if(game.getSelf().checkSelf() == true) {
			chatBox.getModel().setPlayerId(game.getSelf().getPlayerId());
		}
	}
	public void getOwnGameIdSelf() {
		if(game.getSelf().checkSelf() == true) {
			chatBox.getModel().setGameId(game.getSelf().getGameId());
		}
	}
	
	public int getOwnPatternId() {
		return game.getOwnPatternId();
	}
	
	public Player getTurnPlayer() {
		return game.getTurnPlayer();
	}
	
	public void endTurn() {
		if(getTurnPlayer().getSelf()) {
			for(BoardPane bp : boardcontroller.getBoards()) {
				if(bp.getSelf()) {
					bp.resetPlaced();
					game.setNewCurrentPlayer();
					game.buildTurns();
					cardcontroller.enableToolCards();
				}
			}
		}
	}

	public GamePane getGamePane() {
		return gamePane;
	}

	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}
	
	public ChatBoxController getChatBox() {
		return chatBox;
	}
	
	public BoardPane getOwnBoard() {
		return boardcontroller.getOwnBoard();
	}
	
	public void setDicesTrack() {
		gamePane.setRoundTrack(game.getLeftovers());
		
	}
	
	public void showWinnerScreen(ArrayList<ArrayList<Object>> data) {
		gamePane.showWinnerScreen(data);
	}


}
