import java.util.*;
public class aoj0158 {
	static final Scanner stdin = new Scanner(System.in);
	public static int operate(int n){ return (n&1) == 0 ? n/2 : 3*n+1; }
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			int ans;
			for(ans = 0; n != 1; ++ans) n = operate(n);
			System.out.println(ans);
		}
	}
}
