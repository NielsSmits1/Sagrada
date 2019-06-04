package View;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PrivateCardPane extends Pane {
	private Rectangle card;
	private DicePane diceColor;
	private boolean isPrivateCard;
	
	public PrivateCardPane() {
		isPrivateCard = true;
		setCard();
		getChildren().addAll(card);
	}
	private void setCard() {
		card = new Rectangle(0,0, 200, 275);
		card.setStroke(Color.BLACK);
		card.setFill(Color.DARKSLATEGREY);
	}
	
	public void setDice(String color) {
		diceColor = new DicePane(isPrivateCard);
		diceColor.setColor(color);
		diceColor.setLayoutX(50);
		diceColor.setLayoutY(87.5);
		getChildren().add(diceColor);
	}

}
