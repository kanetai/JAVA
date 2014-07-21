package algorithm.graph.networkFlow;
import java.util.*;
import algorithm.graph.GraphElement.*;
import algorithm.graph.networkFlow.NetworkFlowAlgorithm.*;
import static algorithm.graph.networkFlow.NetworkFlowAlgorithm.INF;
public enum MaximumFlowAlgorithm implements MaximumFlowSolver {
	EdmondsKarp { /** O(|V||E|^2) UVA No. 0820. */
		@Override public Result solve(List<List<Edge>> adjList, int s, int t) {
			Result res = new Result(adjList);
			while (augmentingPathExists(res, adjList, s, t)) {
				int cp = INF;
				for (int j = t; res.trace[j] != j; j = res.trace[j])
					cp = Math.min(cp, res.residualCapacity(res.trace[j], j));
				for (int j = t; res.trace[j] != j; j = res.trace[j]) {
					res.flow[res.trace[j]][j] += cp;
					res.flow[j][res.trace[j]] -= cp;
				}
				res.maximumFlow += cp;
			}
			return res;
		}
		/**
		 * Calculates back pointers of augmenting path using BFS and returns whether or not augmenting path exists.
		 * Back pointers of augmenting path are stored in res.trace. O(|E|)
		 * @param res      (in,out)  Result object(containing capacity and flow matrices)
		 * @param adjList  (in)      adjacency list of graph G. Edge weight indicates capacity.
		 * @param s        (in)      source node
		 * @param t        (in)      sink node
		 * @return         true if augmenting path exists, otherwise false.
		 */
		private boolean augmentingPathExists(final Result res, final List<List<Edge>> adjList, int s, int t) { //BFS
			Arrays.fill(res.trace, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(s); res.trace[s] = s;
			while (!q.isEmpty() && res.trace[t] == -1) {
				int u = q.poll();
				for(final Edge e : adjList.get(u)) if (res.trace[e.d] == -1 && res.residualCapacity(u, e.d) > 0) {
					res.trace[e.d] = u;
					q.add(e.d);
				}
			}
			return (res.trace[t] >= 0); // trace[x] == -1 <=> t-side
		}
	}, Dinic { /** O(|V|^2|E|) UVA No. 0820. */
		@Override public Result solve(List<List<Edge>> adjList, int s, int t) {
			Result res = new Result(adjList);
			int n = adjList.size();
			boolean[] used = new boolean[n];
			for (boolean augmented = true; augmented; ) {
				augmented = false;
				calcLayreLevel(res, adjList, s, t); //make layered network
				Arrays.fill(used, false);
				while (true) {
					int f = augment(used, res, adjList, s, t, INF);
					if (f == 0) break;
					res.maximumFlow += f; augmented = true;
				}
			}
			return res;
		}
		/**
		 * Calculates level of node in Dinic's layered network. Level info is stored in the res.trace. O(|E|)
		 * @param res      (in,out) Result object(containing flow, capacity and trace(level))
		 * @param adjList  (in)     adjacency list of graph G. Edge weight indicates capacity.
		 * @param s        (in)     source node.
		 * @param t        (in)     sink node.
		 */
		private void calcLayreLevel(Result res, final List<List<Edge>> adjList, int s, int t) {
			Arrays.fill(res.trace, -1); res.trace[s] = 0; //trace[v] indicates level of v.
			Queue<Integer> q = new LinkedList<Integer>(); q.add(s);
			for (int lm = adjList.size(); !q.isEmpty() && res.trace[q.peek()] < lm; ) { //BFS
				int u = q.poll();
				if (u == t) lm = res.trace[u];
				for (Edge e : adjList.get(u)) if (res.trace[e.d] == -1 && res.residualCapacity(u, e.d) > 0) {
					res.trace[e.d] = res.trace[u] + 1;
					q.add(e.d);
				}
			}
		}
		/**
		 * Augments flow in specified network used DFS and return blocking flow.O(|V|)
		 * @param used      (in,out) visited node in DFS.
		 * @param res       (in,out) Result object(containing flow, capacity and trace(level))
		 * @param adjList   (in)     adjacency list of graph G. Edge weight indicates capacity.
		 * @param u         (in)     start node.
		 * @param t         (in)     sink node.
		 * @param minC      (in)     minimum residual capacity in s-u path.
		 * @return
		 */
		private int augment(boolean[] used, Result res, final List<List<Edge>> adjList, int u, int t, int minC) { //DFS
			if (u == t) return minC;
			if (minC == 0 || used[u]) return 0;
			used[u] = true;
			for(Edge e : adjList.get(u)) if (res.trace[e.d] > res.trace[u]) { //level(e.d) > level(u)
				int f = augment(used, res, adjList, e.d, t, Math.min(minC, res.residualCapacity(u, e.d)));
				if (f > 0) {
					res.flow[u][e.d] += f; res.flow[e.d][u] -= f;
					used[u] = false;
					return f;
				}
			}
			return 0;
		}
	};
}
