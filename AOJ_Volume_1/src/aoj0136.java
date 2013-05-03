import java.util.*;
public class aoj0136 {
	static final Scanner stdin = new Scanner(System.in);
	static final int[] freq = new int[7];
	public static void main(String[] args) {
		int n = stdin.nextInt();
		Arrays.fill(freq, 0);
		while(n-- > 0){
			double x = stdin.nextDouble();
			if(x < 165.0) freq[1]++;
			else if(x < 170.0) freq[2]++;
			else if(x < 175.0) freq[3]++;
			else if(x < 180.0) freq[4]++;
			else if(x < 185.0) freq[5]++;
			else freq[6]++;
		}
		for(int i = 1; i <= 6; ++i){
			System.out.print(i+":");
			while(freq[i]-- > 0) System.out.print("*");
			System.out.println();
		}
	}
}
