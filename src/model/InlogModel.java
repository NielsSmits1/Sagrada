//package model;
//
//import View.Home;
//import View.InlogPane;
//import View.MyScene;
//import controller.PlayerController;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderStroke;
//import javafx.scene.layout.BorderStrokeStyle;
//import javafx.scene.layout.BorderWidths;
//import javafx.scene.paint.Color;
//
//public class InlogModel {
//	
//	private InlogPane inlog;
//	private MySceneModel sceneModel;
//	private MyScene scene;
//	
//	private Home home;
//	
//	public InlogModel(InlogPane inlog) {
//		this.inlog = inlog;
//		sceneModel = new MySceneModel(scene);
//	}
//	
//	
//	
//	public void handleRegister() {
//		
//		inlog.setPlayer(new PlayerController(inlog.getUsernameField().getText(),inlog.getPasswordField().getText())); 
//		if (!inlog.getPlayer().hasRows() && inlog.getPlayer().validateAccountRequirement()) {
//			inlog.getPlayer().newUser();
//			System.out.println("Register accepted");
//			giveSuccessfulBox();
//		} else {
//			System.out.println("Register failed");
//			giveErrorBox();
//		}
//	}
//	
//	public void handleLogin() {
//		
//		home = new Home(inlog.getPlayer(), inlog.getMain());
//		
//		inlog.setPlayer(new PlayerController(inlog.getUsernameField().getText(), inlog.getPasswordField().getText())); 
//		if (inlog.getPlayer().hasRows() && !(inlog.getUsernameField().getText().equals("")) && !(inlog.getPasswordField().getText().equals(""))) {
//			// sends the user to the game screen
//			System.out.println("Login accepted");
//			inlog.getMain().setRoot(sceneModel.createscene(home));
//		} else {
//			System.out.println("Login failed");
//			giveErrorBox();
//
//		}
//	}
//	
//	private void giveSuccessfulBox() {
//		inlog.getUsernameField().setBorder(
//				new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
//		inlog.getPasswordField().setBorder(
//				new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
//	}
//	
//	private void giveErrorBox() {
//		inlog.getUsernameField().setBorder(
//				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
//		inlog.getPasswordField().setBorder(
//				new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
//	}
//
//}
