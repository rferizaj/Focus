import java.awt.Color;
import javax.swing.JFrame;


public class Application {

	private JFrame mainFrame;
	private final int DIMX = 800;
	private final int DIMY = 800;
	private final int DIM_FOCUS = 100;
	private final int NUMBER_OF_ITEMS = 12;
	private final int RADIUS_ITEM_BASIC_DIMENSION = 50;
	private int radius, hasToChange;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application application = new Application();
	}

	public Application() {
		super();
		System.out.println("Initializating frame");
		mainFrame = new JFrame("Here the title - set as constant");
		System.out.println("Setting frame dimension to " + DIMX + ", " + DIMY);
		mainFrame.setSize(DIMX, DIMY);
		ExperimentalCanvas canvas = new ExperimentalCanvas();
		canvas.setSize(DIMX, DIMY);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(canvas);
		
		canvas.addDrowableItem(new FocusItem(DIMX/2 - DIM_FOCUS/2, DIMY/2 - DIM_FOCUS/2, DIM_FOCUS, Color.PINK));
		System.out.println("Created focus item");
		
		//the reading of the file must set this variables
		//radius (distance between the focus and the items)
		//has to change (if there will be changes or no)
		//color or shape
		//...
		radius = 300;
		
		for(int i = 0; i < NUMBER_OF_ITEMS; i ++){
			//to put them around the circle: sin(360 * index/count) * radius
			double tempValue = 360 / NUMBER_OF_ITEMS * i;
			//converting from degree to radiant
			tempValue = tempValue * 2 * Math.PI / 360;
			int tempY = DIMY/2 + (int)(Math.cos(tempValue) * radius);
			int tempX = DIMX/2 + (int)(Math.sin(tempValue) * radius);
			//TODO: change the color or shape? read the file
			canvas.addDrowableItem(new RadusItem(tempX, tempY, RADIUS_ITEM_BASIC_DIMENSION, Color.CYAN));
			System.out.println("Created radius item " + i);
		}	
		
		canvas.startAnimation();
	}
}
