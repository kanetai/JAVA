import java.util.*;
public class aoj0007 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int n = stdin.nextInt();
		double m = 100000;
		while(n-- > 0){
			m = m + m*0.05;
			if(m%1000 != 0) m = m - m % 1000 + 1000; 
		}
		System.out.println((int)m);
	}
}
