package View;

import controller.GameController;
import controller.InlogController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogController inlog = new InlogController(this);
    private GameController game = new GameController(this);
//    private GameProgress gameProgress = new GameProgress();
    

    public MyScene() {
        super(new Pane());
//        
//        setRoot(rules);
        
         inlog.showInlog();
        setRoot(inlog.show());

    }
    public void builtNewGame() {
    	
    	setRoot(game.showOptions());
    	
    }
	
    public GameController getGame() {
		return game;
	}
    
    public void closeThis() {
    	
    }
    
    

}
