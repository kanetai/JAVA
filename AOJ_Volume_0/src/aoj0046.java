import java.util.*;
public class aoj0046{
	static final Scanner stdin = new Scanner(System.in); 
	public static void main(String[] args) {
		double max, min;
		max = min = stdin.nextDouble();
		while(stdin.hasNext()){
			double d = stdin.nextDouble();
			if(max < d) max = d;
			if(min > d) min = d;
		}
		System.out.println(max-min);
	}
}
