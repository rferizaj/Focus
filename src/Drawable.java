import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


abstract class Drawable {
	protected int xPosition, yPosition, dimension;
	protected Color color;
	
	public abstract void draw(Graphics2D g2);
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public int getDimension() {
		return dimension;
	}

	public Color getColor() {
		return color;
	}
}
