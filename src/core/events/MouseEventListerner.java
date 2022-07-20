package core.events;

import java.awt.event.MouseEvent;

public abstract class MouseEventListerner extends IEventListener {

	public abstract void mouseReleased(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseExited(MouseEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mouseClicked(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);
}
