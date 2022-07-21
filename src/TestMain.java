import java.awt.event.MouseEvent;

import core.Game;
import core.ImageObject;
import core.TextObject;
import core.animation.Animation;
import core.animation.TransformAnimetionStep;
import core.events.MouseEventListerner;
import core.tools.Transform;

public class TestMain {

	public static void main(String[] args) {
		Game g = new Game(200, 300);
		ImageObject test = new ImageObject(g, 50, 50);

		TextObject to = new TextObject(g, 30, 30);
		to.setText("Test");

		test.setImg("5sc5epxh9l161", true);
		g.scene.add(test);
		ImageObject test2 = new ImageObject(g, 100, 100);
		test2.setImg("821f37d5e6d4afe67ffdf20ac579dc877ee6057992968955aebf766deebff7a1_1", true);

		Animation anim = new Animation(g);
		Transform temp = to.transform.copy();
		temp.x += 50;
		anim.addStep(new TransformAnimetionStep(2, temp));

		g.eventManager.addEventListener(test2, new MouseEventListerner() {

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println(anim.play(to));
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				test2.setImg("821f37d5e6d4afe67ffdf20ac579dc877ee6057992968955aebf766deebff7a1_1", true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				test2.setImg("Overwolf\\no hon\\14", true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}

		});

		g.scene.add(test2);
		g.scene.add(to);

		g.StartGame();

	}

}
