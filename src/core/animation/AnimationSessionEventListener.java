package core.animation;

public interface AnimationSessionEventListener {
	public void hasEnded();

	public void finishedStep(int step);

	public void paused();

	public void resumed();
}
