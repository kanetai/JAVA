package algorithm.graph;
import static algorithm.graph.GraphElement.INF;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class HamiltonianGraph {
	/** Result of Traveling Salesman Problem (TSP) */
	public static class TSPResult{
		private int s;		//source node (※= destination node)
		private int length;	//length of shortest Hamilton cycle
		private int[][] prev;	//back pointers
		public TSPResult(int s, int length, int[][] prev){ set(s, length, prev); }
		final private void set(int s, int length, int[][] prev){ this.s = s; this.length = length; this.prev = prev; }
		final public int getLength(){ return length; }
		final public int getPrev(int S, int v){ return prev[S][v]; }
		/** Builds the shortest Hamilton cycle */
		public List<Integer> buildPath(){ return buildPath(false); }
		/**
		 * Builds the shortest Hamiltonian cycle
		 * @param useRecursive
		 * @return the shortest Hamiltonian cycle
		 */
		public List<Integer> buildPath(boolean useRecursive){
			int N = prev.length, S = N - 1;
			LinkedList<Integer> path = new LinkedList<Integer>();
			if(length < INF ){
				if(useRecursive) buildPath(S, s, path);
				else buildPath(path);
			}
			return path;
		}
		/** buildPath non recursive ver. */
		private void buildPath(LinkedList<Integer> path){
			int N = prev.length, S = N - 1;
			int prevNode = -1;
			for(int i = s; S != 0; S ^= (1<<i), i = prevNode){
				path.addFirst(i);
				prevNode = prev[S][i];
			}
			path.addFirst(s);
		}
		/** buildPath recursive ver. */
		private void buildPath(int S, int v, List<Integer> path){
			if(S != 0) buildPath(prev[S][v], S & ~(1<<v), path);
			path.add(v);
		}
	}

	/**
	 * Gets length of the shortest Hamiltonian cycle and back pointers for building the shortest path.
	 * O(2^|V| |V|^2)<br>
	 * AOJ No. 0120, 0146(partial modification, Semi-Hamilton graph)
	 * @param G adjacency matrix
	 * @param s source node (※= destination node)
	 * @return  TSPResult
	 */
	static TSPResult TSP(int[][] G, int s){
		int n = G.length, N = 1 << n;
		int[][] DP = new int[N][n], prev = new int[N][n];
		for(int i = 0; i < N; ++i) Arrays.fill(DP[i], INF);
		for(int i = 0; i < n; ++i){
			DP[1<<i][i] = G[s][i];
			prev[1<<i][i] = s;
		}
		for (int S = 1; S < N; ++S) {
			for (int i = 0; i < n; ++i) {
				if ((S & (1<<i)) == 0) continue;
				for (int j = 0; j < n; ++j) {
					if ((S & (1<<j)) != 0 ) continue;
					//i in S, j not in S 
					int newDist = DP[S][i]+G[i][j];
					if(DP[S|(1<<j)][j] > newDist) {
						DP[S|(1<<j)][j] = newDist;
						prev[S|(1<<j)][j] = i;
					}
				}
			}
		} 
		return new TSPResult(s, DP[N-1][s], prev);
	}
}
