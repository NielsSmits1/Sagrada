package View;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DecisionPane extends BorderPane{
	
	private Label informativeText;
	private Label errortext;
	private ArrayList<Button> buttonOption;
	private GamePane gamepane;
	
	public DecisionPane(GamePane gamepane) {
		setButtons();
		this.gamepane = gamepane;
		informativeText = new Label("Kies!");
		errortext = new Label("");
		HBox buttons = new HBox();
		buttons.getChildren().addAll(buttonOption);
		setTop(informativeText);
		setCenter(buttons);
		setBottom(errortext);
	}
	
	private void setButtons() {
		buttonOption = new ArrayList<>();
		buttonOption.add(new Button("��n omlaag"));
		buttonOption.get(0).setOnAction(e -> minus());
		buttonOption.add(new Button("niks aan doen!"));
		buttonOption.get(1).setOnAction(e -> equal());
		buttonOption.add(new Button("��n omhoog"));
		buttonOption.get(2).setOnAction(e -> plus());
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
}
