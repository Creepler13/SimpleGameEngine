package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import core.events.KeyEventListerner;

public class EventManager {

	private Game gameInstace;

	public ArrayList<KeyEventListerner> keyListener = new ArrayList<>();

	public EventManager(Game gameInstace) {
		this.gameInstace = gameInstace;

		gameInstace.window.frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		gameInstace.window.frame.addKeyListener(new KeyListener() {

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

}
