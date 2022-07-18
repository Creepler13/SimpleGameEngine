package core;

public class Game {

	public GameRenderer renderer;
	public EventManager eventManager;
	public Window window = new Window();

	public Game(int width, int height) {
		window.frame.setBounds(0, 0, width, height);
		eventManager = new EventManager(this);
		renderer = new GameRenderer(window);
		renderer.imageRegistry.loadImages("src/images");
		StartGame();
	}

	public Thread gameLoop, renderLoop;

	public void StartGame() {
		gameLoop = new Thread(new Runnable() {

			@Override
			public void run() {
			}
		});

		renderLoop = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					long millis = System.currentTimeMillis();
					renderer.update();
					try {
						Thread.sleep(16 - millis % 16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});

		gameLoop.start();
		renderLoop.start();

	}

	public GameObject createGameObject() {
		return new GameObject(this);
	}

	public GameObject createGameObject(String name) {
		return new GameObject(this, name);
	}

	public GameObject createGameObject(int x, int y) {
		return new GameObject(this, x, y);
	}

	public GameObject createGameObject(int x, int y, String name) {
		return new GameObject(this, x, y, name);
	}
}
