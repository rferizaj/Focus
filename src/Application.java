import java.awt.Frame;

import javax.swing.JFrame;


public class Application {

	private JFrame mainFrame;
	private final int DIMX = 400;
	private final int DIMY = 400;
	
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
	}

}
