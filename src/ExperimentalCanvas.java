import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;



class ExperimentalCanvas extends Canvas {
	
	protected List<Drawable> list;
	
	public ExperimentalCanvas() {
		// TODO Auto-generated constructor stub
		super();
		list = new Vector<Drawable>();
	}

	public void paint (Graphics g) {
        super.paint((java.awt.Graphics) g);
		Graphics2D g2;
        g2 = (Graphics2D) g;
        
        Iterator<Drawable> i = list.iterator();
        while(i.hasNext()){
        	Drawable temp = i.next();
        	temp.draw(g2);
        }
     }
	
	public void addDrowableItem(Drawable drawable){
		list.add(drawable);
	}
}
