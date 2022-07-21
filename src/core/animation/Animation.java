package core.animation;

import java.util.ArrayList;

import core.Game;
import core.GameObject;

public class Animation {

	private ArrayList<AnimationStep> steps = new ArrayList<>();
	private Game gameInstace;

	public Animation(Game gameInstace) {
		this.gameInstace = gameInstace;
	}

	public void addStep(AnimationStep step) {
		steps.add(step);
	}

	public AnimationSession play(GameObject obj) {
		AnimationSession session=new AnimationSession(obj, steps);
		gameInstace.renderer.animationSessions.add(session);
		return session;
	}
}
