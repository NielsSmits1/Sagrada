package View;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObjectiveCardPane extends Pane{
	private Rectangle card;
	private TextArea description;
	
	public ObjectiveCardPane() {
		setCard();
		setTextArea();
		getChildren().addAll(card, description);
	}
	
	private void setCard() {
		card = new Rectangle(0,0, 150, 200);
		card.setStroke(Color.BLACK);
		card.setFill(Color.DARKSLATEGREY);
	}
	
	private void setTextArea() {
		description = new TextArea("Dit is een textarea die niet aan te passen is maar die wel binnen de objectivecard blijft, dit is dus ideaal voor de beschrijving van een kaart");
		description.setEditable(false);
		description.setWrapText(true);
		description.setPrefSize(130, 100);
		description.setLayoutX(10);
		description.setLayoutY(80);
	}
	
	public void changeText(String text) {
		description.setText(text);
	}
}
