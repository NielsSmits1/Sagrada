package View;

import java.util.ArrayList;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.PatternCard;

public class PatterncardSelect extends Pane {
	private ArrayList<PatternPane> board;
	private Button randomButton;

	private Alert alert = new Alert(AlertType.INFORMATION);

	// private double textX = 175;
	// private int fontSize = 20;
	// private int heightPosition = 375;
	// private int paneHeight = 403;
	// private int paneWidth = 324;

	private ArrayList<Integer> id;
	private int patternId;
	private ArrayList<GridPane> choice;
	private Button button;
	private GameController controller;
	private Border peru = new Border(
			new BorderStroke(Color.PERU, BorderStrokeStyle.SOLID, null, new BorderWidths(10.0)));

	public PatterncardSelect(GameController gc) {
		super();

		controller = gc;
		id = new ArrayList<>();
		setGrid();
		button = new Button("Pick This one!");
		button.setFont(Font.font(null, 17));
		button.setOnAction(e -> {
			handle();
		});
		button.setDisable(true);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
		randomButton = new Button("Random kaarten");
		randomButton.setFont(Font.font(null, 17));
		randomButton.setOnAction(e -> handleRandomCard());

		setBoard();

	}

	/// *
	// Sets the GridPane called field that represents the pattern.
	/// **

	private void handleRandomCard() {
		controller.setRandomCard();
		alert.setHeaderText("U heeft een patroonkaarten gekozen");
		alert.showAndWait();
	}

	private void setGrid() {
		choice = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			choice.add(new GridPane());
		}
		for (int i = 0; i < choice.size(); i++) {
			choice.get(i).setVgap(8);
			choice.get(i).setHgap(8);
			choice.get(i).setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		}
		choice.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(0);
				for (int i = 0; i < 4; i++) {
					if (i == 0) {
						choice.get(i).setBorder(peru);
					} else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}
		});

		choice.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(1);
				for (int i = 0; i < 4; i++) {
					if (i == 1) {
						choice.get(i).setBorder(peru);
					} else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}

		});

		choice.get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(2);
				for (int i = 0; i < 4; i++) {
					if (i == 2) {
						choice.get(i).setBorder(peru);
					} else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}

		});

		choice.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				patternId = id.get(3);
				for (int i = 0; i < 4; i++) {
					if (i == 3) {
						choice.get(i).setBorder(peru);
					} else {
						choice.get(i).setBorder(null);
					}
				}
				button.setDisable(false);
			}

		});
	}

	private void setBoard() {
		int counter = 0;
		board = new ArrayList<>();
		for (int j = 0; j < 4; j++) {
			counter = 0;
			for (int c = 1; c <= 5; c++) {
				for (int i = 0; i < 4; i++) {
					board.add(new PatternPane(
							new DicePane(getPatternCard().get(j).getPatternField().get(counter).getEyes(),
									getPatternCard().get(j).getPatternField().get(counter).getColor())));
					choice.get(j).add(board.get(board.size() - 1),
							getPatternCard().get(j).getPatternField().get(counter).getXPos(),
							getPatternCard().get(j).getPatternField().get(counter).getYPos());
					counter++;
				}
			}
			id.add(getPatternCard().get(j).getPatternId());
			board.removeAll(board);

		}
		HBox hBox = new HBox();
		for (int i = 0; i < choice.size(); i++) {
			hBox.getChildren().add(choice.get(i));
		}
		hBox.setSpacing(50.0);
		VBox box = new VBox(hBox, button, randomButton);
		box.setSpacing(25.0);
		getChildren().addAll(box);
	}

	public int getChosenId() {
		if (patternId != 0) {
			return patternId;
		}
		return 0;
	}

	public void handle() {
		controller.setPatternCard(getChosenId());
	}

	public ArrayList<PatternCard> getPatternCard() {
		return controller.getPatternCardOptions();
	}

	/// *
	// Returns the ArrayList with Spaces.
	/// **

}
