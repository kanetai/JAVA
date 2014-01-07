import java.util.*;
public class aoj0224 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	static int m, n, k, d, N;
	public static class Edge implements Comparable<Edge>{
		int s, d, w, used;
		Edge(int s, int d, int w, int used){ this.s = s; this.d = d; this.w = w; this.used = used; }
		@Override public int compareTo(Edge o) { return w < o.w ? -1 : w == o.w ? 0 : 1; }
	}
	static int index(String s) {
		char prefix = s.charAt(0);
		switch(prefix) {
		case 'C': return Integer.parseInt(s.substring(1));
		case 'L': return Integer.parseInt(s.substring(1)) + m;
		case 'D': return N-1;
		case 'H': default: return 0;
		}
	}
	//static boolean isHome(int index) { return index == 0; }
	static boolean isCakeShop(int index) { return 0 < index && index <= m; }
	//static boolean isLandmark(int index) { return m < index && index <= m+n; }
	//static boolean isDestination(int index) { return index == N-1; }
	public static void main(String[] args) {
		while(true) {
			m = stdin.nextInt(); n = stdin.nextInt(); k = stdin.nextInt(); d = stdin.nextInt();
			if ((m|n|k|d) == 0) break;
			N = m+n+2;
			int ck[] = new int[m];
			for (int i = 0; i < m; ++i) ck[i] = stdin.nextInt();
			@SuppressWarnings("unchecked") List<Edge> adjList[] = new List[N];
			for (int i = 0; i < N; ++i) adjList[i] = new ArrayList<Edge>();
			for (int i = 0; i < d; ++i) {
				int src = index(stdin.next()), dst = index(stdin.next()), w = stdin.nextInt() * k;
				adjList[src].add(new Edge(src, dst, w - (isCakeShop(dst) ? ck[dst-1] : 0), 0));
				adjList[dst].add(new Edge(dst, src, w - (isCakeShop(src) ? ck[src-1] : 0), 0));
			}
			System.out.println(extDijkstra(adjList));
		}
	}
	public static int extDijkstra(List<Edge>[] list){
		int[][] dist = new int[N][1<<m]; 
		for (int i = 0; i < N; ++i) Arrays.fill(dist[i], INF);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(N*N);
		q.add(new Edge(-1, 0, 0, 0));
		dist[0][0] = 0;
		while(!q.isEmpty()){
			Edge p = q.poll();
			if(dist[p.d][p.used] < p.w) continue;
			for(final Edge u: list[p.d]){
				Edge next = new Edge(u.s, u.d, dist[p.d][p.used] + u.w, p.used);	
				if (isCakeShop(u.d)) {
					int bit = (1<<(u.d-1));
					next.used |= bit;
					if ((p.used & bit) != 0) next.w = INF; //cannot re-visit the cake shop.
				}
				if(dist[next.d][next.used] > next.w){
					dist[next.d][next.used] = next.w;
					q.add(next);
				}
			}
		}
		int ret = INF;
		for (int i = 0; i < (1<<m); ++i) ret = Math.min(ret, dist[N-1][i]);
		return ret;
	}
}
