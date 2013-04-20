import java.util.*;
public class aoj0057 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			int n = stdin.nextInt();
			System.out.println((n*n+n+2)/2);
		}
	}
}
