import java.util.*;
public class aoj0132 {
	static final Scanner stdin = new Scanner(System.in);
	static int H, W, n, m; //n: ピースの数　m: 選択ピース数
	static int[] h = new int[40], w = new int[40], n_fill = new int[40];
	static Integer[] no = new Integer[10];
	static char[][] in = new char[20][];
	static char[][][] piece = new char[40][20][20];
	static boolean[][] flag = new boolean[20][20];
	static boolean DFS(int c){ //大きいピースから埋めてく
		if(c == m) return true; //成功
		for(boolean[] f: flag) Arrays.fill(f, false);
		//空のマスに、残りのピースをどう置いても埋まらない場合に枝刈りする
		//何回同じピースを使ってもいいので、空のマス(i,j)を埋めてみる
		for(int i = 0; i < H; ++i) for(int j = 0; j < W; ++j){
			if(in[i][j] == '.' && !flag[i][j]){ //i,j:埋めてない座標
				for(int k = c; k < m; k++) for(int t = 0; t < 4; ++t){ //k:選ぶピース番号, t:回転パターン
					int p = no[k] + t * n; //p:選んだピースパターン
					for(int y = Math.max(0, i - h[p] + 1); y <= i && y + h[p] <= H; ++y){ //ピースの右下にあわせてずらしていく
						FAILURE: for(int x = Math.max(0, j - w[p] + 1); x <= j && x + w[p] <= W; ++x){
							for(int a = 0; a < h[p]; ++a) for(int b = 0; b < w[p]; ++b)
								if(piece[p][a][b] == '#' && in[y + a][x + b] == '#') continue FAILURE;
							for(int a = 0; a < h[p]; ++a) for(int b = 0; b < w[p]; ++b)
								if(piece[p][a][b] == '#') flag[y + a][x + b] = true;
						}
					}
				}
				if(!flag[i][j]) return false; //埋められない
			}
		}
		//ピースの挿入位置決め
		for(int t = 0; t < 4; ++t){
			int p = no[c] + t * n;
			for(int y = 0; y + h[p] <= H; ++y) for(int x = 0; x + w[p] <= W; ++x){
				boolean ok = true;
				for(int a = 0; a < h[p]; ++a) for(int b = 0; b < w[p]; ++b)
					if(in[y + a][x + b] == '#' && piece[p][a][b] == '#') ok = false;
				if(ok){
					for(int a = 0; a < h[p]; ++a) for(int b = 0; b < w[p]; ++b)
						if(piece[p][a][b] == '#') in[y + a][x + b] = '#';
					boolean res = DFS(c + 1);
					for(int a = 0; a < h[p]; ++a) for(int b = 0; b < w[p]; ++b)
						if(piece[p][a][b] == '#') in[y + a][x + b] = '.';
					if(res) return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		while(true){
			H = stdin.nextInt(); W = stdin.nextInt();
			if((H|W) == 0) break;
			int n_empty = 0; //パズル
			for(int i = 0; i < H; ++i){
				in[i] = stdin.next().toCharArray();
				for(int j = 0; j < W; ++j) if(in[i][j] == '.') n_empty++;
			}

			n = stdin.nextInt(); //ピースの数
			for(int i = 0; i < n; ++i){
				h[i] = stdin.nextInt(); w[i] = stdin.nextInt(); //ピース
				n_fill[i] = 0;
				for(int j = 0; j < h[i]; ++j){
					System.arraycopy(stdin.next().toCharArray(), 0, piece[i][j], 0, w[i]);
					//piece[i][j] = stdin.next().toCharArray();　<- pieceが40×20×20じゃなくなるから駄目
					for(int k = 0; k < w[i]; ++k) if(piece[i][j][k] == '#') n_fill[i]++;
				}
				int pre = i; //ピース回転
				for(int j = 0; j < 3; ++j){
					int cur = pre + n;
					h[cur] = w[pre];
					w[cur] = h[pre];
					n_fill[cur] = n_fill[pre];
					for(int y = 0; y < h[pre]; ++y) for(int x = 0; x < w[pre]; ++x)
						piece[cur][x][h[pre] - y - 1] = piece[pre][y][x];
					pre = cur;
				}
			}
			int p = stdin.nextInt(); //クエリ数
			while(p-- != 0){
				m = stdin.nextInt(); //選択ピース数
				int sum = 0;
				for(int i = 0; i < m; ++i){
					no[i] = stdin.nextInt(); //ピース番号 1始まりなのでデクリメント
					sum += n_fill[--no[i]];
				}
				if(sum != n_empty){
					System.out.println("NO");
					continue;
				}
				Arrays.sort(no, 0, m, new Comparator<Integer>(){
					public int compare(Integer o1, Integer o2){
						return n_fill[o2] - n_fill[o1];
					}
				});
				System.out.println(DFS(0) ? "YES" : "NO");
			}
		}
	}
}
