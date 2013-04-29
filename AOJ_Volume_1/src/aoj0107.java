import java.util.*;
public class aoj0107 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int a[]={stdin.nextInt(), stdin.nextInt(), stdin.nextInt()};
			if(a[0] == 0 && a[1] == 0 && a[2] == 0) break;
			Arrays.sort(a);
			int n = stdin.nextInt();
			while(n-- > 0)
				System.out.println( 2*stdin.nextInt() > Math.hypot(a[0], a[1]) ? "OK" : "NA" );
		}
	}
}
