import java.util.*;
public class aoj0148 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 39;
	public static void main(String[] args) {
		while(stdin.hasNext()) System.out.printf("3C%02d\n", (stdin.nextInt()-1)%N+1);
	}
}
