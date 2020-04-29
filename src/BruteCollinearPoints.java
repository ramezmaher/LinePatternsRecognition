import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] ans;
	private int total;
	public BruteCollinearPoints(Point[] points) {
		if (points == null || containsDuplicates(points))
			throw new IllegalArgumentException();
		total=0;
		int len = points.length;
		ans = new LineSegment[len];
		double comp;
		Arrays.sort(points);
		for (int i=0 ; i<len ; i++) 
			for(int j=i+1 ; j<len ;j++) {
				comp = points[i].slopeTo(points[j]);
				for(int k=j+1; k<len ;k++) {
					if (comp==points[i].slopeTo(points[k]))
					for(int l=k+1;l<len;l++) {
						if (comp==points[i].slopeTo(points[l])) {
							ans[total] = new LineSegment(minVal(points[i],points[j],points[k],points[l]),maxVal(points[i],points[j],points[k],points[l]));
							total++;
						}
					}
				}
			}
		LineSegment[] dummy = new LineSegment[total];
		for (int i=0 ; i<total ; i++) {
			dummy[i] = ans[i];
		}
		ans = dummy;
	}
	public int numberOfSegments() {
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
    private Point minVal(Point p,Point q,Point r,Point s) {
    	Point v = p ; 
    	if(v.compareTo(q) < 0)
    		v=q;
    	if(v.compareTo(r) < 0)
    		v=r;
    	if(v.compareTo(s) < 0)
    		v=s;
    	return v;
    }
    private Point maxVal(Point p,Point q,Point r,Point s) {
    	Point v = p ; 
    	if(v.compareTo(q) > 0)
    		v=q;
    	if(v.compareTo(r) > 0)
    		v=r;
    	if(v.compareTo(s) > 0)
    		v=s;
    	return v;
    }    
}
