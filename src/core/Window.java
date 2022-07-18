package core;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import core.tools.JBackgroundPanel;

public class Window {

	public JFrame frame = new JFrame();
	public JBackgroundPanel panel = new JBackgroundPanel();

	public Window() {
		frame.setContentPane(panel);
		frame.setVisible(true);
	}

	public void setPanelBC(BufferedImage i) {
		panel.setBackground(i);
	}
}
