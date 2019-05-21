package View;

import java.util.ArrayList;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Dice;

public class GamePane extends BorderPane {
	/// *
	// Sets all instances of a variety of objects, some of them might be removed
	/// later on.
	/// **
	private Button close;
	
	private BoardPane player1;
	private BoardPane player2;
	private BoardPane player3;
	private BoardPane player4;
	private HBox boards;
	private HBox dices;
	private DicePane selected;
	private ArrayList<ToolCardPane> toolcards;
	private PrivateCardPane pc;
	private ObjectiveCardPane ocp;
	private ObjectiveCardPane ocp2;
	private HeaderPane objectiveCard;
	private HeaderPane privateCard;
	private HeaderPane toolCard;
	private BorderPane bottom;
	private GameController controller;
	private ArrayList<Dice> diceArray;
	private boolean toolcardIsActive;
	private DecisionPane decisionpane;
	/// *
	// RootPane creates the controller to communicate with the model that gets all
	/// 90 dices.
	// This constructor also adds some boards, dices and all of the diverse cards to
	/// the screen.
	/// **

	public GamePane(GameController gameController) {
		toolcardIsActive = false;
		decisionpane = new DecisionPane(this);
		dices = new HBox();
		dices.setSpacing(20);
		this.controller = gameController;
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

		// player2 = new BoardPane(this, 7);
		// player2.switchTransparent();
		//
		// player3 = new BoardPane(this, 12);
		// player3.switchTransparent();
		// player4 = new BoardPane(this, 3);
		// player4.switchTransparent();

		boards = new HBox(player1, player2, player3, player4);
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
			dices.getChildren().add(new DicePane(getPlayableDices().get(i).getEyes(),
					getPlayableDices().get(i).getDieColor(), getPlayableDices().get(i).getDieNumber(), this));
				
		}

	}

	/// *
	// Sets all cards, also adds the labels above the cards.
	/// **

	private void setCards() {
		// Creates new cards
		this.close = new Button("opgeven");
		pc = new PrivateCardPane();
		ocp = new ObjectiveCardPane();
		ocp2 = new ObjectiveCardPane();

		toolcards = controller.getToolCards();
		// Creates new headers
		objectiveCard = new HeaderPane();
		privateCard = new HeaderPane();
		toolCard = new HeaderPane();
		// Changes the text of the labels
		objectiveCard.changeLabel("Objective Cards");
		privateCard.changeLabel("Private Card");
		toolCard.changeLabel("Toolcards");
		// changes the price labels
		// tcp1.changePrice("2");
		bottom = new BorderPane();
		bottom.setPadding(new Insets(0, 130, 50, 50));
		bottom.setLeft(dices);

		HBox oc = new HBox(ocp, ocp2);
		oc.setSpacing(5);
		VBox finalOc = new VBox(objectiveCard, oc);
		finalOc.setSpacing(5);
		VBox finalPc = new VBox(privateCard, pc);
		finalPc.setSpacing(5);
		HBox tcp1 = new HBox();
		tcp1.getChildren().addAll(toolcards);
		tcp1.setSpacing(5);
		VBox finalTcp = new VBox(toolCard, tcp1);
		finalTcp.setSpacing(5);
		HBox toolCards = new HBox(finalOc, finalPc, finalTcp,close);
		toolCards.setSpacing(5);
		bottom.setRight(toolCards);
		

//		close.setOnAction(e -> controller.getProgress().closeGame());
	
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
		if(toolcardIsActive) {
			setRight(decisionpane);
		}
	}
	public void downSelected() {
		
		for (int i = 0; i < dices.getChildren().size(); i++) {
            DicePane temporarilyDice = (DicePane)dices.getChildren().get(i);
            if(selected.getDieNumber() == temporarilyDice.getDieNumber() && selected.getColor().equals(temporarilyDice.getColor())) {
            	if(selected.getValue() == 1) {
            		decisionpane.giveError();
            		return;
            	}
                temporarilyDice.removeEyes();
                temporarilyDice.setValue(temporarilyDice.getValue()-1);
                temporarilyDice.addDiceEyes(temporarilyDice.getValue());
                disableToolcard();
                setRight(null);
                controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());
                
            }
            
        }
	}
	
	public void upSelected() {
		
		for (int i = 0; i < dices.getChildren().size(); i++) {
            DicePane temporarilyDice = (DicePane)dices.getChildren().get(i);
            if(selected.getDieNumber() == temporarilyDice.getDieNumber() && selected.getColor().equals(temporarilyDice.getColor())) {
            	if(selected.getValue() == 6) {
            		decisionpane.giveError();
            		return;
            	}
                temporarilyDice.removeEyes();
                temporarilyDice.setValue(temporarilyDice.getValue()+1);
                temporarilyDice.addDiceEyes(temporarilyDice.getValue());
                disableToolcard();
                setRight(null);
                controller.updateEyes(selected.getValue(), selected.getDieNumber(), selected.getColor());
            }
           
        }
	}
	
	public void SelectedStaysEqual() {
		disableToolcard();
		setRight(null);
	}
	
	public void setToolCardActive() {
		toolcardIsActive = true;
	}
	
	public void disableToolcard() {
		toolcardIsActive = false;
	}
	
//	public void enableDiceMovement() {
//		player1.enableDiceMovement();
//	}

	/// *
	// Returns selected, will be used in the class patternPane.
	/// **
	public DicePane getSelected() {
		return selected;
	}

	private ArrayList<Dice> getPlayableDices() {
		return controller.getPlayableDices();
	}

	public void setBoardPlayerOne() {
		player1 = controller.returnBoardPane();
		player2 = controller.getOpponentBoard().get(0);
		player3 = controller.getOpponentBoard().get(1);
		player4 = controller.getOpponentBoard().get(2);
	}


	public Button getClose() {
		return close;
	}

}
