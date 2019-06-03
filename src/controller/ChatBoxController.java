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

	public ChatBoxController(int gameID, int OwnId) {
		model = new ChatBoxModel();
		model.setGameId(gameID);
		
		model.setPlayerId(OwnId);
		System.out.println("gameid + ownid" + gameID	+ OwnId );
		chat = new ChatBox();
		getChat();
		chat.getSend().setOnAction(e -> sendMessages());
		

	}

	public void getChat() {

		DateFormat chatTime = new SimpleDateFormat("HH:mm:ss dd-MM-YYYY ");

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
		System.out.println("ik pas");
		String input = "";
		input = chat.getInPut().getText();
		model.sendCUD(input);
		chat.getInPut().clear();
		getChat();// dit is de refresh
	}
	

	public void refresh() {
		getChat();
	}

	public ChatBoxModel getModel() {
		return model;
	}
	
	

}
