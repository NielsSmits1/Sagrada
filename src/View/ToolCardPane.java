package View;

import controller.ToolcardController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ToolCardPane extends Pane {
	private Label price;
	private int toolCardId;
	private Button button;
	private VBox cardPropertiesAlignment;
	private ToolcardController toolcardController;
	private ImageView toolcards;
	private HBox BuyAlignment;

	private Image toolcard;

	public ToolCardPane(int id, String description, ToolcardController toolcardController) {
		this.toolcardController = toolcardController;
		this.toolCardId = id;
		switch (id) {
		case 1:
			toolcard = new Image("/Resources/toolcard_1.png");
			break;
		case 2:
			toolcard = new Image("/Resources/toolcard_2.png");
			break;
		case 3:
			toolcard = new Image("/Resources/toolcard_3.png");
			break;
		case 4:
			toolcard = new Image("/Resources/toolcard_4.png");
			break;
		case 5:
			toolcard = new Image("/Resources/toolcard_5.png");
			break;
		case 6:
			toolcard = new Image("/Resources/toolcard_6.png");
			break;
		case 7:
			toolcard = new Image("/Resources/toolcard_7.png");
			break;
		case 8:
			toolcard = new Image("/Resources/toolcard_8.png");
			break;
		case 9:
			toolcard = new Image("/Resources/toolcard_9.png");
			break;
		case 10:
			toolcard = new Image("/Resources/toolcard_10.png");
			break;
		case 11:
			toolcard = new Image("/Resources/toolcard_11.png");
			break;
		case 12:
			toolcard = new Image("/Resources/toolcard_12.png");
			break;
		}
		BuyAlignment = new HBox();
		cardPropertiesAlignment = new VBox();
		button = new Button("Koop");
		button.setOnAction(e -> handleButton());
		setPrice();
		toolcards = new ImageView(toolcard);
		BuyAlignment.getChildren().addAll(button, price);
		BuyAlignment.setSpacing(50);
		cardPropertiesAlignment.getChildren().addAll(toolcards, BuyAlignment);
		cardPropertiesAlignment.setAlignment(Pos.CENTER);
		getChildren().addAll(cardPropertiesAlignment);

	}

	public void handleButton() {
		setPlayerTokens();
		if (price.getText().equals("1")) {
			changePrice("2");
		}
		toolcardController.toolcardClicked(toolCardId);
	}

	private void setPrice() {
		price = new Label("1");

	}

	public void changePrice(String value) {
		price.setText(value);

	}

	public int getPricetag() {
		int value = Integer.parseInt(price.getText());
		System.out.println(value);
		return value;
	}

	public void setPlayerTokens() {
		toolcardController.setPlayerTokens(getPricetag());
	}
}
