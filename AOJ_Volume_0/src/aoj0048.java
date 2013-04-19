import java.util.*;
public class aoj0048 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext())
			System.out.println(solve(stdin.nextDouble()));
	}
	static String solve(double d){
		return d <= 48.00 ? "light fly" : d <= 51.00 ? "fly" : d <= 54.00 ? "bantam" : 
			d <= 57.00 ? "feather" : d <= 60.00 ? "light" : d <= 64.00 ? "light welter" :
				d <= 69.00 ? "welter" : d <= 75.00 ? "light middle" : d <= 81.00 ? "middle" : 
					d <= 91.00 ? "light heavy" : "heavy";
	}
}
