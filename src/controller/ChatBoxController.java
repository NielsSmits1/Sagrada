package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import View.ChatBox;
import View.ChatLine;
import model.ChatBoxModel;

public class ChatBoxController {

	private ChatBoxModel model;
	private ChatBox chat;

	public ChatBoxController() {
		model = new ChatBoxModel();
		chat = new ChatBox();
		getChat();
		chat.getSend().setOnAction(e -> sendMessages());

	}

	public void setPlayerName() {
		model.playerUserName();
		model.playerUsername();

		// geef de menutab een gameid mee om op te controleren zodat de tab enkel de
		// juiste zinnen laat zien
		// eerste pane laat alle chat zien met spel erbij
	}

	public void getChat() {
		DateFormat chatTime = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");

		ArrayList<ChatLine> chatbox = new ArrayList<>();

		for (ArrayList<Object> chat : model.playerUserName()) {
			ChatLine poekie = new ChatLine();
			poekie.setUserName((String) chat.get(0));
			poekie.setMessage((String) chat.get(1));
			poekie.setTime(chatTime.format(chat.get(2)));
			poekie.setLabels();

			chatbox.add(poekie);

		}
		chat.setChat(chatbox);
	}

	public ChatBox getScreen() {
		return chat;
	}
	
	public void sendMessages() {
		String input = "";
		input = chat.getInPut().getText();
		model.sendCUD(input);
		chat.getInPut().clear();
		getChat();//dit in de refresh
	}
	
	public void refresh() {
		getChat();
	}
	

}
