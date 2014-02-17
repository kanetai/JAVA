import java.util.*;
public class aoj0244 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2, T = 2;
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), m = stdin.nextInt();
			if ((n|m) == 0) break;
			List<List<Edge>> adjList = new ArrayList<List<Edge>>();
			for (int i = 0; i < n; ++i) adjList.add(new ArrayList<Edge>());
			for (int i = 0; i < m; ++i) {
				int src = stdin.nextInt()-1, dst = stdin.nextInt()-1, cost = stdin.nextInt();
				adjList.get(src).add(new Edge(dst, cost, 0));
				adjList.get(dst).add(new Edge(src, cost, 0));
			}
			System.out.println(solve(adjList));
		}
	}
	public static int solve(List<List<Edge>> list) {
		int n = list.size();
		int[][] dist = new int[T+1][n]; 
		for(int i = 0; i <= T; ++i) Arrays.fill(dist[i], INF);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		dist[T][0] = 0;
		q.add(new Edge(0, 0, T));
		while(!q.isEmpty()){
			Edge p = q.poll();
			int v = p.dst;
			if(dist[p.ticket][v] < p.cost) continue;
			if(v == n-1 && p.ticket != 1) return p.cost;
			if (1 <= p.ticket) for(final Edge u: list.get(v)) { 
				int newTicket = p.ticket - 1, newDist = dist[p.ticket][v];
				if(dist[newTicket][u.dst] > newDist){
					dist[newTicket][u.dst] = newDist;
					q.add(new Edge(u.dst, newDist, newTicket));
				}
			}
			if (p.ticket != 1) for(final Edge u: list.get(v)) { 
				int newTicket = p.ticket, newDist = dist[p.ticket][v] + u.cost;
				if(dist[newTicket][u.dst] > newDist){
					dist[newTicket][u.dst] = newDist;
					q.add(new Edge(u.dst, newDist, newTicket));
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
