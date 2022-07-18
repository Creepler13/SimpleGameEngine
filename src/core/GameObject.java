package core;

import java.util.ArrayList;

import core.events.IEvent;
import core.events.KeyEventListerner;
import core.tools.Transform;

public class GameObject {

	public Transform transform = new Transform();
	private String name = "";
	private String imgID = "";
	public GameObject parent;
	public Game gameInstace;
	public Boolean isRendered = false;

	public GameObject(Game g) {
		gameInstace = g;
	}

	public GameObject(Game g, String name) {
		gameInstace = g;
		this.setName(name);

	}

	public GameObject(Game g, int x, int y) {
		gameInstace = g;
		transform.x = x;
		transform.y = y;
	}

	public GameObject(Game g, int x, int y, String name) {
		gameInstace = g;
		transform.x = x;
		transform.y = y;
		this.setName(name);
	}

	private ArrayList<GameObject> gameObjects = new ArrayList<>();

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void addEventListener(IEvent eventListener) {
		eventListener.parent = this;

		if (eventListener instanceof KeyEventListerner)
			gameInstace.eventManager.keyListener.add((KeyEventListerner) eventListener);
	}

	public String getImg() {
		return imgID;
	}

	public void add(GameObject obj) {
		gameObjects.add(obj);
		obj.parent = this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(String imgID, Boolean fitSizeToImg) {
		this.imgID = imgID;
		if (!fitSizeToImg)
			return;
		transform.width = gameInstace.renderer.imageRegistry.getImage(imgID).getWidth();
		transform.height = gameInstace.renderer.imageRegistry.getImage(imgID).getHeight();

	}

}
