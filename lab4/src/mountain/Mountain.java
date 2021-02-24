package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private double rand;
	private Map <Side, Point> ts;
	
	
	public Mountain (Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		ts = new HashMap<Side,Point>();
	}
	
	@Override
	public String getTitle() {
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
//		g.moveTo(a.getX(), a.getY());
		triangleLine(g, order, a, b, c, dev);
	}
	private void triangleLine(TurtleGraphics g, int order, Point a, Point b, Point c, double deva) {
		if (order == 0) {
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
			g.forwardTo(c.getX(), c.getY());
			g.forwardTo(a.getX(), a.getY());
		} else {
			
			Point d = newPoint (a,b,deva);
			Point e = newPoint (b,c,deva);
			Point f = newPoint (c,a,deva);
			
			triangleLine(g, order-1, a, d, f, deva/2);
			triangleLine(g, order-1, b, d, e, deva/2);
			triangleLine(g, order-1, e, c, f, deva/2);
			triangleLine(g, order-1, d, e, f, deva/2);
		}
	}
	private Point newPoint(Point a, Point b, double devb) {
		rand = RandomUtilities.randFunc(devb);
		Side newSide = new Side(a,b);
		if(ts.containsKey(newSide)) {
			Point pt = ts.get(newSide);
			ts.remove(newSide);
			return pt;
		}
		else {
			int x =(a.getX()+b.getX()) / 2;
			int y =  (int) (((a.getY() + b.getY()) / 2) + rand);
			Point newPoint = new Point(x,y);
			ts.put(newSide, newPoint);
			
			return (newPoint);
		}
	}
}
