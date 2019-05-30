package View;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PrivateCardPane extends Pane {
	private Rectangle card;
	private DicePane diceColor;
	public PrivateCardPane() {
		setCard();
		getChildren().addAll(card);
	}
	private void setCard() {
		card = new Rectangle(0,0, 200, 275);
		card.setStroke(Color.BLACK);
		card.setFill(Color.DARKSLATEGREY);
	}
	
	public void setDice(String color) {
		diceColor = new DicePane();
		diceColor.setColor(color);
		diceColor.setLayoutX(65);
		diceColor.setLayoutY(93.5);
		getChildren().add(diceColor);
	}

}
