package View;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChatBox extends ScrollPane {
	private TextField inPut;
	private Button send = new Button("verstuur");
	private Button closee = new Button("sluiten");
	private ArrayList<ChatLine> chatline;

	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	public ChatBox() {
		this.setPrefSize(WIDTH, HEIGHT);
	}

	public void setChat(ArrayList<ChatLine> lines) {
		this.chatline = lines;
		VBox box = new VBox();
		VBox chat = new VBox();
		HBox close = new HBox();
		inPut = new TextField();

		chat.setPrefSize(WIDTH, HEIGHT);
		chat.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));

		chat.getChildren().addAll(chatline);
		close.getChildren().addAll(send,closee);
		close.setAlignment(Pos.CENTER);
		box.getChildren().addAll(chat, inPut, close);
		this.setContent(box);
		this.setHbarPolicy(getHbarPolicy().NEVER);
		this.setVbarPolicy(getVbarPolicy().AS_NEEDED);
	}

	public TextField getInPut() {
		return inPut;
	}

	public Button getSend() {
		return send;
	}
	
	public Button getClosee() {
		return closee;
	}

}
