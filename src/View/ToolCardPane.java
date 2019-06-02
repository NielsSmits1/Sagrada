package View;

import controller.CardController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ToolCardPane extends Pane {
	private Label price;
	private int toolCardId;
	private Button button;
	private VBox cardPropertiesAlignment;
	private CardController controller;
	private ImageView toolcards;
	private HBox BuyAlignment;
	private Circle tokenPlaceHolder;
	private Label amountOfTokensPlaced;

	private Image toolcard;

	public ToolCardPane(int id, CardController cc) {
		this.controller = cc;
		this.toolCardId = id;
		tokenPlaceHolder = new Circle(30, 28, 10);
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
		Pane image = new Pane();
		button = new Button("Koop");
		button.setOnAction(e -> handleButton());
		setPrice();
		setAmountPlaced();
		amountOfTokensPlaced.setLayoutX(26);
		amountOfTokensPlaced.setLayoutY(20);
		amountOfTokensPlaced.setTextFill(Color.WHITE);
		toolcards = new ImageView(toolcard);
		BuyAlignment.getChildren().addAll(button, price);
		BuyAlignment.setSpacing(50);
		image.getChildren().addAll(toolcards, tokenPlaceHolder, amountOfTokensPlaced);
		cardPropertiesAlignment.getChildren().addAll(image, BuyAlignment);
		cardPropertiesAlignment.setAlignment(Pos.CENTER);
		getChildren().addAll(cardPropertiesAlignment);

	}

	public void handleButton() {
		controller.buyToolCard(this);
	}
	
	public void setAmountPlaced() {
		amountOfTokensPlaced = new Label();
		amountOfTokensPlaced.setText("" + controller.getAmountPlaced(toolCardId));
	}

	public void setPrice() {
		price = new Label();
		price.setTextFill(Color.WHITE);
		price.setText("" + controller.getPrice(toolCardId));

	}

	public void changePrice(String value) {
		price.setText(value);

	}
	
	public int getToolCardId() {
		return toolCardId;
	}
	
	public void setButtonDisabled() {
		button.setDisable(true);
	}
	
	public void setButtonEnabled() {
		button.setDisable(false);
	}

	public int getPricetag() {
		int value = Integer.parseInt(price.getText());
		return value;
	}
}
