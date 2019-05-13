package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	private TextArea usernameField;
	private TextArea passwordField;
	private HBox buttonAlignment;
	private Label gameTitle;
	private BorderPane textAlignment;


	public InlogPane(MyScene scene) {


		usernameField = new TextArea();
		usernameField.setPromptText("Username...");
		usernameField.setPrefHeight(50);
		usernameField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		
		passwordField = new TextArea();
		passwordField.setPromptText("Password...");
		passwordField.setPrefHeight(50);
		passwordField.setBorder(
				new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

		// Login Button
		loginButton = new Button("Inloggen");
		loginButton.setTextAlignment(TextAlignment.CENTER);
		loginButton.setPrefHeight(50);
		loginButton.setPrefWidth(100);

		// Register Button
		registerButton = new Button("Register");
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

//	public InlogPane(EventHandler<ActionEvent> login, EventHandler<ActionEvent> register) {
//		loginButton.setOnAction(login);
//		registerButton.setOnAction(register);
//	}

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

	public Button getLoginButton() {
		return loginButton;
	}

	public Button getRegisterButton() {
		return registerButton;
	}
	
	
}
