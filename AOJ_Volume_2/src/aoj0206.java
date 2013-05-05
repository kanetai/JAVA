import java.util.*;
public class aoj0206 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int L, sum, N = 12, i, ans;
		while((L = stdin.nextInt()) != 0){
			for(i = sum = ans = 0; i < N; ++i){
				sum += stdin.nextInt() - stdin.nextInt();
				if(ans == 0 && sum >= L) ans = i+1;
			}
			System.out.println(ans > 0 ? ans : "NA");
		}
	}
}
