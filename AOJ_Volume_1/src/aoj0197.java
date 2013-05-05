import java.util.*;
public class aoj0197 {
	static final Scanner stdin = new Scanner(System.in);
	static int ans;
	public static int GCD(int a, int b){ ans++; return b == 0 ? a : GCD(b, a%b); }
	public static void main(String[] args) {
		while(true){
			int x = stdin.nextInt(), y = stdin.nextInt();
			if((x|y) == 0) break;
			if(x < y){ int temp = x; x = y; y = temp; }
			ans = -1;
			System.out.println(GCD(x,y) + " " + ans);
		}
	}
}
