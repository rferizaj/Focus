import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

class FocusItem extends Drawable implements Runnable {
	private boolean isVisible;
	
	public FocusItem(int xPosition, int yPosition, int dimension, Color color, ExperimentalCanvas experimentalCanvas){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.dimension = dimension;
		this.experimentalCanvas = experimentalCanvas;
		this.color = color;
		isVisible = true;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		if(isVisible){
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
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			isVisible = !isVisible;
			experimentalCanvas.repaint();
			try {
				//sleep for the time
				//TODO: define time to sleep
				Thread.sleep(getRandom(300l, 1200l));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
