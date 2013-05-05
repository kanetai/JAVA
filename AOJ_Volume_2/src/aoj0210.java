import java.util.*;
public class aoj0210 {
	static final Scanner stdin = new Scanner(System.in);
	static final int Delta = 3, d[][] = {{-1, 0}, {0,-1}, {1,0}, {0,1}}, D = 4, Y = 0, X = 1, L = 180;
	@SuppressWarnings("serial") static final Map<Character, Integer> dir2id = new HashMap<Character, Integer>(){
		{put('N', 0); put('W', 1); put('S', 2); put('E',3); }
	};
	static final char WALL = '#', SPACE = '.', EXIT = 'X', MAN = 'M', TEMP = 'm';
	static class T{
		int y, x, d;
		T(int y, int x, int d){ this.y = y; this.x = x; this.d = d; }
	}
	public static void main(String[] args) {
		while(true){
			int W = stdin.nextInt(), H = stdin.nextInt();
			if((W|H) == 0) break;
			char[][] map = new char[H][];
			List<T> p = new ArrayList<T>();
			for(int i = 0; i < H; ++i){
				map[i] = stdin.next().toCharArray();
				for(int j = 0; j < W; ++j)
					switch(map[i][j]){
					case WALL: break;
					case SPACE: break;
					case EXIT: break; 
					default: p.add(new T(i,j,dir2id.get(map[i][j]))); map[i][j] = MAN;
					}
			}
			int ans = 1;
			while(!p.isEmpty() && ans++ <= L){
				List<T> next = new ArrayList<T>();
				int n = p.size();
				int[] nextD = new int[n];
				for(int i = 0; i < n; ++i){
					T e = p.get(i);
					nextD[i] = -1;
					for(int j = 0; j < D; ++j){
						int nd = (e.d+Delta+j)%D;
						int ny = e.y + d[nd][Y], nx = e.x + d[nd][X];
						if(map[ny][nx] == SPACE || map[ny][nx] == EXIT){ nextD[i] = nd; break; }
					}
					if(nextD[i] == -1) next.add(e);
				}
				for(int k = 0; k < D; ++k){
					for(int i = 0; i < n; ++i){
						if(nextD[i] != (k+1)%D) continue;
						T e = p.get(i);
						int ny = e.y + d[nextD[i]][Y], nx = e.x + d[nextD[i]][X];
						if(map[ny][nx] == EXIT){
							map[e.y][e.x] = SPACE;
							map[ny][nx] = TEMP;
						}else if(map[ny][nx] == SPACE){
							map[ny][nx] = MAN;
							map[e.y][e.x] = SPACE;
							next.add(new T(ny,nx,nextD[i]));
						}else{
							next.add(new T(e.y, e.x, nextD[i]));
						}
					}
				}
				p = next;
				for(int i = 0; i < H; ++i) for(int j = 0; j < W; ++j) if(map[i][j] == TEMP) map[i][j] = EXIT;
			}
			System.out.println(--ans > L ? "NA" : ans);
		}
	}
}
