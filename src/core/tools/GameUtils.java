package core.tools;

import core.Game;
import core.GameObject;

public class GameUtils {

	public static Boolean isOnObject(GameObject obj, int x, int y) {
		return x > obj.transform.x && x < obj.transform.x + obj.transform.width && y > obj.transform.y
				&& y < obj.transform.y + obj.transform.height;
	}

	public static GameObject getGameObjectAtPositionOnTop(Game gameInstace, int x, int y) {

		GameObject curObj = gameInstace.scene;

		for (GameObject go : gameInstace.scene.getGameObjects()) {
			if (isOnObject(go, x, y))
				if (go.transform.layer >= curObj.transform.layer)
					curObj = go;

			GameObject temp = getOntop(curObj, go, x, y);
			if (temp.transform.layer >= curObj.transform.layer)
				curObj = temp;
		}

		return curObj;
	}

	private static GameObject getOntop(GameObject curObj, GameObject obj, int x, int y) {
		GameObject thiscurObj = curObj;

		for (GameObject go : obj.getGameObjects()) {
			if (isOnObject(go, x, y)) {
				if (go.transform.layer >= thiscurObj.transform.layer)
					thiscurObj = go;
			}

			if (go.getGameObjects().size() > 0) {
				GameObject temp = getOntop(thiscurObj, go, x, y);
				if (temp.transform.layer >= thiscurObj.transform.layer)
					thiscurObj = temp;
			}
		}

		return thiscurObj;
	}

}
