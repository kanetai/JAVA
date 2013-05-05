import java.util.*;
public class aoj0164 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			int[] a = new int[n];
			for (int i = 0; i < n; i++) a[i] = stdin.nextInt();
			int b = 32;
			for(int i = 0; b > 0; i = (i+1)%n) {
				b -= (b-1)%5;
				System.out.println(b);
				b -= a[i];
				System.out.println(b >= 0 ? b : 0);
			}
		}
	}
}
