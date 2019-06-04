package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObjectiveCardPane extends Pane{
	private ImageView publicObjectiveCards;
	private Image publicObjectiveCard;


	public ObjectiveCardPane(int id) {
		
		switch (id) {
		case 1:
			publicObjectiveCard = new Image("/Resources/tintvariëteit.png");
			break;
		case 2:
			publicObjectiveCard = new Image("/Resources/halfdonkere_tinten.png");
			break;
		case 3:
			publicObjectiveCard = new Image("/Resources/tintenvariëteit_per_kolom.png");
			break;
		case 4:
			publicObjectiveCard = new Image("/Resources/kleurenvariëteit_per_kolom.png");
			break;
		case 5:
			publicObjectiveCard = new Image("/Resources/donkere_tinten.png");
			break;
		case 6:
			publicObjectiveCard = new Image("/Resources/kleurenvariëteit.png");
			break;
		case 7:
			publicObjectiveCard = new Image("/Resources/kleurenvariëteit_per_rij.png");
			break;
		case 8:
			publicObjectiveCard = new Image("/Resources/kleurendiagonalen.png");
			break;
		case 9:
			publicObjectiveCard = new Image("/Resources/lichte_tinten.png");
			break;
		case 10:
			publicObjectiveCard = new Image("/Resources/tintenvariëteit_per_rij.png");
			break;
		}
		
		publicObjectiveCards = new ImageView(publicObjectiveCard);
		publicObjectiveCards.setFitHeight(280);
		publicObjectiveCards.setFitWidth(200);
			
		getChildren().addAll(publicObjectiveCards);
	}
	
}
