import View.MyScene;
import javafx.application.Application;
import javafx.stage.Stage;
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
	     
	     System.out.println("haai");
	}

}
