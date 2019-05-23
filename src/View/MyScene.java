package View;

import controller.GameController;
import controller.InlogController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogController inlog = new InlogController(this);
    private GameController game = new GameController(this);

    public MyScene() {
        super(new Pane());
//        
//        setRoot(rules);
        
//         inlog.showInlog();

        
//        setRoot(inlog.showInlog());
    	setRoot(game.showOptions());


    }
    public void builtNewGame() {
    	
    	//setRoot(game.showOptions());
    	
    }
	
    public GameController getGame() {
		return null;
	}
    
    public void closeThis() {
    	
    }
    
    

}
