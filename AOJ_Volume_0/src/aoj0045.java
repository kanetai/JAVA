import java.util.*;
public class aoj0045 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) { 
		int sum = 0, num = 0, count = 0;
		while(stdin.hasNext()){
			String[] s = stdin.nextLine().split(",");
			int v = Integer.parseInt(s[0]), n = Integer.parseInt(s[1]);
			num += n; sum += (v*n); count++;
		}
		System.out.println(sum);
		System.out.println( (int)(0.5+(double)num /count) );
	}
}
