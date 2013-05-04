import java.util.*;
public class aoj0156 {
	final static Scanner stdin = new Scanner(System.in);
	final static char GOAL = '?', START = '&', SPACE = '.', FOSSE = '#';
	final static int INF = Integer.MAX_VALUE/2;
	final static int dy[] = {0, -1, 0, 1};
	final static int dx[] = {-1, 0, 1, 0};
	static int cost[][];
	static char map[][];
	public static void main(String[] args) {	
		while(true){
			int w = stdin.nextInt(), h = stdin.nextInt();
			if((w|h) == 0) break;
			stdin.nextLine(); //改行
			map = new char[h+2][w+2];
			int sx = 0, sy = 0;
			Arrays.fill(map[0], GOAL);
			for(int i = 1; i <= h; ++i){
				String line = stdin.nextLine();
				int idx = line.indexOf(START);
				if(idx >= 0){ sx = idx + 1; sy = i; }
				System.arraycopy(line.toCharArray(), 0, map[i], 1, w);
				map[i][0] = map[i][w+1] = GOAL;
			}
			Arrays.fill(map[h+1], GOAL);
			System.out.println(BFS(sx, sy));
		}
	}
	static int BFS(int sx, int sy){
		int h = map.length, w = map[0].length;
		cost = new int[h][w];
		for(int i = 0; i < h; ++i) Arrays.fill(cost[i], INF);

		Queue<int[]> q = new PriorityQueue<int[]>(h*w, new Comparator<int[]>(){  //o[0]:=x, o[1]:=y, o[2]:=cost
			@Override public int compare(int[] o1, int[] o2){ return o1[2] - o2[2]; }
		}); //cost優先
		cost[sy][sx] = 0;
		q.add(new int[]{sx, sy, 0});
		while(!q.isEmpty()){
			int[] p = q.poll();
			int x = p[0], y = p[1];
			if(map[y][x] == GOAL) return cost[y][x];
			for(int d = 0; d < dx.length; ++d) add(q, p, d);
		}
		return -1;
	}
	static void add(Queue<int[]> q, int[] p, int d){
		int x = p[0], y = p[1], c = cost[y][x], nx = x + dx[d], ny = y + dy[d];
		if(map[ny][nx] == FOSSE){
			if(map[y][x] != FOSSE){
				if(c + 1 < cost[ny][nx]){
					q.add(new int[]{nx, ny, c+1});
					cost[ny][nx] = c+1;
				}
			}else{
				if(c < cost[ny][nx]){
					q.add(new int[]{nx, ny, c});
					cost[ny][nx] = c;
				}
			}
		}else{
			if(c < cost[ny][nx]){
				q.add(new int[]{nx, ny, c});
				cost[ny][nx] = c;
			}
		}
	}
}
