import java.util.*;
public class aoj0055 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			double a = stdin.nextDouble();
			double sum = a;
			for(int i = 2; i <= 10; i++)
				sum += ((i&1)==1 ? (a /= 3.0) : (a *= 2.0));
			System.out.println(sum);
		}
	}
}
