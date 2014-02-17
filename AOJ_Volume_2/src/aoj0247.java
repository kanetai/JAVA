import java.util.*;
public class aoj0247 {
	static final Scanner stdin = new Scanner(System.in);
	static int W, H, sidx, gidx, lim;
	static final char map[] = new char[12*12];
	static final boolean visited[] = new boolean[12*12];
	static final int dy[] = {-1, 0, 1, 0}, dx[] = {0, 1, 0, -1}, INF = Integer.MAX_VALUE/2;
	static final int hstar[] = new int[12*12], hardness[] = new int[12*12], ice[] = new int[12*12];
	public static void main(String[] args) {
		while (true) {
			W = stdin.nextInt(); H = stdin.nextInt();
			if ((W|H) == 0) break;
			for (int i = 0; i < H; ++i) {
				String s = stdin.next();
				for (int j = 0; j < W; ++j) {
					char c = s.charAt(j);
					int idx = index(i,j);
					map[idx] = c;
					switch(c) {
					case 'S': sidx = index(i, j); map[idx] = '.'; break;
					case 'G': gidx = index(i, j); map[idx] = '.'; break;
					}
				}
			}
			calcHStar(gidx);
			Arrays.fill(hardness, 0); Arrays.fill(ice, -1);
			int iceNum = 0;
			for (int i = 0; i < H; ++i) for (int j = 0; j < W; ++j) {
				int idx = index(i,j);
				if (map[idx] == 'X' && ice[idx] == -1) {
					discoverIce(idx, iceNum); 
					hardness[iceNum] /= 2;
					iceNum++;
				}
			}
			Arrays.fill(visited, false); visited[sidx] = true;
			for (lim = 0; !IDAStar(0, sidx, sidx) ;lim++);
			System.out.println(lim);
		}
	}
	static void calcHStar(int s) { //WFS
		Arrays.fill(hstar, INF);
		Queue<Integer> q = new LinkedList<Integer>();
		hstar[s] = 0;
		q.add(s);
		while(!q.isEmpty()){
			int v = q.poll();
			for(int k = 0; k < 4; ++k) {
				int ny = getY(v) + dy[k], nx = getX(v) + dx[k], nv = index(ny, nx);
				if(inRange(ny, nx) && (map[nv] == '.' || map[nv] == 'X') && hstar[nv] > hstar[v] + 1){
					hstar[nv] = hstar[v]+1;
					q.add(nv);
				}
			}
		}
	}
	static void discoverIce(int pos, int id) { //WFS
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(pos); ice[pos] = id; hardness[id]++;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int k = 0; k < 4; ++k) {
				int ny = getY(v) + dy[k], nx = getX(v) + dx[k], npos = index(ny, nx);
				if (inRange(ny, nx) && map[npos] == 'X' && ice[npos] == -1) {
					q.add(npos); ice[npos] = id; hardness[id]++;
				}
			}
		}
	}
	static boolean IDAStar(int depth, int pos, int prepos) {
		if (pos == gidx) return true;
		if (depth + hstar[pos] > lim) return false;
		for (int k = 0; k < 4; ++k) { //前にいた位置を除いて、周りが既に到達済みならバックトラック
			int ny = getY(pos) + dy[k], nx = getX(pos) + dx[k], npos = index(ny, nx);
			if (!inRange(ny, nx) || npos == prepos) continue;
			if (visited[npos]) return false;
		}
		for (int k = 0; k < 4; ++k) {
			int ny = getY(pos) + dy[k], nx = getX(pos) + dx[k], npos = index(ny, nx);
			if (!inRange(ny, nx) || visited[npos]) continue;
			boolean nextIce = (map[npos] == 'X');
			if (nextIce || map[npos] == '.') {
				if (nextIce && hardness[ice[npos]] <= 0) continue;
				if (nextIce) hardness[ice[npos]]--; 
				visited[npos] = true;
				if (IDAStar(depth+1, npos, pos)) return true;
				if (nextIce) hardness[ice[npos]]++; 
				visited[npos] = false;
			}
		}
		return false;
	}
	static int getX(int index) { return index%W; }
	static int getY(int index) { return index/W; }
	static int index(int y, int x) { return y * W + x; }
	static boolean inRange(int y, int x) { return 0 <= y && y < H && 0 <= x && x < W; }
}
