package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RoundTrack extends Pane {
	private ImageView roundTrack;
	
	private Image track;
	private HBox diceTrack;
	private Rectangle dice;

	
	public RoundTrack() {
		track = new Image("/Resources/sagradaRonde.jpg");
		diceTrack = new HBox();
		
		roundTrack = new ImageView();
		this.getChildren().addAll(roundTrack);
		
		
		
		
		
//		dice = new Rectangle(0, 0, 70, 70);
//		dice.setStroke(Color.BLACK);
	}
}
