package core;

import java.util.ArrayList;

import core.events.IEventListener;
import core.tools.Transform;

public class GameObject {

	public Transform transform = new Transform(this);
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

	public void addEventListener(IEventListener listener) {
		gameInstance.eventManager.addEventListener(this, listener);
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void add(GameObject obj) {
		gameObjects.add(obj);
		obj.parent = this;
	}

}
