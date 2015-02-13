import java.awt.Color;
import java.awt.Graphics2D;

class RadusItem extends Drawable {

	public RadusItem(int xPosition, int yPosition, int dimension, Color color){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.dimension = dimension;
		this.color = color;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.setPaint(color);
		g2.setColor(color);
        g2.fillOval(xPosition - dimension/2, yPosition - dimension/2, dimension, dimension);
	}
	
}
