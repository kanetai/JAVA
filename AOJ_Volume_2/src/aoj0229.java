import java.util.*;
public class aoj0229 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int b = stdin.nextInt(), r = stdin.nextInt(), g = stdin.nextInt(), c = stdin.nextInt(), s = stdin.nextInt(), t = stdin.nextInt();
			if ((b|r|g|c|s|t) == 0) break;
			System.out.println(95*b + 63*r + 7*g + 2*c + 3*s - 3*t + 100);
		}
	}
}
