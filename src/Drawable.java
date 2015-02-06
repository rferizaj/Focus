import java.awt.Graphics2D;


abstract class Drawable {
	private int xPosition, yPosition, dimension;
	
	public abstract void draw(Graphics2D g2);
}
