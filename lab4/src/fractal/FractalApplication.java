package fractal;

import koch.Koch;
import mountain.*;

public class FractalApplication {
	public static void main(String[] args) {
		Point p1 = new Point(100,400);
		Point p2 = new Point(200,140);
		Point p3 = new Point(400,400);
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Mountain(p1, p2, p3, 30.0);
		fractals[1] = new Koch(300);
		new FractalView(fractals, "Fraktaler", 600, 600);
	    
	}

}
