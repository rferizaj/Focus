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
	
	private short NumberOfLoops;
	protected List<Drawable> list;
	private Timer randomTimer;
	private Timer constantTimer;
	private short loopNumber;
	private static double positionOfSpinning;
	
	public ExperimentalCanvas() {
		// TODO Auto-generated constructor stub
		super();
		list = new Vector<Drawable>();
		randomTimer = new Timer(1000, this);
		randomTimer.setRepeats(false);
		constantTimer = new Timer(10, this);
		constantTimer.setRepeats(true);
		loopNumber = 0;
		NumberOfLoops = 10;
		positionOfSpinning = 0;
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
		constantTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == constantTimer){
			spinItems();
		}
		else
			if(loopNumber < NumberOfLoops*2){
				System.out.println("Animation started");
				loopNumber++;
				//isVisible = !isVisible;
				if(loopNumber == NumberOfLoops){
					int element = (int)getRandom(1, list.size()-1);
					//this is the element to change
					//change the element
					System.out.println("Element changed");
					list.get(element).setDimension(60);
					((FocusItem) list.get(0)).changeVisible();
				}
				else {
					((FocusItem) list.get(0)).changeVisible();
				}
				//code to do
				repaint();
				randomTimer.setInitialDelay((int)(getRandom(150, 400)));
				randomTimer.restart();
			}
			else {
				System.out.println("end of the loop");
				clearCanvas();
			}
	}

	protected long getRandom(long min, long max){
		Random r = new Random();
		return min + (Math.abs(r.nextLong()) % max);
	}
	
	protected void clearCanvas() {
		list.clear();
		repaint();
	}
	
	protected void spinItems() {
		for(int i = 1; i < list.size(); i++){
			//to put them around the circle: sin(360 * index/count) * radius
			int number = list.size() -1;
			positionOfSpinning +=0.02;
			double tempValue = (360) / number * i;
			int radius = 300;
			int dim = 800;
			//converting from degree to radiant
			tempValue = tempValue * 2 * Math.PI / 360;
			tempValue += positionOfSpinning * 2 * Math.PI / 360;
			int tempY = dim/2 + (int)(Math.cos(tempValue) * radius);
			int tempX = dim/2 + (int)(Math.sin(tempValue) * radius);
			list.get(i).xPosition = tempX;
			list.get(i).yPosition = tempY;
		}
		repaint();
	}
}
