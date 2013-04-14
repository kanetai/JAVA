import java.util.*;
public class aoj0019 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { System.out.println(factorial(stdin.nextInt())); }
	static long factorial(int n){ return n > 1 ? n * factorial(n-1) : 1; }
}
