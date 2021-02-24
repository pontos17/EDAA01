package mountain;

public class Side {
	private Point p1;
	private Point p2;
	
	public Side (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	public Point getMid(double dev) {
		return new Point (((p1.getX()+p2.getX())/2),(((p1.getY()-p2.getY()) + (int)RandomUtilities.randFunc(dev) )/ 2));
	}
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Side) {
			Side s  = (Side) obj;
			return (this.p1.equals(s.p1) && this.p2.equals(s.p2)) || (this.p1.equals(s.p2) && this.p2.equals(s.p1)) ;
		} else {
			return false;
		}
	}
}

