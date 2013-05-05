import java.util.*;
import java.awt.Point;
class aoj0194 {
	static final Scanner stdin = new Scanner(System.in);
	static final Point dir[] = { new Point(0,-1), new Point(1,0), new Point(0,1), new Point(-1,0) };
	static Point start, goal;
	static int M, N, D;
	static final Map<Point, HashMap<Point, Integer>> badEdge = new HashMap<Point, HashMap<Point, Integer>>();
	static final Map<Point, Integer> light = new HashMap<Point, Integer>();
	static final int hstar(Point p){ return D*(Math.abs(goal.x - p.x) + Math.abs(goal.y - p.y)); }
	static final Point parsePoint(String s){
		String[] p = s.split("-");
		return new Point(Integer.parseInt(p[1])-1, p[0].charAt(0)-'a');
	}
	static final void addEdge(Point src, Point dst, int d){
		if(!badEdge.containsKey(src)) badEdge.put(src, new HashMap<Point, Integer>());
		if(!badEdge.containsKey(dst)) badEdge.put(dst, new HashMap<Point, Integer>());
		badEdge.get(src).put(dst, d); badEdge.get(dst).put(src, d);
	}
	public static void main(String[] args) {
		while(true){
			M = stdin.nextInt(); N = stdin.nextInt();
			if((M|N) == 0) break;
			D = stdin.nextInt();
			int ns = stdin.nextInt();
			light.clear(); badEdge.clear();
			for(int i = 0; i < ns; ++i) light.put(parsePoint(stdin.next()), stdin.nextInt());
			int nc = stdin.nextInt();
			for(int i = 0; i < nc; ++i) addEdge(parsePoint(stdin.next()), parsePoint(stdin.next()), -1);
			int nj = stdin.nextInt();
			for(int i = 0; i < nj; ++i) addEdge(parsePoint(stdin.next()), parsePoint(stdin.next()), stdin.nextInt());
			start = parsePoint(stdin.next()); goal = parsePoint(stdin.next());
			System.out.println(AStar());
		}
	}
	static int AStar(){
		PriorityQueue<State> q = new PriorityQueue<State>(M*N*400);
		HashSet<State> closed = new HashSet<State>();
		q.add(new State(start.x, start.y, 0, 1)); //time = 0, dir=1(東向き)
		while(!q.isEmpty()){
			State e = q.poll();
			if(e.p.equals(goal)) return e.time;
			if(closed.contains(e)) continue;
			closed.add(e);
			HashMap<Point, Integer> nextNode = badEdge.get(e.p);
			for(int i = 0; i < 4; ++i){
				if((e.dir + 2) % 4 == i) continue; //turn
				int nx = e.p.x + dir[i].x, ny = e.p.y + dir[i].y;
				if(0 <= nx && nx < N && 0 <= ny && ny < M){
					Point np = new Point(nx, ny);
					Integer d = null;
					if(nextNode != null) d = nextNode.get(np);
					if(d != null && d == -1) continue; //工事中
					int ntime = e.time + D + (d == null ? 0 : d); //d:渋滞遅延
					if(light.containsKey(np) && ((ntime/light.get(np)) & 1) != (i&1)) continue; //信号赤
					State ne = new State(np.x, np.y, ntime, i);
					if(ne.fstar > 100) continue;
					q.add(ne);
				}
			}
		}
		return -1;
	}
	static class State implements Comparable<State>{
		Point p;
		int time, dir, fstar;
		State(int x, int y, int time, int dir){
			this.p = new Point(x,y); this.time = time; this.dir = dir;
			fstar = time + hstar(p);
		}
		@Override public int compareTo(State o) { return fstar - o.fstar; } //for priority queue
		@Override public boolean equals(Object _o){ //for hash set
			State o = (State)_o;
			return p.equals(o.p) && time == o.time && dir == o.dir;
		}
		@Override public int hashCode(){ //for hash set
			return p.x + 100*p.y + 10000*dir + 100000*time;
		}
	}
}
