import java.util.*;
public class aoj0067 {
	static final Scanner stdin = new Scanner(System.in);
	static final boolean[][] map = new boolean[14][14];
	public static void main(String[] args) { 
		while(stdin.hasNext()){
			Arrays.fill(map[0], false);
			for(int i = 1; i <= 12; ++i){
				Arrays.fill(map[i], false);
				char[] temp = stdin.next().toCharArray();
				for(int j=0; j<12; ++j)
					if(temp[j]=='1') map[i][j+1] = true;
			}
			Arrays.fill(map[13], false);
			System.out.println(solve());
		}
	}
	static int solve(){
		int res = 0;
		for(int i = 1; i <= 12; ++i)
			for(int j = 1; j <= 12; ++j)
				if(map[i][j]){ res++; DFS(i,j); }
		return res;
	}
	static void DFS(int i, int j){
		map[i][j] = false;
		if(map[i-1][j]) DFS(i-1,j);
		if(map[i][j+1]) DFS(i,j+1);
		if(map[i+1][j]) DFS(i+1,j);
		if(map[i][j-1]) DFS(i-1,j-1);
	}
}
