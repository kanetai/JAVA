import java.util.*;
public class aoj0024 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			double v = stdin.nextDouble();
			System.out.println( (int) Math.ceil( (v*v/19.6 + 5)/5 ) );
			// ceil( v*v/98 + 1 )ではwrong answerだった
		}
	}
}
