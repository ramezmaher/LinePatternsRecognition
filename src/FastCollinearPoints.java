import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
	LineSegment[] ans;
	int total;
	public FastCollinearPoints(Point[] points) {
		if (points==null || containsDuplicates(points))
			throw new IllegalArgumentException();
		Arrays.sort(points);
		int len = points.length;
		ArrayList<LineSegment> s = new ArrayList<LineSegment>();
		ArrayList<String> st = new ArrayList<String>();
		for (int i=0 ; i<len ; i++) {
		   Point[] p = points.clone();
		   Arrays.sort(p,points[i].slopeOrder());
		   for (int j=1 ; j<len-2 ;) {
			   if (p[0].slopeTo(p[j]) == p[0].slopeTo(p[j+1]) && p[0].slopeTo(p[j]) == p[0].slopeTo(p[j+2])) {
				   int count = 4;
				   int h = j+3;
				   while(h<len && p[0].slopeTo(p[j]) == p[0].slopeTo(p[h])) {
					   count++;
					   h++;
				   }
				   Point[] line = new Point[count];
				   line[0] = p[0];
				   for (int y=1 ; y<count ; y++ ) {
					   line[y] = p[j++];
				   }
				   LineSegment l = makeLine(line);
				   if (s.isEmpty()) {
					   s.add(l);
					   st.add(l.toString());
				   }
				   else {
					   if (!st.contains(l.toString())) {
						   s.add(l);
						   st.add(l.toString());
					   }
				   }
			   }
			   else j++;
		   }
		}
		total = s.size();
		ans = new LineSegment[s.size()];
		for (int i=0 ; i<s.size();i++) {
			ans[i]=s.get(i);
		}
	    }   
	public int numberOfSegments()   {
		   return total;
	   }  
    public LineSegment[] segments() {
		   return ans;
	   }
    private boolean containsDuplicates(Point[] point) {
    	int len = point.length;
    	for (int i=0 ; i<len ; i++) {
    		if (point[i]==null)
    			return true;
    		for (int j=i+1 ; j<len ;j++)
    			if(point[j]==null || point[i].compareTo(point[j])==0) {
    				System.out.println(point[i].toString()+" "+point[j].toString());
    				return true;
    			}
    	}
    	return false;
    }

    private LineSegment makeLine(Point[] a) {

    	Point[] p = a;
    	Arrays.sort(p);
    	return new LineSegment(p[0],p[p.length-1]);
    }
    public static void main (String[] args) {
    	Point[] a = new Point[12];
    	a[0] = new Point(1,1);
    	a[1] = new Point(1,2);
    	a[2] = new Point(1,3);
    	a[3] = new Point(1,4);
    	a[4] = new Point(3,4);
    	a[5] = new Point(4,4);
    	a[6] = new Point(4,2);
    	a[7] = new Point(4,1);
    	a[8] = new Point(2,2);
    	a[9] = new Point(3,3);
    	a[10] = new Point(2,3);
    	a[11] = new Point(3,2);
    	FastCollinearPoints b = new FastCollinearPoints(a);
    	LineSegment[] s = b.segments();
    	for (LineSegment l : s)
    		System.out.println(l.toString());
    	System.out.println(b.numberOfSegments());
    }
}
