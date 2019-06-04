package View;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SearchPlayerPane extends VBox {
	private TextField online = new TextField();
	private Button challenge = new Button("Uitdagen");
	private ChoiceBox choice;
	private Label user = new Label();
	private Button search = new Button("Zoeken");
	private Button stats = new Button("Statistieken");
	private Alert alert = new Alert(AlertType.ERROR);
	private String username;

	public SearchPlayerPane() {
		online.setFont(Font.font(14));
		online.setMaxWidth(120);
		this.getChildren().setAll(online, search);
	}

	public void setChoiceBox(ArrayList<String> op) {
		this.getChildren().remove(choice);
		choice = new ChoiceBox(FXCollections.observableArrayList(op));
		this.getChildren().add(choice);
	}

	public String getChoice() {
		return choice.getValue().toString();
	}

	public void alert(String message) {
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	public void showPlayer(String username) {
		VBox boxie = new VBox();
		boxie.setLayoutY(30);
		boxie.setMinWidth(300);
		user.setFont(Font.font(20));
		user.setTextFill(Color.WHITE);
		user.setText(username);
		boxie.getChildren().addAll(user, stats, challenge);

		this.getChildren().add(boxie);

	}

	public Button getStats() {
		return stats;
	}

	public TextField getOnline() {
		return online;
	}

	public String getUsername() {
		return username;
	}

	public Button getChallenge() {

		return challenge;
	}

	public Alert getAlert() {
		return alert;
	}

	public Button getSearch() {
		return search;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSearch(Button search) {
		this.search = search;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public void setOnline(TextField online) {
		this.online = online;
	}

	public void setChallenge(Button challenge) {
		this.challenge = challenge;
	}

	public void setStats(Button stats) {
		this.stats = stats;
	}

}
