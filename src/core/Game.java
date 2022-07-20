package core;

public class Game {

	public GameRenderer renderer;
	public EventManager eventManager;
	public Window window = new Window();
	private Game instace = this;

	public GameObject scene = new GameObject(this);

	public Game(int width, int height) {
		
		
		scene.transform.width = width;
		scene.transform.height = height;
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
					renderer.update(instace);
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
}
