package View;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChatLine extends HBox {

	private Label userName;
	private Label message;
	private Label Time;
	VBox username = new VBox();
	VBox Message = new VBox();
	VBox time = new VBox();
	
	public ChatLine() {
		
		
		this.userName = new Label();
		this.message = new Label();
		this.message.setWrapText(true);
		
		this.Time = new Label();

		username.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		this.username.setPrefSize((500/3), 500);
		this.username.setMaxSize((500/3), 500);
		this.username.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		
		this.Message.setPrefSize((500/3), 500);
		this.Message.setMaxSize((500/3), 500);
		Message.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));
		this.Message.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		
		this.time.setPrefSize((500/3), 500);
		this.time.setMaxSize((500/3), 500);
		time.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
		this.time.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		username.getChildren().add(this.userName);
		Message.getChildren().add(this.message);
		time.getChildren().add(this.Time);
	}
	
	public Label getUserName() {
		return userName;
	}
	public void setUserName(String string) {
		this.userName.setText(string);
	}
	public Label getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message.setText(message);
	}
	public Label getTime() {
		return Time;
	}
	public void setTime(String time) {
		this.Time.setText(time.toString());
	}
	
	public void setLabels() {
		this.getChildren().addAll(username, Message, time);
	}
	
	
	
}
