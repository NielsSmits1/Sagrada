package View;
import controller.MenubarController;
import controller.PlayerController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.MenubarModel;

public class MyScene extends Scene {
    private InlogPane launcher;
    private RootPane game;
    private VBox vbox;
    private Home home;
    private PlayerController player;
    private MenubarController menuController;
    private MenubarModel menuModel;
    private Menubar menu;

    public MyScene() {
        super(new Pane(), 500, 500);
        launcher = new InlogPane(this);
        game = new RootPane();
        player = new PlayerController(getUserAgentStylesheet());
        home = new Home(player, this);
        menuModel = new MenubarModel();
        menu = new Menubar();
        menuController = new MenubarController(menuModel, menu);
        
       
        setRoot(new VBox(menu,new VBox(launcher)));
    }

}
