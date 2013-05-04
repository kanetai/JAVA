import java.util.*;
public class aoj0147 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 100, M = 17;
	static final int[] ans = new int[N], q = new int[M];
	static Group[] g = new Group[N];
	public static void main(String[] args){
		for(int i = 0; i < N; ++i) g[i] = new Group(i);
		Arrays.fill(q, 0); Arrays.fill(ans, 0);
		int time = 0, cur = 0;
		while(cur < N){
			while(cur < N && g[cur].hitting <= time){ //次の人が到来している
				int i = insIndex(g[cur].n);
				if(i != -1){
					for(int j = i; j < i+g[cur].n; ++j) q[j] = g[cur].meal; //着席
					ans[g[cur].id] = time - g[cur].hitting;
					cur++;
				}else{
					break;
				}
			}
			time++;
			for(int i = 0; i < M; ++i) if(q[i] > 0) q[i]--;
		}
		while(stdin.hasNext()) System.out.println(ans[stdin.nextInt()]);
	}
	static int insIndex(int length){
		int count = 0;
		for(int i = 0; i < M; ++i){
			if(q[i] == 0){
				if(++count == length) return i - length + 1;
			}else{
				count = 0;
			}
		}
		return -1; //挿入不可
	}
	static class Group{
		int id, n, hitting, meal;
		Group(int i){
			id = i; //グループ番号
			n = (i%5 == 1 ? 5 : 2); //人数
			hitting = 5 * i; //到達時刻
			meal = 17*(i%2) + 3*(i%3) + 19; //食事時間
		}
	}
}
