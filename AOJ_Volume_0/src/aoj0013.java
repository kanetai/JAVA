import java.util.*;
public class aoj0013 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
		while(stdin.hasNext()){
			int i = stdin.nextInt();
			if( i == 0 ) System.out.println( st.pop() );
			else st.push(i);
		}
	}
}
