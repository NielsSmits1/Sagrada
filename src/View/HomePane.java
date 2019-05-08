package View;

import controller.ChallengerController;
import controller.HomeController;
import controller.MenubarController;
import controller.MyScene;
import controller.PlayerController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomePane extends Pane{
	private ChallengerPane challenger;
	private ChallengesPane challenges;
	private SearchPlayerPane search;
	private Menubar menu;
	private Label lb;
	private HBox box;
	private VBox boxie;
	private Font f = new Font(20);
	private int x = 250;
	
	public HomePane(PlayerController self, MyScene scene) {
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));

		
		
		setPanes("Uw uitdagingen",challenges);
		
		x += 400;
		
		setPanes("Wie u heeft uitgedaagt", challenger);
		
		x += 400;
		
		setPanes("Zoek een speler", search);
		
		this.getChildren().add(menu);
	}
	

	public HomePane(SearchPlayerPane searchPlayerPane, ChallengerPane challengerPane, ChallengesPane challengesPane, Menubar menubar) {
		search = searchPlayerPane;
		challenges = challengesPane;
		challenger = challengerPane;
		menu = menubar;
	}


	private void logOut() {
		//main.setRoot(new InlogPane(main));
	}

	private void setPanes(String text, SearchPlayerPane scr) {
		lb = new Label(text);
		boxie = new VBox();
		scr.setPrefSize(250, 400);
		
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(250, 500);
		lb.setFont(f);
		
		boxie.getChildren().addAll(lb,scr);
		this.getChildren().add(boxie);
		
	}

	private void setPanes(String text, ScrollPane scr) {
		Label lb = new Label(text);
		boxie = new VBox();
		scr.setPrefSize(250, 400);
		
		boxie.setLayoutX(x);
		boxie.setLayoutY(200);
		boxie.setPrefSize(300, 500);
		lb.setFont(f);
		
		boxie.getChildren().addAll(lb,scr);
		this.getChildren().add(boxie);
	}
	

}
