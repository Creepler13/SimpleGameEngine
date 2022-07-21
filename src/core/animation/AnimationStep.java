package core.animation;

import core.Game;
import core.GameObject;

public abstract class AnimationStep {

	public double time;
	public double timePassed;

	public AnimationStep(double time) {
		this.time = time;

	}

	public boolean isDone(double deltaTime, GameObject obj, Game gameInstance) {
		update(deltaTime, obj, gameInstance);
		timePassed = timePassed + deltaTime;
		if (timePassed >= time) {
			clear();
			return true;
		} else
			return false;
	}

	public void clear() {
		timePassed = 0;
	}

	public abstract void update(double deltaTime, GameObject obj, Game gameInstance);

}
