package chan;

public class Point {
	double x;
	double y;
	Point()
	{
		
	}
	Point(Point p)
	{
		this.x=p.x;
		this.y=p.y;
	}
	double getX() {
		return x;
	}
	double getY() {
		return y;
	}
	void setX(double x) {
		this.x=x;
	}
	void setY(double y) {
		this.y=y;
	}
}
