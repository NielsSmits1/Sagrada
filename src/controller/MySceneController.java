package controller;

import View.MyScene;
import model.MySceneModel;

public class MySceneController {
	
	private MyScene myScene;
	private MySceneModel mySceneModel;
	
	public MySceneController(MySceneModel myscenemodel, MyScene myScene) {
		this.myScene = myScene;
		this.mySceneModel = myscenemodel;
	}

}
