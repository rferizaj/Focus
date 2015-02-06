import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;



class ExperimentalCanvas extends Canvas {

	public ExperimentalCanvas() {
		// TODO Auto-generated constructor stub
		super();
	}

	
	public void paint (Graphics g) {
        super.paint((java.awt.Graphics) g);
		Graphics2D g2;
        g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.drawOval(10, 10, 30, 30);
     }
}
