import java.util.*;
public class aoj0137 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		for(int i = 1; i <= n; ++i){
			System.out.println("Case " + i + ":");
			int s = stdin.nextInt();
			for(int j = 0; j < 10; ++j){
				s = Integer.parseInt(String.format("%08d", s*s).substring(2, 6));
				System.out.println(s);
			}
		}
	}
}
