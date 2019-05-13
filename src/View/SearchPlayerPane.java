package View;

import controller.HomeController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SearchPlayerPane extends VBox  {
	private TextField online = new TextField();
	private Button challenge = new Button("Uitdagen");
	private Label user = new Label();
	private Button search = new Button("Zoeken");
	private Button stats = new Button("Statistieken");
	private Alert alert = new Alert(AlertType.ERROR);
	
	private HomeController hc;
	
	private String username;
	
	public SearchPlayerPane(HomeController home){
		hc = home;

		search.setOnAction(E -> search());
		online.setFont(Font.font(14));
		online.setMaxWidth(120);

		this.getChildren().setAll(online,search);
	}
	
	public SearchPlayerPane() {
		// TODO Auto-generated constructor stub
	}

	private void alert(String message) {
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	private void search() {
		this.username = online.getText();
		if(!username.equals("")) {
			if(hc.usernameExist(username)) {
				showPlayer(username);
				
				
			}else {
				alert("Niemand gevonden met deze gebruikersnaam");
			}
		}
		
		/*
		 * de tekst ophlen --------
		 * Checken of username exists 
		 *     
		 * de tekst doorsturen naar de controller
		 * controller mss even checken
		 * model aanmaken
		 * mss ook wat dingen doen
		 * iets met db
		 * en iets terug krijgen?
		 */
		
	}
	public void showPlayer(String username) {
		VBox boxie = new VBox();
		boxie.setLayoutY(30);
		boxie.setMinWidth(300);
		user.setFont(Font.font(20));
		user.setText(username);
//		stats.setOnAction(E-> showPlayerStats(username));
		challenge.setOnAction(E -> challengePlayer(username));
		boxie.getChildren().addAll(user,stats, challenge);
		
		this.getChildren().add(boxie);
		
	}

	private String showPlayerStats(String username) {
		
		return null;
	}

	private void challengePlayer(String u) {
		/*
		 * ik bouw eerst de juiste instantie homecontroller
		 * hey controllertje
		 *  kijk is even of ik in een spel met die andere
		 *  hey klopt
		 *  of niet
		 *  doe ietsa anders
		 *  
		 */
		/*if(pc.isInGame(u, self)) {
			//user.setText(username);
			//error.setText("Zit al in een game");
			//this.getChildren().remove(challenge);
			//this.getChildren().add(error);
		}
		else {
			/*self.challenge(u){
				// moet hij weer gegeven woirden in challenger box/pane
			}
			
		}*/
		
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
	
	
	
	
	
	

	
}
