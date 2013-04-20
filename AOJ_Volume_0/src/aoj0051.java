import java.util.*;
public class aoj0051 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt(); stdin.nextLine();	
		while(n-->0){
			char[] input = stdin.nextLine().toCharArray();
			Arrays.sort(input);
			StringBuilder sb = new StringBuilder(new String(input));
			System.out.println(-Integer.parseInt(sb.toString()) + Integer.parseInt(sb.reverse().toString()));
		}
	}
}
