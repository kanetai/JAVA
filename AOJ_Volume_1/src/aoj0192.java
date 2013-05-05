import java.util.*;
public class aoj0192 {
	static final Scanner stdin = new Scanner(System.in);
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		while(true){
			int m = stdin.nextInt(), n = stdin.nextInt();
			if((m|n) == 0)break;
			int[] t = new int[n+1];
			for(int i = 1; i <= n; ++i)t[i] = stdin.nextInt();
			int[][] P = new int[m][2];
			for(int i = 0; i < m; ++i) Arrays.fill(P[i], 0);
			List<Integer> ans = new LinkedList<Integer>();
			Queue<Integer> q = new LinkedList<Integer>();
			int r = 10;
			while(ans.size() < n){
				if(r%10 == 0 && 1 <= r/10 && r/10 <= n) q.add(r/10);
				for(int i = 0; i < m; ++i) for(int j = 0; j < 2; j++) if(P[i][j] > 0) t[P[i][j]]--;
				for(int i = 0; i < m; ++i){
					if(P[i][0] > 0 && t[P[i][0]] <= 0){
						ans.add(P[i][0]); P[i][0] = P[i][1]; P[i][1] = 0;
						if(P[i][0] > 0 && t[P[i][0]] <= 0){ //出した後にもう一回確認
							ans.add(P[i][0]); P[i][0] = 0; 
						}
					}
				}
				boolean flag = true;
				while(!q.isEmpty() && flag){
					flag = false;
					int dm = INF, um = INF, di = -1, ui = -1;
					for(int i = 0; i < m; i++){
						if(P[i][0] == 0){
							flag = true;
							P[i][0] = q.poll();
							break;
						}
						if(P[i][1]==0){
							if(t[P[i][0]] >= t[q.peek()]){
								if(t[P[i][0]]-t[q.peek()] < um){ um = t[P[i][0]]-t[q.peek()]; ui = i; }
							}else{
								if(t[q.peek()]-t[P[i][0]] < dm){ dm = t[q.peek()]-t[P[i][0]]; di = i; }
							}
						}
					}
					if(flag)continue;
					if(ui >= 0){
						P[ui][1] = P[ui][0]; P[ui][0] = q.poll(); flag = true;
					}else if(di >= 0){
						P[di][1] = P[di][0]; P[di][0] = q.poll(); flag = true;
					}
				}
				r++;
			}
			for(int i = 0; i < n; ++i) System.out.print(ans.get(i) + (i == n-1 ? "\n" : " "));
		}
	}
}
