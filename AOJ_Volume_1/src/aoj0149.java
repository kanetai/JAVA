import java.util.*;
public class aoj0149 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 4;
	static int[] l = new int[N], r = new int[N];
	static int eyesight(double x){ return (x >= 1.1 ? 0 : x >= 0.6 ? 1 : x >= 0.2 ? 2 : 3); }
	public static void main(String[] args) {
		Arrays.fill(l, 0); Arrays.fill(r, 0);
		while(stdin.hasNext()){
			l[eyesight(stdin.nextDouble())]++;
			r[eyesight(stdin.nextDouble())]++;
		}
		for(int i = 0; i < N; ++i) System.out.println(l[i] + " " + r[i]);
	}
}
