import java.util.*;
public class aoj0122 {
	static final Scanner stdin = new Scanner(System.in);
	static final int[] offx = {0,  1,  2,  2,  2,  1,  0,  -1, -2, -2, -2, -1};
	static final int[] offy = {-2, -2, -1, 0,  1,  2,  2,  2,  1,  0,  -1, -2};
	static class State implements Comparable<State>{
		int x, y, time;
		State(int x, int y, int time){this.x = x; this.y = y; this.time = time; }
		public int compareTo(State o) {
			return this.x < o.x ? -1 : this.x > o.x ? 1 :
				this.y < o.y ? -1 : this.y > o.y ? 1 :
					this.time < o.time ? -1 : this.time > o.time ? 1 : 0;
		}
	}
	static final int N = offx.length;
	public static void main(String[] args) { 
		while(true){
			int x = stdin.nextInt(), y = stdin.nextInt();
			if(x == 0 && y == 0) break;
			int n = stdin.nextInt();
			int[] sx = new int[n], sy = new int[n];
			for(int i = 0; i < n; ++i){
				sx[i] = stdin.nextInt(); sy[i] = stdin.nextInt();
			}
			System.out.println(BFS(x, y, n, sx, sy) ? "OK" : "NA");
		}
	}
	static boolean BFS(int x, int y, int n, int[] sx, int[]sy){
		Queue<State> q = new LinkedList<State>();
		State s = new State(x, y, -1);
		q.add(s);
		TreeSet<State> map = new TreeSet<State>();
		map.add(s);
		while(!q.isEmpty()){
			s = q.poll();
			if(s.time == n-1) return true;
			for(int i = 0; i < N; ++i){
				int nx = s.x + offx[i], ny = s.y + offy[i];
				if(check(nx, ny, sx[s.time+1], sy[s.time+1])){
					State ns = new State(nx, ny, s.time + 1);
					if(!map.contains(ns)){
						q.add(new State(nx, ny, ns.time));
						map.add(ns);
					}
				}
			}
		}
		return false;
	}
	static boolean check(int x, int y, int sx, int sy){
		return 0 <= x && 0 <= y && x < 10 && y < 10 && 
				sx-1 <= x && x <= sx+1 && sy-1 <= y && y <= sy+1;
	}
}
