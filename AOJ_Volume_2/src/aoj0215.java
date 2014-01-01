import java.util.*;
public class aoj0215 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE/2, TYPE = 5, ID = 0, DIST = 1;
	static class T {
		int y, x, id, type;
		T(int y, int x, int id, int type) { this.y = y; this.x = x; this.id = id; this.type = type;}
		int dist(T o) { return Math.abs(x - o.x) + Math.abs(y - o.y); }
	}
	public static void main(String[] args) {
		while (true) {
			int W = stdin.nextInt(), H = stdin.nextInt();
			if ((H|W) == 0) break;
			int id = 0;
			List<T> ref = new ArrayList<T>();
			List<List<T>> lists = new ArrayList<List<T>>();
			for (int i = 0; i < TYPE; ++i) lists.add(new ArrayList<T>());
			T  S = null, G = null;
			for (int i = 0; i < H; i++) {
				char[] line = stdin.next().toCharArray();
				for (int j = 0; j < W; j++) {
					switch (line[j]) {
					case 'S' : S = new T(i, j, id++, TYPE); ref.add(S); break;
					case 'G' : G = new T(i, j, id++, TYPE); ref.add(G); break;
					case '.' : break;
					default : 
						int type = line[j] - '1';
						T t = new T(i, j, id++, type);
						ref.add(t);
						lists.get(type).add(t);
						break;
					}
				}
			}
			
			int mType = -1, mDist = INF;
			for (int firstType = 0; firstType < TYPE; ++firstType) {
				S.type = firstType; G.type = (firstType + TYPE - 1) % TYPE;
				int dist = Dijkstra(ref, lists, S, G, id);
				if (dist < mDist) { mType = firstType + 1; mDist = dist; }
			}
			System.out.println(mDist < INF ? (mType + " " + mDist) : "NA");
		}
	}
	public static int Dijkstra(List<T> ref, List<List<T>> lists, T S, T G, int n){
		int[] dist = new int[n]; Arrays.fill(dist, INF);
		boolean[] visit = new boolean[n];
		Queue<int[]> q = new PriorityQueue<int[]>(n, new Comparator<int[]>() {
			@Override public int compare(int[] o1, int[] o2) { return o1[DIST] != o2[DIST] ? o1[DIST] - o2[DIST] : o1[ID] - o2[ID]; }
		});
		dist[S.id] = 0;
		q.add(new int[]{S.id, 0});
		while(!q.isEmpty()){
			int[] p = q.poll();
			T s = ref.get(p[ID]);
			if(visit[s.id]) continue;
			visit[s.id] = true;
			if (s.type == G.type) {
				int newDist = p[DIST] + s.dist(G);
				if(dist[G.id] > newDist){
					dist[G.id] = newDist;
					q.add(new int[]{ G.id, dist[G.id] });
				}
				continue;
			}
			for(final T d: lists.get((s.type+1)%TYPE)){
				int newDist = p[DIST] + s.dist(d);
				if(dist[d.id] > newDist){
					dist[d.id] = newDist;
					q.add(new int[]{ d.id, dist[d.id] });
				}
			}
		}
		return dist[G.id];
	}
}
