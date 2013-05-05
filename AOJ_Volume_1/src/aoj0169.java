import java.util.*;
public class aoj0169 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 21;
	public static void main(String[] args) {
		while(true){
			String[] s = stdin.nextLine().split(" ");
			if(s[0].equals("0")) break;
			int one = 0, base = 0;
			for(int i = 0; i < s.length; ++i){
				int temp = Integer.parseInt(s[i]);
				if(temp == 1) one += Math.min(temp, 10);
				else base += Math.min(temp, 10);
			}
			int ans = 0;
			for(int i = 0; i <= one; ++i){
				int sum = base + i*10 + one;
				ans = Math.max(ans, (sum > N ? 0 : sum)); 
			}
			System.out.println(ans);
		}
	}
}
