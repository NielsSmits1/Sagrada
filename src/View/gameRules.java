package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gameRules {

	private Stage stage = new Stage();
	private Scene scene;

	private VBox boxie;
	private HBox hoxie;
	private HBox nextBox;
	private HBox previousBox;
	private HBox closeBox;
	private HBox image;

	private Button next = new Button("volgende pagina");
	private Button previous = new Button("vorige pagina");
	private Button close = new Button("sluiten");

	private Image page = new Image("/Resources/uitleg_pagina_1.png");
	private ImageView pages;
	

	public gameRules() {

		next.setOnMouseClicked(e -> creatPage2());
		close.setOnAction(e -> this.stage.close());
	}

	public void createStage1() {
		// pane plus image;
		image = new HBox();
		// images

		pages = new ImageView(page);
		image.getChildren().add(pages);
		image.setAlignment(Pos.CENTER);

		// buttons
		previous.setVisible(false);
		next.setVisible(true);
		// next button layout
		nextBox = new HBox();
		nextBox.setPrefSize(205, 50);
		nextBox.getChildren().add(next);
		nextBox.setAlignment(Pos.CENTER);
		// close button layout
		closeBox = new HBox();
		closeBox.setPrefSize(205, 50);
		closeBox.getChildren().add(close);
		closeBox.setAlignment(Pos.CENTER);
		// previous button layout
		previousBox = new HBox();
		previousBox.setPrefSize(205, 50);
		previousBox.getChildren().add(previous);
		previousBox.setAlignment(Pos.CENTER);

		// layout
		hoxie = new HBox();
		hoxie.getChildren().addAll(previousBox, closeBox, nextBox);
		hoxie.setAlignment(Pos.CENTER);
		hoxie.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, null, null)));
		// layout
		boxie = new VBox();
		boxie.getChildren().addAll(image, hoxie);

		scene = new Scene(boxie, 615, 845);
		stage.setScene(scene);
		stage.show();

	}

	public void creatPage2() {
		page = new Image("/Resources/uitleg_pagina_2.png");
		createStage1();
		previous.setVisible(true);
		next.setOnMouseClicked(e -> creatPage3());
		previous.setOnMouseClicked(e -> {
			page = new Image("/Resources/uitleg_pagina_1.png");
			createStage1();
		});

	}

	public void creatPage3() {
		page = new Image("/Resources/uitleg_pagina_3.png");
		createStage1();
		previous.setVisible(true);
		next.setOnMouseClicked(e -> creatPage4());
		previous.setOnMouseClicked(e -> creatPage2());
	}

	public void creatPage4() {
		page = new Image("/Resources/uitleg_pagina_4.png");
		createStage1();
		previous.setVisible(true);
		next.setVisible(false);
		previous.setOnMouseClicked(e -> creatPage3());
	}

}
