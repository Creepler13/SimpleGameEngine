package core.events;

public abstract class KeyEventListerner extends IEvent{

	public abstract void keyReleased(String character,int keyCode);
	public abstract void keyTyped(String character,int keyCode);
	public abstract void keyPressed(String character,int keyCode);
	
}
