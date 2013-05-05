import java.util.*;
public class aoj0160 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE;
	static final int size[] = {60, 80, 100, 120, 140, 160, INF};
	static final int weight[] = {2, 5, 10, 15, 20, 25, INF};
	static final int fee[] = {600, 800, 1000, 1200, 1400, 1600, 0};
	static final int N = size.length;
	static final int calc(int x, int y, int h, int w){
		int s = x + y + h;
		for(int i = 0; i < N; ++i) if(s <= size[i] && w <= weight[i]) return fee[i];
		return 0;
	}
	public static void main(String[] args) {
		int n;
		while((n = stdin.nextInt()) != 0){
			long ans = 0;
			for (int i = 0; i < n; i++)
				ans += calc(stdin.nextInt(), stdin.nextInt(), stdin.nextInt(), stdin.nextInt());
			System.out.println(ans);
		}
	}
}
