package core;

public class ImageObject extends GameObject {

	private String imgID = "";

	public String getImg() {
		return imgID;
	}

	public void setImg(String imgID, Boolean fitSizeToImg) {
		this.imgID = imgID;
		if (!fitSizeToImg)
			return;
		transform.width = gameInstance.renderer.imageRegistry.getImage(imgID).getWidth();
		transform.height = gameInstance.renderer.imageRegistry.getImage(imgID).getHeight();

	}

	public ImageObject(Game g) {
		super(g);
	}

	public ImageObject(Game g, int x, int y) {
		super(g, x, y);
	}

}
