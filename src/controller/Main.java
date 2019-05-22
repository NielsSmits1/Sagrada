package controller;
import View.MyScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);	
	}

	@Override
	public void start(Stage stage) throws Exception {
		 MyScene scene = new MyScene();
	     stage.setScene(scene);
	     stage.setFullScreen(true);
	     stage.setTitle("Sagrada login and register");
	     stage.show();
	}

	private void check() {
		System.out.println("test");
	}

}
