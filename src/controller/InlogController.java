//package controller;
//
//import View.InlogPane;
//import model.InlogModel;
//
//public class InlogController {
//
//	private InlogPane inlogPane;
//	private InlogModel inlogModel;
//
//	public InlogController(InlogModel inlogModel, InlogPane inlogPane) {
//		this.inlogPane = inlogPane;
//		this.inlogModel = inlogModel;
//
//		
//		// this calls the button from the inlog view and calls the methode from the inlogmodel.
//		inlogPane.getLoginButton().setOnAction(e -> inlogModel.handleLogin());
//
//		// this calls the button from the inlog view and calls the methode from the inlogmodel.
//		inlogPane.getRegisterButton().setOnAction(e -> inlogModel.handleRegister());
//	}
//
//}
