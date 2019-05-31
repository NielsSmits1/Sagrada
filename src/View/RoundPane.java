package View;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Dice;

public class RoundPane extends StackPane {
	private DicePane template;
	private DicePane dice;
	private ArrayList<DicePane> dices;
	private HBox trackSpaces;
	
	private int counter = 0;
	
	public RoundPane() {
		trackSpaces = new HBox();
		trackSpaces.setSpacing(8);
		this.getChildren().addAll(trackSpaces);
		
		addSpace();
	}
	
	public void addSpace() {
		
		for (int i = 0; i < 10; i++) {
			template = new DicePane();
			dices = new ArrayList<>();
			//dices.add(new DicePane(3, "blauw", 8));
			template.setTransparent();
			template.getChildren().addAll(dices);
			trackSpaces.getChildren().addAll(template);	
		}
	}
	
	public void getNextDice() {
		if (counter < dices.size()) {
			dices.get(counter);
		} counter++;
	}
	
	//TODO get roundnumber from model and add dice
	public void addDice(int eyes, String color, int number) {
		dices.add(new DicePane(eyes,color,number));
		
	}
}
