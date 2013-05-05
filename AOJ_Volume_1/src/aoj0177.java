import java.util.*;
public class aoj0177 {
	static final Scanner stdin = new Scanner(System.in);
	static final double rad(double d){ return d*Math.PI/180; }
	static final double r = 6378.1;
	public static void main(String[] args) {
		double[] theta = new double[2], phi = new double[2];
		while(true){
			theta[0] = stdin.nextDouble(); phi[0] = stdin.nextDouble();
			theta[1] = stdin.nextDouble(); phi[1] = stdin.nextDouble();
			if(theta[0] == -1 && phi[0] == -1 && theta[1] == -1 && phi[1] == -1) break;
			System.out.println( (int)(r*Math.acos(
					Math.cos(rad(theta[0]))*Math.cos(rad(phi[0]))*Math.cos(rad(theta[1]))*Math.cos(rad(phi[1])) + 
					Math.cos(rad(theta[0]))*Math.sin(rad(phi[0]))*Math.cos(rad(theta[1]))*Math.sin(rad(phi[1])) +
					Math.sin(rad(theta[0]))*Math.sin(rad(theta[1]))
					)+0.5)
					);
		}
	}
}
