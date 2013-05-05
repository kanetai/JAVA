import java.util.*;
public class aoj0178 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 5, H = 5001;
	static final BitSet[] map = new BitSet[H];
	public static void main(String[] args) {
		for(int i = 0; i < H; ++i) map[i] = new BitSet(N);
		int n;
		while((n = stdin.nextInt()) != 0){
			for(int i = 0; i < H; ++i) map[i].clear();
			for(int i = 0; i < n; ++i){
				int d = stdin.nextInt(), p = stdin.nextInt(), q = stdin.nextInt()-1;
				drop(d, p, q);	
			}
			int ans = 0;
			for(int i = 0; i < H; ++i) ans += map[i].cardinality();
			System.out.println(ans);
		}
	}
	static void drop(int d, int p, int q){
		int h = 1, y = H - 1, w = p; //横
		if(d == 2){ h = p; y = H - p; w = 1; } //縦
		y--; //一番上は空白
		while(y != 0 && downCheck(y,w,q)) y--; //落とせるところまで落とす
		for(int i = 0; i < h; ++i) for(int j = 0; j < w; ++j) map[y+i].set(q+j); //セット
		for(int i = 0; i < h; ++i){
			if(map[y].cardinality() == N)
				for(int j = y+1; j < H; ++j){ map[j-1].clear(); map[j-1].or(map[j]); } //消してずらす
			else
				++y;
		}
	}
	static boolean downCheck(int y, int w, int q){
		for(int i = 0; i < w; ++i) if(map[y-1].get(q+i)) return false;
		return true;
	}
}
