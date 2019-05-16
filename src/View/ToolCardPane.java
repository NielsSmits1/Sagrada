package View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ToolCardPane extends Pane{
	private Rectangle card;
	private Label price;
	private TextArea textArea;
	
	public ToolCardPane(String description) {
		setCard();
		setPrice();
		setTextArea(description);
		getChildren().addAll(card,price,textArea);
	}
	
	private void setCard() {
		card = new Rectangle(0,0, 150, 200);
		card.setStroke(Color.BLACK);
		card.setFill(Color.GOLD);
	}
	
	private void setPrice() {
		price = new Label("1");
		price.setLayoutX(10);
		price.setLayoutY(10);
	}
	
	public void changePrice(String value) {
		price.setText(value);
	}
	
	private void setTextArea(String description) {
		textArea = new TextArea(description);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefSize(130, 100);
		textArea.setLayoutX(10);
		textArea.setLayoutY(80);
	}
	
	public void changeText(String text) {
		textArea.setText(text);
	}
	
	
}
