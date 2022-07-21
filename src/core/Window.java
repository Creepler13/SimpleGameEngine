package core;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import core.tools.JBackgroundPanel;

public class Window {

	public JFrame frame = new JFrame();
	public JBackgroundPanel panel = new JBackgroundPanel();

	public Window() {
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		frame.setContentPane(panel);
		frame.setVisible(true);
	}

	public void setPanelBC(BufferedImage i) {
		panel.setBackground(i);
	}
}
