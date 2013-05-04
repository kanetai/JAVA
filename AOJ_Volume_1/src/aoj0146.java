import java.util.*;
public class aoj0146 { //状態(S, v) S: 到達済み頂点集合, v: 最後に到達した頂点
	static final Scanner stdin = new Scanner(System.in);
	static final double INF = 1e15; 
	static int[] name; //name[i]: ノードiの倉番号
	static int[] d; //d[i]: 屋敷からノードiへの距離
	static int[] w; //w[i]: ノードiの宝の総重量
	static int[] W; //W[S]: 状態Sでの宝の総重量 
	static double[][] DP; //DP[S][v]: 状態(S, v)になるまでの最短時間
	static int[][] B; //B[S][v]: バックポインタ
	static double getTime(int i, int j, int S){ //状態(S,i)から(S∪j, j)に遷移するのにかかる時間
		return Math.abs(d[i]-d[j])/(2000.0/(70+W[S])); //Math.abs(d[i]-d[j])*((70+W[S])/2000.0)
	}
	public static void main(String[] args) {
		int n = stdin.nextInt();
		name = new int[n]; d = new int[n]; w = new int[n];
		DP = new double[1<<n][n]; B = new int[1<<n][n];
		W = new int[1<<n];
		for(double[] i: DP) Arrays.fill(i, INF);
		for(int i = 0; i < n; ++i){
			name[i] = stdin.nextInt();
			d[i] = stdin.nextInt(); w[i] = 20 * stdin.nextInt();
			DP[1<<i][i] = 0; B[1<<i][i] = -1;
		}
		//W[S]: 状態Sでの重さ
		for(int S = 0; S < (1<<n); ++S){
			W[S] = 0;
			for(int i = 0; i < n; ++i) if((S>>i & 1) == 1) W[S] += w[i];
		}
		List<Integer> ans = solve1(n);
		for(int i = 0; i < ans.size(); ++i) System.out.print(name[ans.get(i)] + (i < ans.size()-1 ? " " : "\n"));
	}
	static List<Integer> solve1(int n){
		int N = 1<<n;
		for(int S = 1; S < N; ++S){
			for(int i = 0; i < n; ++i){
				if( (S & (1<<i)) == 0) continue;
				for(int j = 0; j < n; ++j){
					if((S & (1<<j)) == 0){
						double temp = DP[S][i] + getTime(i, j, S);
						if(DP[S|(1<<j)][j] > temp){
							DP[S|(1<<j)][j] = temp;
							B[S|(1<<j)][j] = i;
						}
					}
				}
			}
		}
		ArrayList<Integer> res = new ArrayList<Integer>(n);
		double m = INF;
		int x = -1;
		for(int i = 0; i < n; ++i) if(m > DP[N-1][i]){
			m = DP[N-1][i];
			x = i;
		}
		buildPath(x, N-1, res);
		return res;
	}
	static void buildPath(int s, int S, List<Integer> path){
		if(S != 0) buildPath(B[S][s], S & ~(1<<s), path);
		else return; //B[0][s] = -1はpathに加えない
		path.add(s);
	}
}
