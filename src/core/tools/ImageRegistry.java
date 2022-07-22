package core.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageRegistry {

	HashMap<String, BufferedImage> images = new HashMap<>();

	String imageFolderPath = "";

	public BufferedImage getImage(String imgID) {
		if (!images.containsKey(imgID))
			return getImage("------");
		return images.get(imgID);
	}

	public void loadImages(String imageFolderPath) {
		this.imageFolderPath = imageFolderPath;
		images.put("------", new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB));

		try {
			loadImageFolder(new File(imageFolderPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadImageFolder(File folder) throws IOException {
		System.out.println("Loading folder [ " + folder.getPath() + " ]");
		if (!folder.isDirectory())
			return;

		for (File file : folder.listFiles()) {
			if (file.isDirectory())
				loadImageFolder(file);
			else {
				BufferedImage i = ImageIO.read(file);
				String id = file.getPath().split("\\.")[0];
				id = id.substring(imageFolderPath.length() + 1);
				images.put(id, i);
				System.out.println("Loading image: [" + id + "] " + file.getPath());
			}

		}

	}

}
