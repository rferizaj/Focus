import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


abstract class Drawable {
	protected int xPosition, yPosition, dimension;
	protected Color color;
	protected ExperimentalCanvas experimentalCanvas;
	
	public abstract void draw(Graphics2D g2);
	
	protected long getRandom(long min, long max){
		Random r = new Random();
		return min + (Math.abs(r.nextLong()) % max);
	}
}
