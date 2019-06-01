package View;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DecisionPane extends BorderPane{
	
	private Label informativeText;
	private Label errortext;
	private ArrayList<Button> buttonOption;
	private GamePane gamepane;
	private HBox buttons;
	private int chosenNumber;
	
	public DecisionPane(GamePane gamepane) {
		this.gamepane = gamepane;
		chosenNumber = 1;
		buttonOption = new ArrayList<>();
		informativeText = new Label("Kies!");
		errortext = new Label("");
		buttons = new HBox();
		setTop(informativeText);
		setCenter(buttons);
		setBottom(errortext);
	}

	public void minus() {
		gamepane.downSelected();
	}
	
	public void equal() {
		gamepane.SelectedStaysEqual();
	}
	
	public void plus() {
		gamepane.upSelected();
	}
	
	public void giveError() {
		errortext.setText("Dit is niet mogelijk");
	}
	
	public void showInfoBoxToolcardOne() {	
		buttons.getChildren().clear();
		buttonOption.clear();
		buttonOption.add(new Button("één omlaag"));
		buttonOption.get(0).setOnAction(e -> minus());
		buttonOption.add(new Button("niks aan doen!"));
		buttonOption.get(1).setOnAction(e -> equal());
		buttonOption.add(new Button("één omhoog"));
		buttonOption.get(2).setOnAction(e -> plus());
		buttons.getChildren().addAll(buttonOption);
	}
	
	public void showInfoBoxToolcardEleven() {
		buttons.getChildren().clear();
		buttonOption.removeAll(buttonOption);
		buttonOption.add(new Button("1"));
		buttonOption.get(0).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(0).getText())));
		buttonOption.add(new Button("2"));
		buttonOption.get(1).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(1).getText())));
		buttonOption.add(new Button("3"));
		buttonOption.get(2).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(2).getText())));
		buttonOption.add(new Button("4"));
		buttonOption.get(3).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(3).getText())));
		buttonOption.add(new Button("5"));
		buttonOption.get(4).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(4).getText())));
		buttonOption.add(new Button("6"));
		buttonOption.get(5).setOnAction(e -> changeChosenNumber(Integer.parseInt(buttonOption.get(5).getText())));
		Button yes = new Button("Stop dit nummer terug in zak");
		yes.setOnAction(e -> handleYesButton());
		Button no = new Button("Behoudt deze dobbelsteen");
		no.setOnAction(e -> handleNoButton());
		buttons.getChildren().addAll(buttonOption);
		VBox option = new VBox();
		option.getChildren().addAll(buttons, no, yes);
		setCenter(option);
	}
	
	public void handleYesButton() {
		gamepane.swapDice(chosenNumber);
	}
	
	public void handleNoButton() {
		gamepane.SelectedStaysEqual();
	}
	
	public void changeChosenNumber(int number) {
		chosenNumber = number;
		informativeText.setText("Kies : " + chosenNumber);
	}
}
