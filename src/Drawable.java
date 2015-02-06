import java.awt.Color;
import java.awt.Graphics2D;


abstract class Drawable {
	protected int xPosition, yPosition, dimension;
	protected Color color;
	
	public abstract void draw(Graphics2D g2);
}
