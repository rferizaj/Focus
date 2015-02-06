import java.awt.Color;
import java.awt.Graphics2D;


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
		// TODO Auto-generated method stub
		g2.setColor(color);
        g2.drawOval(xPosition, yPosition, dimension, dimension);
	}

}
