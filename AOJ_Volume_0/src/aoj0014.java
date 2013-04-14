import java.util.*;
public class aoj0014 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()) System.out.println( solve(stdin.nextInt()) );
	}
	static int solve(int d){
		int ans = 0;
		for( int i=d; i<600; i+=d ) ans += d*i*i;
		return ans;
	}
}
