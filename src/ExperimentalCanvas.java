import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.Timer;

class ExperimentalCanvas extends Canvas implements ActionListener {
	
	final static short NUMBERS_OF_LOOP = 15;
	protected List<Drawable> list;
	private Timer randomTimer;
	private short loopNumber;
	
	public ExperimentalCanvas() {
		// TODO Auto-generated constructor stub
		super();
		list = new Vector<Drawable>();
		randomTimer = new Timer(1000, this);
		randomTimer.setRepeats(false);
		loopNumber = 0;
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
	
	public void startAnimation(){
		randomTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(loopNumber < NUMBERS_OF_LOOP){
			System.out.println("Animation started");
			loopNumber++;
			//isVisible = !isVisible;
			if(loopNumber == NUMBERS_OF_LOOP / 2){
				int element = (int)getRandom(0, list.size()-1);
				//this is the element to change
				while(list.get(element).getClass() == FocusItem.class)
					element = (int)getRandom(0, list.size()-1);
				
			}
			else {
				Iterator<Drawable> i = list.iterator();
				while(i.hasNext()){
					Drawable temp = i.next();
					if (temp.getClass() == (FocusItem.class)){
						((FocusItem)temp).changeVisible();
					}
				}
			}
			//code to do
			repaint();
			randomTimer.setInitialDelay((int)(getRandom(300, 900)));
			randomTimer.restart();
		}
		else {
			System.out.println("end of the loop");
		}
		
	}

	protected long getRandom(long min, long max){
		Random r = new Random();
		return min + (Math.abs(r.nextLong()) % max);
	}
	
}
