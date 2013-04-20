import java.util.*;
public class aoj0070 {
	static final Scanner stdin = new Scanner(System.in);
	static final boolean[] flag = new boolean[10];
	static final int[][] table = new int[11][331];
	public static void main(String[] args) { 
		for(int i = 0; i < 11; ++i) Arrays.fill(table[i], 0);
		DFS(10, 0, 0);
		while(stdin.hasNext()){
			int n = stdin.nextInt(), s = stdin.nextInt();
			System.out.println(s<331 ? table[n][s] : 0);
		}
	}
	static void DFS(int max, int depth, int sum){
		table[depth][sum]++;
		if(depth == max) return;
		for(int i = 0; i < 10; ++i){
			if(!flag[i]){
				flag[i] = true;
				DFS(max, depth+1, sum+i*(depth+1));
				flag[i] = false;
			}
		}
	}
}
