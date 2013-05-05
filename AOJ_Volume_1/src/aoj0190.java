import java.util.*;
public class aoj0190 {
	static final Scanner stdin = new Scanner(System.in);
	static final String goal = "  0   123 45678 9ab   0  ";
	static final boolean f[] = {false, false, true, false, false,
		false, true,  true, true,  false,
		true,  true,  true, true,  true,
		false, true,  true, true,  false,
		false, false, true, false, false};
	static final int M = 5, D = 4;
	static final int gx[]={2,1,2,3,0,1,2,3,4,1,2,3,2};
	static final int gy[]={0,1,1,1,2,2,2,2,2,3,3,3,4};
	static final int ManhattanDistance(int x, int y, int g){ return Math.abs(x-gx[g]) + Math.abs(y-gy[g]); }
	static final int dx[]={0, 1, 0,-1};
	static final int dy[]={-1,0, 1, 0};
	static final boolean check(int x, int y){
		int i = y*M+x;
		return 0 <= i && i < goal.length() && f[i];
	}
	static class T implements Comparable<T>{
		String state;
		int step;
		int score;
		T(String state, int step){
			this.state = state;
			this.step = step;
			score = step;
			for(int i = 0; i < state.length(); ++i){
				char c = state.charAt(i);
				if(c == '0' || c==' ') continue;
				int g = (Character.isDigit(c) ? c - '0' : c - 'a' + 10);
				int y = i/M, x = i - M*y;
				score += ManhattanDistance(x, y, g);
			}
		}
		@Override public int compareTo(T o) { return score - o.score; }
	}
	public static void main(String[] args) {
		int i;
		while((i = stdin.nextInt())!= -1){
			StringBuilder sb = new StringBuilder("  "); sb.append(Integer.toHexString(i)); sb.append("  ");
			for(int y = 1; y < M; ++y)
				for(int x = 0; x < M; ++x)
					sb.append(f[y*M+x] ? Integer.toHexString(stdin.nextInt()): " ");
			int ans = AStar(sb.toString());
			System.out.println(ans < 0 ? "NA" : ans);
		}
	}
	static int AStar(String s){
		Queue<T> q = new PriorityQueue<T>();
		Set<String> closed = new HashSet<String>();
		int[] zero = new int[2], zx = new int[2], zy = new int[2];
		q.add(new T(s, 0));
		while(!q.isEmpty()){
			T e = q.poll();
			if(e.state.equals(goal)) return e.step;
			if(closed.contains(e.state)) continue;
			closed.add(e.state);
			zero[0] = e.state.indexOf("0"); zero[1] = e.state.indexOf("0", zero[0]+1);
			zy[0] = zero[0]/M; zx[0] = zero[0] - zy[0]*M; zy[1] = zero[1]/M;  zx[1] = zero[1] - zy[1]*M;
			for(int k = 0; k < 2; ++k){
				for(int i = 0; i < D; ++i){
					int nx = zx[k]+dx[i], ny = zy[k]+dy[i], n = ny*M + nx;
					if(check(nx, ny)){
						char[] nstate = e.state.toCharArray();
						nstate[zero[k]] = nstate[n]; nstate[n] = '0';
						T nt = new T(new String(nstate), e.step+1);
						if(nt.score <= 20) q.add(nt);
					}
				}
			}
		}
		return -1;
	}
}
