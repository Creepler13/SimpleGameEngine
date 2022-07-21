package core.animation;

import core.Game;
import core.GameObject;
import core.tools.Transform;

public class TransformAnimetionStep extends AnimationStep {

	private Transform destTransform;
	private Transform deltaTransform;

	public TransformAnimetionStep(double time, Transform destTransform) {
		super(time);
		this.destTransform = destTransform;

	}

	@Override
	public void update(double deltaTime, GameObject obj, Game gameInstance) {
		if (deltaTransform == null)
			deltaTransform = new Transform(destTransform.x - obj.transform.x, destTransform.y - obj.transform.y, 0,
					destTransform.width - obj.transform.width, destTransform.height - obj.transform.height,
					destTransform.rot - obj.transform.rot);

		obj.transform.x = obj.transform.x + deltaTransform.x * deltaTime;
		obj.transform.y = obj.transform.y + deltaTransform.y * deltaTime;
		obj.transform.rot = obj.transform.rot + deltaTransform.rot * deltaTime;
		obj.transform.width = obj.transform.width + deltaTransform.width * deltaTime;
		obj.transform.height = obj.transform.height + deltaTransform.height * deltaTime;
	}

}
