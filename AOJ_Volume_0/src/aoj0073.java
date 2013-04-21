import java.util.*;
public class aoj0073 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int x = stdin.nextInt(), h = stdin.nextInt();
			if(x == 0 && h == 0) break;
			System.out.println(x*(x + 2*Math.hypot(h, x/2.0)) );
		}
	}
}
