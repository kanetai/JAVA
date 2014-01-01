import java.util.*;
public class aoj0216 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), a = 4280 - 1150, temp;
			if (n == -1) break;
			if (n >= 30) { temp = n - 30;  a -= (160 * temp);  n -= temp; } 
			if (n >= 20) { temp = n - 20;  a -= (140 * temp);  n -= temp; } 
			if (n >= 10) { temp = n - 10;  a -= (125 * temp);  n -= temp; }
			System.out.println(a);
		}
	}
}
