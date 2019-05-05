package View;

import controller.HomeController;
import controller.MenubarController;
import controller.PlayerController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Home extends Pane{
	private ChallengerPane challenger;
	private ChallengesPane challenges;
	private SearchPlayerPane search;
	private Menubar menu;
	private HomeController home;
	private MyScene main;
	Label lb;
	HBox box;
	VBox boxie;
	Font f = new Font(20);
	int x = 250;
	
	public Home(PlayerController self, MyScene scene) {
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		home = new HomeController(self);
		main = scene;
		search = new SearchPlayerPane(home);
		challenges = new ChallengesPane(home);
		challenger = new ChallengerPane(home);
		menu = new Menubar(scene, self);
		
		setPanes("Uw uitdagingen",challenges);
		
		x += 400;
		
		setPanes("Wie u heeft uitgedaagt", challenger);
		
		x += 400;
		
		setPanes("Zoek een speler", search);
		
		setLogout();
		
		this.getChildren().add(menu);
	}
	

	private Menubar Menubar(MyScene scene) {
		// TODO Auto-generated method stub
		return null;
	}


	private void setLogout() {
		box = new HBox();
		lb = new Label("Welkom " + home.getUsername());
		lb.setFont(f);
		Button loggout = new Button("Uitloggen");
		loggout.setLayoutX(30);
		loggout.setOnAction(E -> logOut());
		box.getChildren().addAll(lb, loggout);
		box.setLayoutY(30);
		box.setPrefWidth(500);
		this.getChildren().add(box);
	}

	private void logOut() {
		main.setRoot(new InlogPane(main));
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
	
	/*private VBox rightPane;
	private Pane centerPane;
	private Pane rightTopPane;
	private Pane rightCenterPane;


	private Pane textbox;
	private HBox total_textarea;
	private VBox rightArea;
	private TextArea rightTextArea;
	private TextArea leftTextArea;
	private Button refresh;

//	private TextArea online;
//	private Button search;
	
	private Color rightbackground = Color.SLATEGRAY;
	private Color centerbackground = Color.CORNFLOWERBLUE;
	

	==BRAM_03_05_19
	//private HBox menuBar;
	private Menubar menu;
	private PlayerController player;
	private Scene main;
	private searchPlayerPane searchPlayer;
	
	
	public Home(PlayerController player, Scene scene) {
		super();
		menu = new Menubar();
		this.player = player;

		makeCenterPane();
		makeRightBorderPane();
		
		main = scene;
		
		

		centerPane.getChildren().add(menu);
	==BRAM
	}
	private void makeRightBorderPane() {
		rightPane = new VBox();
		rightTopPane = new Pane();
		rightCenterPane = new Pane();
		searchPlayer = new searchPlayerPane(player);
		//System.out.println(player.getPlayer().getPassword());
		
		
		//this is the text area and button where you can search players
//		online = new TextArea();
//		online.setMaxSize(80, 120);
//		online.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
//		search = new Button("Zoeken");
//		search.setMinSize(80, (500/3)-120);
//		search.setMaxSize(80, (500/3)-120);
//		search.setPrefSize(80, (500/3)-120);
//		search.setStyle("-fx-background-color: DARKGRAY; ");
//		search.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
//		search.setStyle("-fx-font-size: 14; ");
		
		

		//top borderpane
		rightTopPane.setMinSize(100, (500 / 3));
		rightTopPane.setMaxSize(100, (500 / 3));
		rightTopPane.setPrefSize(100, (500 / 3));
		rightTopPane.setBackground(new Background(new BackgroundFill(rightbackground, null, null)));

		
		
		//middle borderpane
		rightCenterPane.setMinSize(100, (500 / 3));
		rightCenterPane.setMaxSize(100, (500 / 3));
		rightCenterPane.setPrefSize(100, (500 / 3));
		rightCenterPane.setBackground(new Background(new BackgroundFill(rightbackground, null, null)));
		
		
		
		//bottom borderpane
				searchPlayer.setMinSize(100, (500 / 3));
				searchPlayer.setMaxSize(100, (500 / 3));
				searchPlayer.setPrefSize(100, (500 / 3));
				searchPlayer.setBackground(new Background(new BackgroundFill(rightbackground, null, null)));

		
		
		//borderpane
		rightPane.setMinSize(80, 500);
		rightPane.setMaxSize(80, 500);
		rightPane.getChildren().addAll(rightTopPane, rightCenterPane, searchPlayer);

		
		
		setRight(rightPane);

	}

	public void makeCenterPane() {
		centerPane = new Pane();
		centerPane.setBackground(new Background(new BackgroundFill(centerbackground, null, null)));

		
		
		// this contains leftTextArea.
		textbox = new Pane();
		textbox.setMinSize(100, 250);
		textbox.setMaxSize(100, 250);
		textbox.setPrefSize(100, 250);

		
		
		// this contains textbox and rightArea.
		total_textarea = new HBox();
		total_textarea.setMinSize(200, 240);
		total_textarea.setMaxSize(200, 240);
		total_textarea.setPrefSize(200, 240);
		
		

		// this contains RightTextArea and refreshButton.
		rightArea = new VBox();
		rightArea.setMinSize(100, 240);
		rightArea.setMaxSize(100, 240);
		rightArea.setPrefSize(100, 240);	
		
		
		
		//this is in rightArea.
		rightTextArea = new TextArea();
		rightTextArea.setMinSize(100, 240);
		rightTextArea.setMaxSize(100, 240);
		rightTextArea.setPrefSize(100, 240);
		rightTextArea.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
		refresh = new Button("Refresh");
		refresh.setMaxWidth(100);
		refresh.setMinWidth(100);
		refresh.setPrefWidth(100);
		refresh.setStyle("-fx-background-color: DARKGRAY; ");
		refresh.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");
		refresh.setStyle("-fx-font-size: 14; ");
		
		
		
		// this will be in textbox.
		leftTextArea = new TextArea();
		leftTextArea.setMinSize(100, 240);
		leftTextArea.setMaxSize(100, 240);
		leftTextArea.setPrefSize(100, 240);
		leftTextArea.setStyle("-fx-border-color: BLACK; -fx-border-width: 1px;");

		
		
		//this is the leftTextArea.
		textbox.getChildren().add(leftTextArea);
		
		
		
		// this is the rightTextArea and button
		rightArea.getChildren().addAll(rightTextArea, refresh);
		
		
		
		//this contains textbox and rightArea.
		total_textarea.getChildren().addAll(textbox, rightArea);

		
		
		//this contains the textarea's and button
		centerPane.getChildren().add(total_textarea);
		total_textarea.setLayoutX(200/2);
		total_textarea.setLayoutY((500/4));
		

		
		setCenter(centerPane);

	}*/

}
