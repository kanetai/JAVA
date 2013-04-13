import java.util.*;
public class aoj0002 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int a,b;
		while(stdin.hasNext()){
			a=stdin.nextInt();
			b=stdin.nextInt();
			System.out.println( solve(a,b) );
		}
	}
	static int solve(int a, int b){
		int res=1;
		for(int c = a + b; c >=10; ++res, c /= 10);
		return res;
	}
}
