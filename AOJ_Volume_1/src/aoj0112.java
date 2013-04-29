import java.util.*;
public class aoj0112 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			int[] l = new int [n];
			for(int i = 0; i < n; ++i) l[i] = stdin.nextInt();
			Arrays.sort(l);
			long sum = 0, ans = 0;
			for(int i = 0; i < n; ++i){
				ans += sum;
				sum += l[i];
			}
			System.out.println(ans);
		}
	}
}
