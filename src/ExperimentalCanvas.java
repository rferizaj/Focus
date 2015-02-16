import java.awt.Canvas;
import java.awt.Color;
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
	
	private int NumberOfLoops;
	protected List<Drawable> list;
	private Timer randomTimer;
	private Timer constantTimer;
	private short loopNumber;
	private double positionOfSpinning;
	private boolean spin;
	private double radius;
	private double dimension;
	private boolean bigChange;
	private boolean changeSize;
	private boolean isChanging;
	
	/**
	 * create the canvas
	 * @param radius
	 * @param spin true if the items will spin
	 */
	public ExperimentalCanvas(double dimension, boolean spin) {
		// TODO Auto-generated constructor stub
		super();
		list = new Vector<Drawable>();
		randomTimer = new Timer(1000, this);
		randomTimer.setRepeats(false);
		loopNumber = 0;
		NumberOfLoops = 6;
		
		this.spin = spin;
		this.dimension = dimension;
		
		if(spin){
			constantTimer = new Timer(10, this);
			constantTimer.setRepeats(true);
			positionOfSpinning = 0;
		}
		
		setBackground(Color.WHITE);
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
		System.out.println("Loop started");
		//set initial time?
		randomTimer.start();
		if(spin)
			constantTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == constantTimer){
			spinItems();
		}
		else
			if(loopNumber < NumberOfLoops*2){
				loopNumber++;
				//isVisible = !isVisible;
				if(loopNumber == NumberOfLoops  && isChanging){
					int element = (int)getRandom(1, list.size()-1);
					//this is the element to change
					//change the element
					if(changeSize){
						//inside the changing size
						if(bigChange){
							list.get(element).setDimension(list.get(element).getDimension() + list.get(element).getDimension());
						}
						else {
							list.get(element).setDimension(list.get(element).getDimension() + list.get(element).getDimension() / 2 );
						}
					}
					else {
						//inside the change of color
						//TODO: question? going darker or brighter?
						int r = list.get(element).getColor().getRed(), g = list.get(element).getColor().getGreen(), b = list.get(element).getColor().getBlue();
						if(bigChange){
							//brighter
							int change = 100;
							r = checkColorsize((int)getRandom(r, r + change));
							g = checkColorsize((int)getRandom(g, g + change));
							b = checkColorsize((int)getRandom(b, b + change));
							list.get(element).setColor(checkColorsize(new Color(r, g, b)));
//							//darker
//							list.get(element).setColor(currentColor);
						}
						else {
							//brighter
//							int change = 50;
//							r = (int)getRandom(r, checkColorsize(r + change));
//							g = (int)getRandom(g, checkColorsize(g + change));
//							b = (int)getRandom(b, checkColorsize(b + change));
//							Color currentColor = new Color(r, g, b);
//							list.get(element).setColor(currentColor);
//							//darker
							int change = 50;
							r = checkColorsize((int)getRandom(r - change, r));
							g = checkColorsize((int)getRandom(g - change, g));
							b = checkColorsize((int)getRandom(b - change, b));
							list.get(element).setColor(checkColorsize(new Color(r, g, b)));
						}
					}
					
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
				System.out.println("End of the loop");
				stopTasks();
			}
	}

	private void stopTasks() {
		clearCanvas();
		randomTimer.stop();
		if(spin)
			constantTimer.stop();
	}

	private int checkColorsize(int number){
		if (number > 254) {
			return 255;
		}
		if (number < 0) {
			return 1;
		}
		return number;
	}
	
	private Color checkColorsize(Color color){
		int r = color.getRed(), g = color.getGreen(), b = color.getGreen();
		
		//check if the color is too bright
		if( r + g + b > 720)
		{
			r -= 16;
			g -= 16;
			b -= 16;
		}
		//check if it is too dark
		if (r + g + b < 48){
			r += 16;
			g += 16;
			b += 16;
		}
		return new Color(r, g, b);
	}
	
	protected long getRandom(long min, long max){
		Random r = new Random();
		return min + (Math.abs(r.nextLong()) % max);
	}
	
	protected void clearCanvas() {
		list.clear();
		System.gc();
		repaint();
	}
	
	protected void spinItems() {
		for(int i = 1; i < list.size(); i++){
			//to put them around the circle: sin(360 * index/count) * radius
			int number = list.size() -1;
			positionOfSpinning +=0.02;
			double tempValue = (360) / number * i;
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
	
	/**
	 * This method is the central method that create and dispose all the items
	 * and also define what the experiment is about by defining if there will
	 * be a small or big change and if the color or the shape will change
	 * 
	 * @param radius distance between the central object and the peripheral objects
	 * @param dimensionOfTheFocus dimension of the object in the focus
	 * @param dimensionOfTheRadius dimension of the object in the radius
	 * @param deltaChange delta in between the radial object can change
	 * @param numberOfRadiusItems number of item in the radius
	 * @param numberOfBlinks number of times the central object will blink
	 * @param bigChange true if there will be a big change, false if the change will be small
	 * @param changeSize true if the size will change, false if the color
	 * @param isChanging true if there will be changes
	 */
	public void createItems(int radius, int dimensionOfTheFocus, int dimensionOfTheRadius, int deltaChange, int numberOfRadiusItems, int numberOfBlinks, boolean bigChange, boolean changeSize, boolean isChanging){
		
		this.radius = radius;
		this.bigChange = bigChange;
		this.changeSize = changeSize;
		this.NumberOfLoops = numberOfBlinks;
		this.isChanging = isChanging;
		
		addDrowableItem(new FocusItem((int)dimension/2 - dimensionOfTheFocus/2, (int)dimension/2 - dimensionOfTheFocus/2, dimensionOfTheFocus, Color.PINK));
		System.out.println("Created focus item");
		//the reading of the file must set this variables
		//radius (distance between the focus and the items)
		//has to change (if there will be changes or no)
		//color or shape
		//...
		
		for(int i = 0; i < numberOfRadiusItems; i ++){
			//to put them around the circle: sin(360 * index/count) * radius
			double tempValue = 360 / numberOfRadiusItems * i;
			//converting from degree to radiant
			tempValue = tempValue * 2 * Math.PI / 360;
			int tempY = (int)(dimension/2 + (int)(Math.cos(tempValue) * radius));
			int tempX = (int)(dimension/2 + (int)(Math.sin(tempValue) * radius));
			
			//TODO: change the color or shape? read the file
			//do we change always the color and size or not?
			Color c = new Color((int)getRandom(0, 255), (int)getRandom(0, 255), (int)getRandom(0, 255));
			int dimensionOfTheRadiusShape = (int)getRandom(dimensionOfTheRadius - deltaChange, dimensionOfTheRadius + deltaChange);
			addDrowableItem(new RadusItem(tempX, tempY, dimensionOfTheRadiusShape, c));
			System.out.println("Created radius item " + i);
		}	
	}
}
