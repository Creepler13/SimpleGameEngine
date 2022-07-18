package core.tools;

import core.GameObject;

public class Transform {

	public int x = 0, y = 0, layer = 0;
	public int width = 0, height = 0;
	public double rot = 0;

	private GameObject owner;

	public Transform() {

	}

	public Transform(GameObject owner) {
		this.owner = owner;
	}

	public int getAbsoluteX() {
		if (owner == null)
			return x;
		if (owner.parent == null)
			return x;
		return owner.parent.transform.getAbsoluteX() + x;
	}

	public int getAbsoluteY() {
		if (owner == null)
			return y;
		if (owner.parent == null)
			return y;
		return owner.parent.transform.getAbsoluteY() + y;
	}

}
