import java.util.*;
public class aoj0033 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int A[] = new int[10], n = stdin.nextInt();
		while(n-- > 0){
			for(int i=0; i<10; ++i) A[i] = stdin.nextInt();
			System.out.println( solve(A) ? "YES" : "NO");
		}
	}
	static boolean solve(int A[]){
		int b=0, c=0;
		for(int a : A){
			if(b < a) b=a;
			else if(c < a) c=a;
			else return false;
		}
		return true;
	}
}
