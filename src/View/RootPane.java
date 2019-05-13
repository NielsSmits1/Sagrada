package View;

import java.util.ArrayList;

import java.util.Random;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Dice;

public class RootPane extends BorderPane {
	/// *
	// Sets all instances of a variety of objects, some of them might be removed
	/// later on.
	/// **
	private BoardPane player1;
	private BoardPane player2;
	private BoardPane player3;
	private BoardPane player4;
	private HBox boards;
	private HBox dices;
	private DicePane selected;
	private ToolCardPane tcp;
	private ToolCardPane tcp2;
	private ToolCardPane tcp3;
	private PrivateCardPane pc;
	private ObjectiveCardPane ocp;
	private ObjectiveCardPane ocp2;
	private HeaderPane objectiveCard;
	private HeaderPane privateCard;
	private HeaderPane toolCard;
	private BorderPane bottom;
	private GameController controller;
	private Random r;
	private ArrayList<Dice> diceArray;
//	private Button endTurn;
//	private Button refreshDice;
	// private Menubar menu;

	/// *
	// RootPane creates the controller to communicate with the model that gets all
	/// 90 dices.
	// This constructor also adds some boards, dices and all of the diverse cards to
	/// the screen.
	/// **

	public RootPane(GameController gameController) {
		// scene = s;
		// this.menu = menu;
//		endTurn = new Button("End Turn");
//		endTurn.setOnAction(e -> handle());
//		refreshDice = new Button("Refresh");
//		refreshDice.setOnAction(e -> refresh());
		dices = new HBox();
		dices.setSpacing(20);
		this.controller = gameController;
//		diceArray = getDiceArray();
//		r = new Random();
		setBoard();
		addDice();
		finish();
	}

	/// *
	// Sets all boards.
	/// **

	private void setBoard() {
		/// *
		// The the number in the constructor from BoardPane stands for the number of the
		/// windowpattern in the DB.
		/// **
		// player1 = new BoardPane(this,5);
		setBoardPlayerOne();

//		player2 = new BoardPane(this, 7);
//		player2.switchTransparent();
//
//		player3 = new BoardPane(this, 12);
//		player3.switchTransparent();
//		player4 = new BoardPane(this, 3);
//		player4.switchTransparent();
		
		boards = new HBox(player1);
		boards.setSpacing(20);
		boards.setPadding(new Insets(0, 0, 0, 50));
	}

	/// *
	// Add dices to the screen, this constructor of dicePane wants an instance of
	/// the model Dice.
	// This model contains the amount of eyes and the color that the dice should be.
	/// **

	private void addDice() {
		for (int i = 0; i < getPlayableDices().size(); i++) {
			dices.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),getPlayableDices().get(i).getDieColor(),getPlayableDices().get(i).getDieNumber()  , this));
		}

	}

	/// *
	// Sets all cards, also adds the labels above the cards.
	/// **

	private void setCards() {
		// Creates new cards
		pc = new PrivateCardPane();
		ocp = new ObjectiveCardPane();
		ocp2 = new ObjectiveCardPane();
		tcp = new ToolCardPane();
		tcp2 = new ToolCardPane();
		tcp3 = new ToolCardPane();
		// Creates new headers
		objectiveCard = new HeaderPane();
		privateCard = new HeaderPane();
		toolCard = new HeaderPane();
		// Changes the text of the labels
		objectiveCard.changeLabel("Objective Cards");
		privateCard.changeLabel("Private Card");
		toolCard.changeLabel("Toolcards");
		// changes the price labels
		tcp.changePrice("2");
		bottom = new BorderPane();
		bottom.setPadding(new Insets(0, 130, 50, 50));
		bottom.setLeft(dices);

		HBox oc = new HBox(ocp, ocp2);
		oc.setSpacing(5);
		VBox finalOc = new VBox(objectiveCard, oc);
		finalOc.setSpacing(5);
		VBox finalPc = new VBox(privateCard, pc);
		finalPc.setSpacing(5);
		HBox tcp1 = new HBox(tcp, tcp2, tcp3);
		tcp1.setSpacing(5);
		VBox finalTcp = new VBox(toolCard, tcp1);
		finalTcp.setSpacing(5);
		HBox toolCards = new HBox(finalOc, finalPc, finalTcp);
		toolCards.setSpacing(5);
		bottom.setRight(toolCards);
	}

	/// *
	// Gets all dices out of the DB. This means the size of the Array is 90.
	/// **

	public ArrayList<Dice> getDiceArray() {
		return controller.getPlayableDices();
	}

	/// *
	// Adds all panes to the rootPane.
	/// **
	private void finish() {
		setCards();
		setCenter(boards);
		setBottom(bottom);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
	}

	/// *
	// Delete the selected DicePane by setting its value to null.
	/// **

	public void deleteSelected() {
		selected = null;
	}

	/// *
	// Selected becomes the dicePane that has been clicked
	/// **

	public void setSelected(DicePane p) {
		selected = p;
		// p.removeEventHandler(MouseEvent.MOUSE_CLICKED, p.getOnMouseClicked());
		// selected.removeEventHandler(MouseEvent.MOUSE_CLICKED,
		// selected.getOnMouseClicked());
		// selected.removeEventHandler(MouseEvent.MOUSE_PRESSED, p.getClicker());
		// System.out.println("" + selected);
	}

	/// *
	// Returns selected, will be used in the class patternPane.
	/// **
	public DicePane getSelected() {
		return selected;
	}
	
	private ArrayList<Dice> getPlayableDices(){
		return controller.getPlayableDices();
	}

	public void setBoardPlayerOne() {
		player1 = controller.returnBoardPane();
	}

//	private void handle() {
//		if (player1.isMouseTransparent() == false) {
//			player1.switchTransparent();
//			player2.switchTransparent();
//			return;
//		}
//		if (player2.isMouseTransparent() == false) {
//			player2.switchTransparent();
//			player3.switchTransparent();
//			return;
//		}
//		if (player3.isMouseTransparent() == false) {
//			player3.switchTransparent();
//			player4.switchTransparent();
//			return;
//		}
//		if (player4.isMouseTransparent() == false) {
//			player4.switchTransparent();
//			player1.switchTransparent();
//			return;
//		}
//	}

//	private void refresh() {
//		if (dices.getChildren().isEmpty()) {
//			addDice();
//		} else {
//
//		}
//	}
}
