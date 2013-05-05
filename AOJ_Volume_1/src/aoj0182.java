import java.util.*;
public class aoj0182 {
	static final Scanner stdin = new Scanner(System.in);
	static int n;
	static int[] a;
	static boolean[] finalState, water;
	public static void main(String[] args) {
		while((n = stdin.nextInt()) != 0){
			a = new int[n]; finalState = new boolean[n]; 
			for(int i = 0; i < n; ++i) a[i] = stdin.nextInt();
			Arrays.sort(a); Arrays.fill(finalState, false);
			finalState[0] = true; //YESだと仮定すると、最終状態で一番小さいやつに水が注がれている状態になる
			System.out.println(DFS(1, a[n-1]-a[0]) ? "YES": "NO");
		}
	}
	/** 最終状態を決めてから検証 */
	static boolean DFS(int k, int v){ //a[k]以降からvになるように選ぶ
		if(v == 0){ //最終状態が決定したので検証開始
			water = finalState.clone();
			for(int i = 0; i < n; ++i){
				if(!water[i]){ //a[i]は未使用、a[0]～a[i-1]は使用済み
					if(!dfs(i-1, a[i])) return false; //a[i]にa[0]～a[i-1]の水を使って、a[i]に入れられるかを調べる
					water[i] = true;
				}
			}
			return true;
		}
		if(v < a[k]) return false; //選んだ組み合わせではa[n-1]分の水を分割して注げなかった。
		//最終状態をDFSで決定 a[k]以降を使う
		finalState[k] = true; //a[k]を使う
		if(DFS(k+1, v-a[k])) return true; //成功してたらreturn
		finalState[k] = false;
		return DFS(k+1, v); //a[k]は使わない
	}
	static boolean dfs(int k, int v){ //a[k]～a[0]で水が入っているものから選ぶ(大きい方優先で使う)
		if(v == 0) return true;
		if(k < 0) return false;
		if(!water[k]) return dfs(k-1, v);
		if(a[k] <= v){
			water[k] = false;
			if(dfs(k-1, v-a[k])) return true;
			water[k] = true;
		}
		return dfs(k-1, v);
	}
}
