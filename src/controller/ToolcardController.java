package controller;

import java.util.ArrayList;

import View.ToolCardPane;
import model.Toolcard;

public class ToolcardController {
	private Toolcard toolcard;
//	private ArrayList<ArrayList<Object>> toolcards;
	private ArrayList<ToolCardPane> toolcardpanes;
	public ToolcardController() {
		toolcard = new Toolcard();
		toolcardpanes = new ArrayList<>();
		toolcardpanes.add(new ToolCardPane((String)toolcard.getToolcardsFromDatabase().get(0).get(1)));
		toolcardpanes.add(new ToolCardPane((String)toolcard.getToolcardsFromDatabase().get(1).get(1)));
		toolcardpanes.add(new ToolCardPane((String)toolcard.getToolcardsFromDatabase().get(2).get(1)));
//		toolcards = toolcard.getToolcardsFromDaW
	}

}
