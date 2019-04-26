package controller;

import View.InlogPane;
import model.InlogModel;

public class InlogController {
	
	private InlogPane inlogPane;
	private InlogModel inlogModel;
	
	public InlogController(InlogPane inlogPane, InlogModel inlogModel) {
		this.inlogPane = inlogPane;
		this.inlogModel = inlogModel;
	}

}
