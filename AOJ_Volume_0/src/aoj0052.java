import java.util.*;
public class aoj0052 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int n = stdin.nextInt();
			if(n==0) break;
			int res = 0;
			while((n/=5) != 0) res += n;
			System.out.println(res);
		}
	}
}
