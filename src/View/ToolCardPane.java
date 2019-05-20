package View;

import controller.ToolcardController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ToolCardPane extends Pane {
	private Rectangle card;
	private Label price;
	private TextArea textArea;
	private int toolCardId;
	private Button button;
	private VBox cardPropertiesAlignment;
	private ToolcardController toolcardController;

	public ToolCardPane(int id, String description, ToolcardController toolcardController) {
		this.toolcardController = toolcardController;
//		this.toolCardId = id;
		toolCardId = 2;
		cardPropertiesAlignment = new VBox();
		button = new Button("Koop");
		button.setOnAction(e -> handleButton());
		setCard();
		setPrice();
		setTextArea(description);
		cardPropertiesAlignment.getChildren().addAll(textArea, button);
		cardPropertiesAlignment.setAlignment(Pos.CENTER);
		cardPropertiesAlignment.setPadding(new Insets(50,0,0,10));
		getChildren().addAll(card, price, cardPropertiesAlignment);
	}

	public void handleButton() {
		if(price.getText().equals("1")) {
			changePrice("2");
		}
		toolcardController.toolcardClicked(toolCardId);
	}

	private void setCard() {
		card = new Rectangle(0, 0, 150, 200);
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
