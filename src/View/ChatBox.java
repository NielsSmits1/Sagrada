package View;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChatBox extends ScrollPane {
	private TextField inPut;
	private Button send;
	private ArrayList<ChatLine> chatline;

	public ChatBox() {
		this.setPrefSize(500, 500);
	}

	public void setChat(ArrayList<ChatLine> lines) {
		this.chatline = lines;
		VBox box = new VBox();
		VBox chat = new VBox();
		inPut = new TextField();
		send = new Button("verstuur");
		chat.setPrefSize(500, 500);
		chat.setBackground(new Background(new BackgroundFill(Color.HOTPINK, null, null)));

		chat.getChildren().addAll(chatline);
		box.getChildren().addAll(chat, inPut, send);
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
	
	
}
