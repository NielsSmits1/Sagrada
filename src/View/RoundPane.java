package View;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Dice;

public class RoundPane extends Pane {
	private DicePane template0;
	private DicePane template1;
	private DicePane template2;
	private DicePane template3;
	private DicePane template4;
	private DicePane template5;
	private DicePane template6;
	private DicePane template7;
	private DicePane template8;
	private DicePane template9;

	private ArrayList<DicePane> dices = new ArrayList<DicePane>();
	private HBox trackSpaces;

	private int g = -1;

	public RoundPane() {

		addSpace();
	}

	public void addSpace() { // i = 1 box
		trackSpaces = new HBox();
		template0 = new DicePane();
		template1 = new DicePane();
		template2 = new DicePane();
		template3 = new DicePane();
		template4 = new DicePane();
		template5 = new DicePane();
		template6 = new DicePane();
		template7 = new DicePane();
		template8 = new DicePane();
		template9 = new DicePane();
		template0.setTransparent();
		template1.setTransparent();
		template2.setTransparent();
		template3.setTransparent();
		template4.setTransparent();
		template5.setTransparent();
		template6.setTransparent();
		template7.setTransparent();
		template8.setTransparent();
		template9.setTransparent();
		trackSpaces.setSpacing(8);
		trackSpaces.getChildren().addAll(template0, template1, template2, template3, template4, template5, template6,
				template7, template8, template9);

	}

	public void addDice(int eyes, String color, int number) {
		dices.add(new DicePane(eyes, color, number));

	}

	public void setRoundTrack(ArrayList<ArrayList<Dice>> d) {
		int z;
		String y;
		int x;
		this.getChildren().clear();
		trackSpaces.getChildren().clear();
		

		dices.clear();

		for (int j = 0; j < d.get(0).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(0).get(j).getEyes();
			y = (String) d.get(0).get(j).getDieColor();
			x = (int) d.get(0).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template0.setTransparent();
//		template0.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template0);

		for (int j = 0; j < d.get(1).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(1).get(j).getEyes();
			y = (String) d.get(1).get(j).getDieColor();
			x = (int) d.get(1).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template1.setTransparent();
//		template1.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template1);

		for (int j = 0; j < d.get(2).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(2).get(j).getEyes();
			y = (String) d.get(2).get(j).getDieColor();
			x = (int) d.get(2).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template2.setTransparent();
//		template2.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template2);

		for (int j = 0; j < d.get(3).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(3).get(j).getEyes();
			y = (String) d.get(3).get(j).getDieColor();
			x = (int) d.get(3).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template3.setTransparent();
//		template3.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template3);

		for (int j = 0; j < d.get(4).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(4).get(j).getEyes();
			y = (String) d.get(4).get(j).getDieColor();
			x = (int) d.get(4).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template4.setTransparent();
//		template4.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template4);

		for (int j = 0; j < d.get(5).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(5).get(j).getEyes();
			y = (String) d.get(5).get(j).getDieColor();
			x = (int) d.get(5).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template5.setTransparent();
//		template5.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template5);

		for (int j = 0; j < d.get(6).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(6).get(j).getEyes();
			y = (String) d.get(6).get(j).getDieColor();
			x = (int) d.get(6).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template6.setTransparent();
//		template6.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template6);

		for (int j = 0; j < d.get(7).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(7).get(j).getEyes();
			y = (String) d.get(7).get(j).getDieColor();
			x = (int) d.get(7).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template7.setTransparent();
//		template7.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template7);

		for (int j = 0; j < d.get(8).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(8).get(j).getEyes();
			y = (String) d.get(8).get(j).getDieColor();
			x = (int) d.get(8).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template8.setTransparent();
//		template8.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template8);

		for (int j = 0; j < d.get(9).size(); j++) { // gets all leftover dice from the round
			z = (int) d.get(9).get(j).getEyes();
			y = (String) d.get(9).get(j).getDieColor();
			x = (int) d.get(9).get(j).getDieNumber();
			dices.add(new DicePane(z, y, x)); // adds to arraylist<DicePane> needs to be a stackedPane
		}

		template9.setTransparent();
		//template9.getChildren().addAll(dices);
		trackSpaces.getChildren().addAll(template9);
		this.getChildren().addAll(trackSpaces);

	}

	public void getNextDice(int b, ArrayList<Dice> d) {
		int z;
		String y;
		int x;
		dices.clear();
		
		if (g < d.size()) {
			g++;
			for (int j = 0; j < d.size(); j++) {
				z = (int) d.get(j).getEyes();
				y = (String) d.get(j).getDieColor();
				x = (int) d.get(j).getDieNumber();
				dices.add(new DicePane(z, y, x));

			}
			if (b == 0 && g < dices.size()) {
				template0.getChildren().addAll(dices.get(g));
			} else
			if (b == 1 && g < dices.size()) {
				template1.getChildren().addAll(dices.get(g));
			} else
			if (b == 2 && g < dices.size()) {
				template2.getChildren().addAll(dices.get(g));
			} else
			if (b == 3 && g < dices.size()) {
				template3.getChildren().addAll(dices.get(g));
			} else
			if (b == 4 && g < dices.size()) {
				template4.getChildren().addAll(dices.get(g));
			} else
			if (b == 5 && g < dices.size()) {
				template5.getChildren().addAll(dices.get(g));
			} else
			if (b == 6 && g < dices.size()) {
				template6.getChildren().addAll(dices.get(g));
			} else
			if (b == 7 && g < dices.size()) {
				template7.getChildren().addAll(dices.get(g));
			} else
			if (b == 8 && g < dices.size()) {
				template8.getChildren().addAll(dices.get(g));
			} else
			if (b == 9 && g < dices.size()) {
				template9.getChildren().addAll(dices.get(g));
			}
		} else {
			g = -1;
		}
		
		
	}

	public DicePane getDicePane() {
		return null;
	}
}