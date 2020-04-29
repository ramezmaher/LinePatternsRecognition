import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;


public class Point implements Comparable<Point> {
	
	private class OrderOfSlope implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			double slope1 = slopeTo(p1);
			double slope2 = slopeTo(p2);
			
			if (slope1<slope2) return -1;
			if (slope1>slope2) return 1;
			return 0;
			
		}
	}

	private final int x;
	private final int y;
	
	public Point(int X,int Y) {
		this.x=X;
		this.y=Y;
	}
	
	public void draw() {
		StdDraw.point(x, y);
	}
	
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
	@Override
	public int compareTo(Point that) {
		if (this.y == that.y)
			if (this.x == that.x)
				return 0;
			else if (this.x < that.x)
				return -1;
			else return 1;
		
		else if (this.y < that.y)
			return -1;
		
		else return 1;
	}
	
	public double slopeTo(Point that) {
		int deltaX = that.x-this.x;
		int deltaY = that.y-this.y;
		if (deltaX==0 && deltaY==0)
			return Double.NEGATIVE_INFINITY;
		else if (deltaX == 0)
			return Double.POSITIVE_INFINITY;
		else if (deltaY == 0)
			return Double.MIN_VALUE;
		else return ((1.0*deltaY)/(1.0*deltaX));
	}
	
	public Comparator<Point> slopeOrder(){
		return new OrderOfSlope();
	}
	
}
