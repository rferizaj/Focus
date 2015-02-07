import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;


class FocusItem extends Drawable {
	
	public FocusItem(){
		xPosition = 10;
		yPosition = 10;
		dimension = 30;
		color = Color.YELLOW;
	}
	
	public FocusItem(int xPosition, int yPosition, int dimension, Color color){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.dimension = dimension;
		this.color = color;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.setPaint(color);
        Path2D star = new Path2D.Float (); 
        star.moveTo (xPosition + dimension/5F, yPosition + dimension-1); 
        star.lineTo (xPosition + dimension/2F, yPosition); 
        star.lineTo (xPosition + 4*dimension/5F, yPosition + dimension-1); 
        star.lineTo (xPosition, yPosition + 2*dimension/5F); 
        star.lineTo (xPosition + dimension-1, yPosition + 2*dimension/5F); 
        star.closePath (); 
        g2.draw (star);
        g2.fill (star);
	}

}
