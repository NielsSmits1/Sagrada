package View;

import java.util.ArrayList;

import controller.GameController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Dice;
import model.Game;


public class RoundTrack extends Pane {
	private ImageView roundTrack;
	private Image track;
	private Button nextDice1;
	private Button nextDice2;
	private Button nextDice3;
	private Button nextDice4;
	private Button nextDice5;
	private Button nextDice6;
	private Button nextDice7;
	private Button nextDice8;
	private Button nextDice9;
	private Button nextDice10;
	
	private ArrayList<ArrayList<Dice>> d;
	private Pane pane;
	private Pane imagePane;
	private RoundPane round;
	private HBox roundBox;
	private HBox buttonBox;
	private VBox totalBox;
	
	private GameController game;
	private Game gameModel;
	private int x;
	
	public RoundTrack(GameController game, Game gameModel, ArrayList<ArrayList<Dice>> d) {
		this.game = game;
		this.gameModel = gameModel;
		this.d = d;
		pane = new Pane();
		imagePane = new Pane();
		round = new RoundPane();
		roundBox = new HBox();
		roundBox.setSpacing(20);
		buttonBox = new HBox();
		totalBox = new VBox();
		
		buildTrack();
		buildButtons();
		setRoundTrack(this.d);
		
		pane.getChildren().addAll(round);
		
		roundBox.getChildren().addAll(pane);
		buttonBox.getChildren().addAll(nextDice1, nextDice2, nextDice3, nextDice4, nextDice5,
				nextDice6, nextDice7, nextDice8, nextDice9, nextDice10);
		
		this.getChildren().addAll(roundBox);
	}
	
	public void buildButtons() {
		nextDice1 = new Button("  Volg 1  ");
		
		nextDice1.setOnAction(e -> round.getNextDice(0, d.get(0)));
		
		nextDice2 = new Button("  Volg 2  ");
		
		nextDice2.setOnAction(e -> round.getNextDice(1, d.get(1)));
		
		nextDice3 = new Button("   Volg 3  ");
		
		nextDice3.setOnAction(e -> round.getNextDice(2 ,d.get(2)));
		
		nextDice4 = new Button("  Volg 4  ");
		
		nextDice4.setOnAction(e -> round.getNextDice(3, d.get(3)));
		
		nextDice5 = new Button("  Volg 5  ");
		
		nextDice5.setOnAction(e -> round.getNextDice(4, d.get(4)));
		
		nextDice6 = new Button("   Volg 6  ");
		
		nextDice6.setOnAction(e -> round.getNextDice(5, d.get(5)));
		
		nextDice7 = new Button("  Volg 7  ");
		
		nextDice7.setOnAction(e -> round.getNextDice(6, d.get(6)));
		
		nextDice8 = new Button("  Volg 8  ");
		
		nextDice8.setOnAction(e -> round.getNextDice(7, d.get(7)));
		
		nextDice9 = new Button("   Volg 9  ");
		
		nextDice9.setOnAction(e -> round.getNextDice(8, d.get(8)));
		
		nextDice10 = new Button("  Volg 10 ");
		
		nextDice10.setOnAction(e -> round.getNextDice(9, d.get(9)));
	}
	
	public void buildTrack() {
		track = new Image("/Resources/RondeTrack.jpg");
		
		roundTrack = new ImageView(track);
		roundTrack.setFitHeight(50);
		roundTrack.setFitWidth(580);
		pane.setMinSize(580, 100);
		pane.getChildren().addAll(new VBox(roundTrack, buttonBox));
		
	}
	
	public void setRoundTrack(ArrayList<ArrayList<Dice>> d) {
		this.d = d;
		round.setRoundTrack(this.d);
		buildButtons();
	}
	
	
}