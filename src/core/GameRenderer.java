package core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.tools.ImageRegistry;

public class GameRenderer {

	private Window window;
	public ImageRegistry imageRegistry = new ImageRegistry();

	public GameRenderer(Window window) {
		this.window = window;
	}

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private ArrayList<ArrayList<GameObject>> renderingLayers = new ArrayList<ArrayList<GameObject>>();

	public void update() {

		renderingLayers.clear();
		generateLayers(gameObjects);

		BufferedImage i = new BufferedImage(this.window.panel.getWidth(), window.panel.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = i.createGraphics();

		for (ArrayList<GameObject> arrayList : renderingLayers)
			for (GameObject gameObject : arrayList)
				g2d.drawImage(imageRegistry.getImage(gameObject.getImg()), gameObject.transform.getAbsoluteX(),
						gameObject.transform.getAbsoluteY(), null);

		window.setPanelBC(i);
	}

	private void generateLayers(ArrayList<GameObject> objects) {
		for (GameObject gameObject : objects) {

			int layer = gameObject.transform.layer;
			if (renderingLayers.size() <= layer)
				renderingLayers.add(layer, new ArrayList<GameObject>());
			renderingLayers.get(layer).add(gameObject);
			gameObject.isRendered = true;

			if (gameObject.getGameObjects().size() > 0)
				generateLayers(gameObject.getGameObjects());

		}
	}

	public void renderObject(GameObject obj) {
		gameObjects.add(obj);
	}

	public void stopRenderingObject(GameObject obj) {
		gameObjects.remove(obj);
		afterRemoved(obj);
	}

	public void stopRenderingObjects(String name) {
		for (GameObject gameObject : gameObjects) {
			if (gameObject.getName() == name) {
				gameObjects.remove(gameObject);
				afterRemoved(gameObject);
			}
		}
	}

	private void afterRemoved(GameObject obj) {
		for (GameObject gameObject : obj.getGameObjects()) {
			if (gameObject.getGameObjects().size() > 0)
				afterRemoved(gameObject);
			gameObject.isRendered = false;
		}
	}

}
