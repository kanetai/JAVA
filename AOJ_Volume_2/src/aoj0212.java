import java.util.*;
public class aoj0212 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2;
	public static void main(String[] args) {
		while(true) {
			int c = stdin.nextInt(), n = stdin.nextInt(), m = stdin.nextInt(), s = stdin.nextInt(), d = stdin.nextInt();
			if((c|n|m|s|d) == 0) break;
			--s; --d;
			List<List<Edge>> list = new ArrayList<List<Edge>>(n);
			for(int i = 0; i < n; ++i) list.add(new LinkedList<Edge>());
			for(int i = 0; i < m; ++i) {
				int src = stdin.nextInt()-1, dst = stdin.nextInt()-1, cost = stdin.nextInt();
				list.get(src).add(new Edge(dst, cost, -1));
				list.get(dst).add(new Edge(src, cost, -1));
			}
			System.out.println(solve(list, c, s, d));
		}
	}
	public static int solve(List<List<Edge>> list, int c, int s, int d) {
		int n = list.size();
		int[][] dist = new int[c+1][n]; 
		for(int i = 0; i <= c; ++i) Arrays.fill(dist[i], INF);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		for(int i = 0; i<=c; ++i) dist[i][s] = 0;
		q.add(new Edge(s, 0, c));
		while(!q.isEmpty()){
			Edge p = q.poll();
			int v = p.dst;
			if(v == d) return p.cost;
			if(dist[p.ticket][v] < p.cost) continue; //dist[v] < p.weight
			int N = (p.ticket > 0 ? 2 : 1);
			for(int k = 0; k < N; ++k){
				for(final Edge u: list.get(v)){ //※u.s = v
					int newTicket = p.ticket - k, newDist = dist[newTicket][v] + (u.cost>>k);
					if(dist[newTicket][u.dst] > newDist){
						dist[newTicket][u.dst] = newDist;
						q.add(new Edge(u.dst, newDist, newTicket));
					}
				}
			}
		}
		return INF;
	}
	static class Edge implements Comparable<Edge>{
		int dst, cost, ticket;
		Edge(int dst, int cost, int ticket){ this.dst = dst; this.cost = cost; this.ticket = ticket; }
		@Override public int compareTo(Edge o) {
			return cost != o.cost ? cost - o.cost : ticket != o.ticket ? o.ticket - ticket : dst - this.dst;
		}
	}
}
