import java.util.*;
public class aoj0102 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n == 0) break;
			int[] a = new int[n];
			int sum;
			Arrays.fill(a, 0);
			for(int i = 0; i < n; ++i){
				sum = 0;
				for(int j = 0; j < n; ++j){
					int m = stdin.nextInt();
					System.out.printf("%5d", m);
					a[j] += m;
					sum += m;
				}
				System.out.printf("%5d\n", sum);
			}
			sum = 0;
			for(int i: a){
				System.out.printf("%5d", i);
				sum += i;
			}
			System.out.printf("%5d\n", sum);
		}
	}
}
