package controller;
import Database.Db;
import View.MyScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);	
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Db.setConn();
		 MyScene scene = new MyScene();
	     stage.setScene(scene);
	     stage.setFullScreen(true);
	     stage.setTitle("Sagrada groep G - 2019");
	     stage.initStyle(StageStyle.UNDECORATED);
	     stage.show();
	}

}
