import java.util.*;
public class aoj0246 {
	static final Scanner stdin = new Scanner(System.in);
	static final int twoPair[][][] = { {{1,1}, {9,1}}, {{2,1}, {8,1}}, {{3,1}, {7,1}}, {{4,1}, {6,1}}, {{5,2}}};
	static final int pair[][][] = {
			{{1, 2}, {8, 1}}, {{1,1}, {2,1}, {7,1}}, {{1,1}, {3,1}, {6,1}}, {{1,1}, {4,1}, {5,1}}, {{2,2}, {6,1}}, {{2,1}, {3,1}, {5,1}}, {{2,1}, {4,2}}, {{3,2}, {4,1}}, 
			{{1,3}, {7,1}}, {{1,2}, {2,1}, {6,1}}, {{1,2}, {3,1}, {5,1}}, {{1,2}, {4,2}}, {{1,1}, {2,2}, {5,1}}, {{1,1}, {2,1}, {3,1}, {4,1}}, {{1,1}, {3,3}}, {{2,3}, {4,1}}, {{2,2}, {3,2}}, 
			{{1,4}, {6,1}}, {{1,3}, {2,1}, {5,1}}, {{1,3}, {3,1}, {4,1}}, {{1,2}, {2,2}, {4,1}}, {{1,2}, {2,1}, {3,2}}, {{1,1}, {2,3}, {3,1}}, {{2,5}}, 
			{{1,5}, {5,1}}, {{1,4}, {2,1}, {4,1}}, {{1,4}, {3,2}}, {{1,3}, {2,2}, {3,1}}, {{1,2}, {2,4}}, 
			{{1,6}, {4,1}}, {{1,5}, {2,1}, {3,1}}, {{1,4}, {2,3}}, 
			{{1,7}, {3,1}}, {{1,6}, {2,2}}, 
			{{1,8}, {2,1}}, 
			{{1,10}}
	};
	static final int freq[] = new int[10];
	static int sum, ans, candidate;
	static boolean use(int[][] pat) {
		for (int[] f : pat) 
			if (freq[f[0]] < f[1]) return false;
		for (int i = 0; i < pat.length; ++i) freq[pat[i][0]] -= pat[i][1];
		sum -= 10; candidate++;
		return true;
	}
	static void back(int[][] pat) {
		sum += 10; candidate--;
		for (int i = 0; i < pat.length; ++i) freq[pat[i][0]] += pat[i][1];
	}
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			Arrays.fill(freq, 0);
			ans = 0; sum = 0; candidate = 0;
			while (n-- > 0) {
				int tmp = stdin.nextInt();
				freq[tmp]++;
				sum += tmp;
			}
			for (int i = 0; i < twoPair.length;) if (!use(twoPair[i])) i++;
			ans = candidate;
			DFS(0);
			System.out.println(ans);
		}
	}
	static void DFS(int prek) {
		for (int k = prek; ans < sum/10 + candidate && k < pair.length; ++k) {
			if (use(pair[k])) {
				DFS(k);
				back(pair[k]);
			}
		}
		ans = Math.max(ans, candidate);
	}
}
