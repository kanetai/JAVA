import java.util.*;
public class aoj0123 {
	static final Scanner stdin = new Scanner(System.in);
	static String[] rank = {"AAA", "AA", "A", "B", "C", "D", "E", "NA"};
	static double[][] time = {{35.5, 71.0}, {37.5, 77.0}, {40.0, 83.0}, {43.0, 89.0}, {50.0, 105.0}, {55.0, 116.0}, {70.0, 148.0}};
	public static void main(String[] args) {
		while(stdin.hasNext()){
			double a = stdin.nextDouble(), b = stdin.nextDouble();
			int i;
			for(i = 0; i < time.length; ++i)
				if(a < time[i][0] && b < time[i][1]) break;
			System.out.println(rank[i]);
		}
	}
}
