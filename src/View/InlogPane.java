package View;

import controller.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class InlogPane extends BorderPane {
	private Button loginButton;
	private Button registerButton;
	private VBox layout;
	private TextArea usernameField;
	private TextArea passwordField;
	private HBox buttonAlignment;
	private Scene main;
	private PlayerController player;
	private Label gameTitle;
	private BorderPane textAlignment;

	public InlogPane(MyScene myScene) {
		main = myScene;
		//this.setBackground(new Background(new BackgroundImage(new Image("file:images/loginWallpaper.jpg"),
				//BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				//new BackgroundSize(0, 0, false, false, false, true))));
	

		// Username textArea
		usernameField = new TextArea();
		usernameField.setPromptText("Username...");
		usernameField.setPrefHeight(50);
		usernameField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		// Password TextArea
		passwordField = new TextArea();
		passwordField.setPromptText("Password...");
		passwordField.setPrefHeight(50);
		passwordField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		// Login Button
		loginButton = new Button("Inloggen");
		loginButton.setOnAction(e -> handleLogin());
		loginButton.setTextAlignment(TextAlignment.CENTER);
		loginButton.setPrefHeight(50);
		loginButton.setPrefWidth(100);

		// Register Button
		registerButton = new Button("Register");
		registerButton.setOnAction(e -> handleRegister());
		registerButton.setTextAlignment(TextAlignment.CENTER);
		registerButton.setPrefHeight(50);
		registerButton.setPrefWidth(100);

		// Button alignment
		buttonAlignment = new HBox();
		buttonAlignment.getChildren().addAll(loginButton, registerButton);
		buttonAlignment.setSpacing(20);
		buttonAlignment.setAlignment(Pos.CENTER);
		
		// Sagrada label
		gameTitle = new Label();
		gameTitle.setText("Sagrada");
		gameTitle.setTextFill(Color.BLACK);
		gameTitle.setFont(new Font("Arial", 50));
		gameTitle.setPadding(new Insets(50));
		textAlignment = new BorderPane();
		textAlignment.setCenter(gameTitle);
		
		// aligns the pane in it's total
		layout = new VBox();
		layout.getChildren().addAll(usernameField, passwordField, buttonAlignment);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setSpacing(20);
		loginButton.setAlignment(Pos.CENTER);
		layout.setAlignment(Pos.CENTER);
		layout.setPrefHeight(myScene.getHeight());
		setCenter(layout);
		setTop(textAlignment);
	}

	private void handleRegister() {
		player = new PlayerController(usernameField.getText(), passwordField.getText());
		if (!player.hasRows() && player.validateAccountRequirement()) {
			player.newUser();
			System.out.println("Register accepted");
			giveSuccessfulBox();
		} else {
			System.out.println("Register failed");
			giveErrorBox();
		}
	}

	private void handleLogin() {
		player = new PlayerController(usernameField.getText(), passwordField.getText());
		if (player.hasRows() && !(usernameField.getText().equals("")) && !(passwordField.getText().equals(""))) {
			main.setRoot(new Home(player, main));
		} else {
			giveErrorBox();

		}
	}

	private void giveErrorBox() {
		usernameField.setBorder(
				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		passwordField.setBorder(
				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	}
	
	private void giveSuccessfulBox() {
		usernameField.setBorder(
				new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		passwordField.setBorder(
				new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	}

}
