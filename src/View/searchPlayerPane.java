package View;

import controller.PlayerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class searchPlayerPane extends VBox  {
	private TextField online = new TextField();
	private Button challenge = new Button("Uitdagen");
	private Label user = new Label();
	private Button search = new Button("Zoeken");
	private PlayerController pc;
	public searchPlayerPane(){
		search.setOnAction(E -> search());
		//search.setMinSize(80, (500/3)-120);
		//search.setMaxSize(80, (500/3)-120);
		search.setPrefSize(80, (500/3)-120);
		search.setStyle("-fx-background-color: DARKGRAY; ");
		search.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
		search.setStyle("-fx-font-size: 14; ");
		
		online.setMaxSize(80, 120);
		online.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
		
		this.getChildren().setAll(online,search);
	}
	private void search() {
		String username = online.getText();
		if(!username.equals("")) {
			pc = new PlayerController(username);
			if(pc.usernameExist()) {
				user.setText(username);
				challenge.setOnAction(E -> challengePlayer(username));
				this.getChildren().addAll(user, challenge);
			}else {
				// foutmelding
				System.out.println("foutje");
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
	private void challengePlayer(String username) {
		//if(pc.checkalsjealineengamesitmetdesespeler(guery))
		//select username from player where game_idgame = (select game_idgame from player where username = 'bram')
		// check of je in een game sit met die player 
		// so wel foutmelding
		// so niet ninsert new game uitdaging bl bl bl
	}

	
}
