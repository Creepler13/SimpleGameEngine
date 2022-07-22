package core.tools;

import core.GameObject;

public class Transform {

	public double x = 0, y = 0;
	public int layer = 0;
	public double width = 0, height = 0;
	public double rot = 0;

	private GameObject owner;

	public Transform() {

	}

	public Transform(double x, double y, int layer, double width, double height, double rot) {
		this.x = x;
		this.y = y;
		this.layer = layer;
		this.width = width;
		this.height = height;
		this.rot = rot;
	}

	public Transform copy() {
		return new Transform(x, y, layer, width, height, rot);
	}

	public Transform(GameObject owner) {
		this.owner = owner;
	}

	public void setAbsoluteX(double newX) {
		x = newX - (getAbsoluteX() - x);
	}

	public void setAbsoluteY(double newY) {
		y = newY - (getAbsoluteY() - y);
	}

	public double getAbsoluteX() {
		if (owner == null)
			return x;
		if (owner.parent == null)
			return x;
		return owner.parent.transform.getAbsoluteX() + x;
	}

	public double getAbsoluteY() {
		if (owner == null)
			return y;
		if (owner.parent == null)
			return y;
		return owner.parent.transform.getAbsoluteY() + y;
	}

}
