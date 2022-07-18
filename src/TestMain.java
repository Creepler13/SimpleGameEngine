import core.Game;
import core.GameObject;
import core.events.KeyEventListerner;

public class TestMain {

	public static void main(String[] args) {
		Game g = new Game(200, 300);
		GameObject test = g.createGameObject(50, 50);
		test.setImg("5sc5epxh9l161", true);
		g.renderer.renderObject(test);
		GameObject test2 = g.createGameObject(100, 100);
		test2.setImg("821f37d5e6d4afe67ffdf20ac579dc877ee6057992968955aebf766deebff7a1_1", true);

		test2.addEventListener(new KeyEventListerner() {
			
			@Override
			public void keyTyped(String character, int keyCode) {
			}
			
			@Override
			public void keyReleased(String character, int keyCode) {
				test2.setImg("interlude_partner", true);
			}
			
			@Override
			public void keyPressed(String character, int keyCode) {
			}
		});
		
		g.renderer.renderObject(test2);

	}

}
