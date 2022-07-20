package core;

import java.awt.Color;
import java.awt.Font;

public class TextObject extends GameObject {

	public TextObject(Game gameInstance) {
		super(gameInstance);
	}

	public TextObject(Game gameInstance, int x, int y) {
		super(gameInstance, x, y);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	private Color textColor = Color.RED;
	private String text = "";
	private Font font = Font.decode(Font.DIALOG);

}
