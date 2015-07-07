package chan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chan.Helper;

public class JarvisMarch {
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		/* Give the absolute path if not running from eclipse and change the FileReader line */
		String fileName = "/src/chan/data.txt";
		int noOfPoints = 0;
		int line = 1;
		String temp = null;
		String[] splited = new String[2];
		List<Point> points = new ArrayList<Point>();
		List<Point> result = new ArrayList<Point>();
		Point p = new Point(); 
		
		try {

            FileReader fileReader = new FileReader(System.getProperty("user.dir")+fileName);
//			FileReader fileReader = new FileReader(fileName);            
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((temp = bufferedReader.readLine()) != null) {
            	if(line == 1)
            		noOfPoints = Integer.parseInt(temp);
            	else
            	{
            		 splited = temp.split("\\s+");
            		 p.setX(Double.parseDouble(splited[0]));
            		 p.setY(Double.parseDouble(splited[1]));
            		 points.add(new Point(p));
            	}
            	if(line > noOfPoints) {
            		break;
            	}
            	line++;
            }    
            bufferedReader.close();            
            result=convexHull(points);
   			Helper.printPoints(result);
    		long endTime = System.nanoTime();
    		System.out.println("Time taken : "+(endTime - startTime)/1000 + " micro seconds");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Please check the location of File '"+fileName + "'. It is not there where you are expecting it to be!");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ fileName + "'");                   
        }
	}
		
	public static List<Point> convexHull(List<Point> points) {
		List<Point> result = new ArrayList<Point>();
		Point extreme = Helper.findExtreme(points);
		result.add(new Point(extreme));
		Point p = new Point(extreme);
		Point q = new Point(extreme);
		
		while(true) {
			Point r = new Point();
	        for(int i=0; i<points.size();i++) {
	        	if((points.get(i).getX() == p.getX()) && (points.get(i).getY() == p.getY())) {
	        		continue;
	        	}
	        	r.setX(points.get(i).getX());
	        	r.setY(points.get(i).getY());
	        	int turn = Helper.orientation(p,q,r);
	        	double dist = Helper.compare(Helper.dist(p,r),Helper.dist(p,q));
	        	if(turn == -1 || turn == 0 && dist == 1) {
	        		q.setX(r.getX());
	        		q.setY(r.getY());
	        	}
	        }
			if((q.getX() == result.get(0).getX()) && (q.getY() == result.get(0).getY())) {
				break;
			}
			result.add(new Point(q));
			p.setX(q.getX());
			p.setY(q.getY());
		}
        return result;
	}	
}