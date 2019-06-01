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
		
		
		
		
//		addSpace();
	}
	
	public void addSpace() { // i = 1 box
		
		for (int i = 0; i < 10; i++) {
			template = new DicePane();
			dices = new ArrayList<>();
			//dices.add(new DicePane(3, "blauw", 8));
			template.setTransparent();
//			template.getChildren().addAll(dices);
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
	public void setRoundTrack(ArrayList<ArrayList<Dice>> d) {
		int z;
		String y;
		int x;
		trackSpaces = new HBox();
		trackSpaces.setSpacing(8);
		this.getChildren().addAll(trackSpaces);
		
		for(int i = 0; i<d.size();i++) {									// gets round [1-10]
			template = new DicePane();
			dices = new ArrayList<DicePane>();
			for (int j = 0; j<d.get(i).size();j++) {						// gets all leftover dice from the round
				z=(int) d.get(i).get(j).getEyes();
				y=(String) d.get(i).get(j).getDieColor();
				x=(int) d.get(i).get(j).getDieNumber();
				dices.add(new DicePane(z,y,x));								// adds to arraylist<DicePane>      needs to be a stackedPane
				
				
		}
			
			template.setTransparent();
			template.getChildren().addAll(dices);
			trackSpaces.getChildren().addAll(template);	
				
		}
		
	}
	public DicePane getDicePane() {
		return null;
	}
}
