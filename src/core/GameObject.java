package core;

import java.util.ArrayList;

import core.tools.Transform;

public class GameObject {

	public Transform transform = new Transform();
	public GameObject parent;
	public Game gameInstance;
	public Boolean isRendered = false;

	public GameObject(Game gameInstance) {
		this.gameInstance = gameInstance;
	}

	public GameObject(Game gameInstance, int x, int y) {
		this.gameInstance = gameInstance;
		transform.x = x;
		transform.y = y;
	}

	private ArrayList<GameObject> gameObjects = new ArrayList<>();

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void add(GameObject obj) {
		gameObjects.add(obj);
		obj.parent = this;
	}

}
