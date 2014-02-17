import java.util.*;
public class aoj0235 {
	static final Scanner stdin = new Scanner(System.in);
	static int sum, maxLength;
	static ArrayList<Edge>[] adjList;
	static boolean[] used;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		while (true) {
			int N = stdin.nextInt();
			if (N == 0) break;
			adjList = new ArrayList[N];
			used = new boolean[N];
			sum = 0;
			for (int i = 0; i < N; ++i) adjList[i] = new ArrayList<Edge>();
			while (--N > 0) {
				int a = stdin.nextInt()-1, b = stdin.nextInt()-1, t = stdin.nextInt();
				adjList[a].add(new Edge(a, b, t));
				adjList[b].add(new Edge(b, a, t));
				sum += t;
			}
			maxLength = 0;
			used[0] = true;
			rec(0, 0, 0);
			System.out.println(sum * 2 - maxLength);
		}
	}
	static void rec(int cur, int length, int preDist) {
		boolean isLeaf = true;
		for (Edge e: adjList[cur]) {
			if (!used[e.d]) {
				isLeaf = false;
				used[e.d] = true;
				rec(e.d, length+e.w, e.w);
				used[e.d] = false;
			}
		}
		if (isLeaf) {
			sum -= preDist;
			maxLength = Math.max(maxLength, length - preDist);
		}
	}
	public static class Edge {
		int s, d, w;
		Edge(int s, int d, int w) { this.s = s; this.d = d; this.w = w; }
	}
}
