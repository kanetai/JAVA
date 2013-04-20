import java.util.*;
public class aoj0060 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		boolean[] talon = new boolean[11];
		while(stdin.hasNext()){
			Arrays.fill(talon, true);
			int a = stdin.nextInt(), b = stdin.nextInt(), c = stdin.nextInt(), sum = 0;
			talon[a] = talon[b] = talon[c] = false;
			for(int i = 0; i < talon.length; ++i)
				if(talon[i] && i < 20-a-b) sum++;
			System.out.println( 100 * sum / 7 >= 50 ? "YES" : "NO");
		}
	}
}
