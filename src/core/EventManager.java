package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import core.events.IEventListener;
import core.events.KeyEventListerner;
import core.events.MouseEventListerner;
import core.tools.GameUtils;

public class EventManager {

	private Game gameInstace;

	ArrayList<KeyEventListerner> keyListener = new ArrayList<>();
	ArrayList<MouseEventListerner> mouseListener = new ArrayList<>();

	int lastX = 0, lastY = 0;

	public EventManager(Game gameInstace) {
		this.gameInstace = gameInstace;

		gameInstace.window.panel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());
				for (MouseEventListerner MListerner : mouseListener) {
					MListerner.isPressed = false;
					if (MListerner.parent == isON)
						MListerner.mouseReleased(e);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());
				for (MouseEventListerner MListerner : mouseListener) {
					if (MListerner.parent == isON) {
						MListerner.mousePressed(e);
						MListerner.isPressed = true;
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());
				for (MouseEventListerner MListerner : mouseListener) {
					if (MListerner.parent == isON)
						MListerner.mouseClicked(e);
				}
			}

			Boolean hasExited = false;

			@Override
			public void mouseEntered(MouseEvent e) {
				GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());

				for (MouseEventListerner list : mouseListener)
					if (isON == list.parent)
						if (hasExited) {
							list.mouseEntered(e);
							hasExited = false;
						}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GameObject wasON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, lastX, lastY);
				for (MouseEventListerner list : mouseListener)
					if (wasON == list.parent) {
						list.mouseExited(e);
						hasExited = true;
					}
			}
		});

		gameInstace.window.panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {

				if (mouseListener.size() != 0) {
					GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());
					GameObject wasON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, lastX, lastY);

					for (MouseEventListerner list : mouseListener) {
						if (wasON != isON)
							if (list.parent == isON)
								list.mouseEntered(e);
							else if (list.parent == wasON)
								list.mouseExited(e);

						if (list.parent == isON)
							list.mouseMoved(e);
					}
				}

				lastX = e.getX();
				lastY = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				GameObject isON = GameUtils.getGameObjectAtPositionOnTop(gameInstace, e.getX(), e.getY());
				for (MouseEventListerner list : mouseListener) {
					if (list.parent == isON || list.isPressed)
						list.mouseDragged(e);
				}

			}
		});

		gameInstace.window.panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				for (KeyEventListerner keyEventListerner : keyListener) {
					if (keyEventListerner.parent.isRendered) {
						keyEventListerner.keyTyped(e.getKeyChar() + "", e.getKeyCode());
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				for (KeyEventListerner keyEventListerner : keyListener) {
					if (keyEventListerner.parent.isRendered) {
						keyEventListerner.keyReleased(e.getKeyChar() + "", e.getKeyCode());
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				for (KeyEventListerner keyEventListerner : keyListener) {
					if (keyEventListerner.parent.isRendered) {
						keyEventListerner.keyPressed(e.getKeyChar() + "", e.getKeyCode());
					}
				}
			}
		});

	}

	public void addEventListener(GameObject obj, IEventListener eventListener) {
		eventListener.parent = obj;

		if (eventListener instanceof KeyEventListerner)
			gameInstace.eventManager.keyListener.add((KeyEventListerner) eventListener);

		if (eventListener instanceof MouseEventListerner)
			gameInstace.eventManager.mouseListener.add((MouseEventListerner) eventListener);
	}

}
