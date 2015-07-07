package chan;

import java.util.List;

public class Helper {
	static int compare(double a, double b)
	{
		if(a>b) 
			return 1;
		else if(a<b)
			return -1;
		return 0;
	}
	
	static double dist(Point p, Point q) {
		double dx = q.getX()-p.getX();
		double dy = q.getY()-p.getY();
		return ((dx*dx) + (dy*dy));
	}
	
	static int orientation(Point p, Point q, Point r)
	{
		return compare(((q.getX()-p.getX())*(r.getY()-p.getY())) - ((q.getY()-p.getY())*(r.getX()-p.getX())),0);
	}

	static Point findExtreme(List<Point> points) {
		Point p = new Point(points.get(0));
		for(int i=1;i<points.size();i++) {
			double x = points.get(i).getX();
			double y = points.get(i).getY();
			if(x < p.getX() || (x == p.getX() && y<p.getY())) {
				p.setX(x);
				p.setY(y);
			}
		}
		return p;
	}
	
	static void printPoint(Point p) {
		System.out.println("point x : "+p.getX());
		System.out.println("point y : "+p.getY());
	}
	
	static void printPoints(List<Point> points) {
		System.out.println("Total Points in the Convex Hull are "+points.size());
		for(int i=0;i<points.size();i++) {
			System.out.println("("+points.get(i).getX()+","+points.get(i).getY()+")");
		}
	}

}
