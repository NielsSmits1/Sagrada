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
		nextDice.setPrefHeight(70);
		nextDice.setOnAction(e -> round.getNextDice());
		roundBox.getChildren().addAll(nextDice);
	}
	
	public void buildTrack() {
		track = new Image("/Resources/RondeTrack.jpg");
		
		roundTrack = new ImageView(track);
		roundTrack.setFitHeight(70);
		roundTrack.setFitWidth(780);
		pane.getChildren().addAll(roundTrack);
	}
	
	
}
