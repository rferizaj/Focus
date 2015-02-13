import java.awt.Color;
import javax.swing.JFrame;


public class Application {

	private JFrame mainFrame;
	private final int DIMENSION = 800;
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
		System.out.println("Setting frame dimension to " + DIMENSION + ", " + DIMENSION);
		mainFrame.setSize(DIMENSION, DIMENSION);
		
		radius = 300;
		
		ExperimentalCanvas canvas = new ExperimentalCanvas(DIMENSION, false);
		canvas.setSize(DIMENSION, DIMENSION);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(canvas);
		
		canvas.createItems(radius, DIM_FOCUS, 30, 5, NUMBER_OF_ITEMS, 8, true, true);
		canvas.startAnimation();
	}
}
