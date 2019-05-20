package View;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PrivateCardPane extends Pane {
	private Rectangle card;
	private DicePane diceColor;
	public PrivateCardPane() {
		setCard();
		setDice();
		getChildren().addAll(card, diceColor);
	}
	private void setCard() {
		card = new Rectangle(0,0, 150, 200);
		card.setStroke(Color.BLACK);
		card.setFill(Color.DARKSLATEGREY);
	}
	
	private void setDice() {
		diceColor = new DicePane();
		diceColor.setColor("blauw");
		diceColor.setLayoutX(40);
		diceColor.setLayoutY(65);
	}

}
