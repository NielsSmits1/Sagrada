package View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class InlogPane extends BorderPane {
	private Button loginButton;
	private Button registerButton;
	private VBox layout;
	private TextField usernameField;
	private TextField passwordField;
	private HBox buttonAlignment;
	private Label gameTitle;
	private BorderPane textAlignment;


	public InlogPane() {

		//sets background_image
		this.setBackground(new Background(new BackgroundImage(new Image("file:images/loginWallpaper.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				new BackgroundSize(0, 0, false, false, false, true))));

		usernameField = new TextField();
		usernameField.setPromptText("Gebruikersnaam...");
		usernameField.setPrefHeight(50);
		usernameField.setMaxWidth(400);
		usernameField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		
		passwordField = new TextField();
		passwordField.setPromptText("Wachtwoord...");
		passwordField.setPrefHeight(50);
		passwordField.setMaxWidth(400);
		passwordField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		// Login Button
		loginButton = new Button("Inloggen");
		loginButton.setTextAlignment(TextAlignment.CENTER);
		loginButton.setPrefHeight(50);
		loginButton.setPrefWidth(100);

		// Register Button
		registerButton = new Button("Registreren");
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
		setCenter(layout);
		setTop(textAlignment);
	}

	public String getUsernameText() {
		return this.usernameField.getText();
	}
	
	public String getPasswordText() {
		return this.passwordField.getText();
	}

	public void giveErrorBox() {
		usernameField.setBorder(
				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		passwordField.setBorder(
				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	}
	
	public void acceptedLogin() {
		usernameField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
		passwordField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public Button getRegisterButton() {
		return registerButton;
	}
	
	
}
