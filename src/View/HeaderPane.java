package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class HeaderPane extends TilePane{
	
	private Label headerText;
	public HeaderPane() {
		headerText = new Label("Label tekst");
		setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null)));
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		setAlignment(Pos.CENTER);
		setPrefWidth(headerText.getWidth());
		getChildren().add(headerText);
	}
	
	public void changeLabel(String s) {
		headerText.setText(s);
	}
	
}
