package View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class RoundTrack extends Pane {
	private ImageView roundTrack;
	private Image track;
	private Button nextDice;
	
	private Pane pane;
	private RoundPane round;
	private HBox roundBox;
//	private GamePane game;
	
	public RoundTrack() {
		pane = new Pane();
		round = new RoundPane();
		roundBox = new HBox();
		
		buildButton();
		buildTrack();
		
		
		pane.getChildren().addAll(round);
		roundBox.getChildren().addAll(pane);
		this.getChildren().addAll(roundBox);
		
	}
	
	public void buildButton() {
		nextDice = new Button("Volgende");
		nextDice.setPrefHeight(50);
		nextDice.setOnAction(e -> round.getNextDice());
		roundBox.getChildren().addAll(nextDice);
	}
	
	public void buildTrack() {
		track = new Image("/Resources/RondeTrack.jpg");
		
		roundTrack = new ImageView(track);
		roundTrack.setFitHeight(50);
		roundTrack.setFitWidth(580);
		pane.getChildren().addAll(roundTrack);
	}
	
//	public void leftover() {
//		game.getLeftovers;
//	}
	
	
}
