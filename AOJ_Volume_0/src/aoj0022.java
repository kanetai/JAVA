import java.util.*;
public class aoj0022 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if( n == 0 ) break;
			int sum = stdin.nextInt(), max = sum;
			for(int i=1; i < n ; ++i){
				int x = stdin.nextInt();
				sum = sum + x < x ? x : sum + x;
				max = Math.max(sum, max);
			}
			System.out.println(max);
		}
	}
}
