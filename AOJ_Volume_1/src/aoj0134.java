import java.util.*;
public class aoj0134 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		long sum = 0L;
		for(int i = 0; i < n; ++i) sum += stdin.nextLong();
		System.out.println(sum/n);
	}
}
