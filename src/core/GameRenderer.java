package core;

import java.awt.Font;
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

	private ArrayList<ArrayList<GameObject>> renderingLayers = new ArrayList<ArrayList<GameObject>>();

	public void update(Game gameInstace) {

		renderingLayers.clear();
		generateLayers(gameInstace.scene);

		BufferedImage i = new BufferedImage(this.window.panel.getWidth(), window.panel.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = i.createGraphics();

		for (ArrayList<GameObject> arrayList : renderingLayers)
			for (GameObject gameObject : arrayList) {
				if (gameObject instanceof ImageObject) {
					ImageObject io = (ImageObject) gameObject;
					g2d.drawImage(imageRegistry.getImage(io.getImg()), io.transform.getAbsoluteX(),
							io.transform.getAbsoluteY(), null);
				} else if (gameObject instanceof TextObject) {
					TextObject to = (TextObject) gameObject;
					g2d.setColor(to.getTextColor());
					g2d.setFont(to.getFont());
					g2d.drawString(to.getText(), to.transform.getAbsoluteX(), to.transform.getAbsoluteY());
				}

			}
		window.setPanelBC(i);
	}

	@SuppressWarnings("unchecked")
	private void generateLayers(GameObject scene) {

		int layer = scene.transform.layer;
		if (renderingLayers.size() <= layer)
			renderingLayers.add(layer, new ArrayList<GameObject>());
		renderingLayers.get(layer).add(scene);
		scene.isRendered = true;

		for (GameObject gameObject : (ArrayList<GameObject>) scene.getGameObjects().clone()) {

			if (gameObject.getGameObjects().size() > 0)
				generateLayers(gameObject);
			else {
				layer = gameObject.transform.layer;
				if (renderingLayers.size() <= layer)
					renderingLayers.add(layer, new ArrayList<GameObject>());
				renderingLayers.get(layer).add(gameObject);
				gameObject.isRendered = true;
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
