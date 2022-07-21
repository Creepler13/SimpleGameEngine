package core.animation;

import java.util.ArrayList;

import core.Game;
import core.GameObject;

public class AnimationSession {

	private GameObject obj;
	private ArrayList<AnimationSessionEventListener> listeners = new ArrayList<>();
	private ArrayList<AnimationStep> steps;
	private int curStep = 0;
	private Boolean isPaused = false;

	public AnimationSession(GameObject obj, ArrayList<AnimationStep> steps) {
		this.obj = obj;
		this.steps = steps;
	}

	public AnimationSession(GameObject obj, ArrayList<AnimationStep> steps, AnimationSessionEventListener listener) {
		this.obj = obj;
		this.steps = steps;
		listeners.add(listener);
	}

	public void addEventListener(AnimationSessionEventListener listener) {
		listeners.add(listener);
	}

	public boolean update(double deltaTime, Game gameInstance) {
		if (this.curStep == steps.size()) {
			for (AnimationSessionEventListener animationSessionEventListener : listeners)
				animationSessionEventListener.hasEnded();
			return true;
		}

		if (isPaused)
			return false;

		AnimationStep curStep = getStep();

		if (curStep.isDone(deltaTime, obj, gameInstance)) {
			curStep.clear();
			for (AnimationSessionEventListener animationSessionEventListener : listeners)
				animationSessionEventListener.finishedStep(this.curStep);
			this.curStep++;
		}

		return false;

	}

	public AnimationStep getStep() {
		return steps.get(this.curStep);
	}

	public void stop() {
		getStep().clear();
		pause();
		this.curStep = steps.size();

	}

	public void pause() {
		isPaused = true;
		for (AnimationSessionEventListener animationSessionEventListener : listeners)
			animationSessionEventListener.paused();

	}

	public void resume() {
		isPaused = false;
		for (AnimationSessionEventListener animationSessionEventListener : listeners)
			animationSessionEventListener.resumed();
	}

}
