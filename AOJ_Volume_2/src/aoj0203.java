import java.util.*;
public class aoj0203 {
	static final Scanner stdin = new Scanner(System.in);
	static final int SAFE = 0, UNSAFE = 1, JUMP = 2;
	static final int dx[] = {-1, 0, 1};
	static final int dy[] = {1, 1, 1};
	public static void main(String[] args) {
		while(true){
			int X = stdin.nextInt(), Y = stdin.nextInt();
			if((X|Y) == 0) break;
			int[][] map = new int[Y+1][X+2], DP = new int[Y+1][X+2];
			Arrays.fill(map[0], UNSAFE); Arrays.fill(DP[0], 0);
			for(int i = 1; i <= Y; ++i){
				for(int j = 1; j <= X; ++j){
					map[i][j] = stdin.nextInt();
					DP[i][j] = i == 1 && map[i][j] == SAFE ? 1 : 0;
				}
				map[i][0] = map[i][X+1] = DP[i][0] = map[i][X+1] = 0; //UNSAFE
			}
			for(int y = 2; y <= Y; ++y){
				for(int x = 1; x <= X; ++x){
					if(map[y][x] == SAFE){
						for(int k = 0; k < 3; ++k)
							if(map[y-dy[k]][x-dx[k]] == SAFE) DP[y][x] += DP[y-dy[k]][x-dx[k]];
						if(map[y-2][x] == JUMP) DP[y][x] += DP[y-2][x];
					}
					if(map[y][x] == JUMP){
						if(map[y-1][x] == SAFE) DP[y][x] += DP[y-1][x];
						if(map[y-2][x] == JUMP) DP[y][x] += DP[y-2][x]; 
					}
				}
			}
			int ans = 0;
			for(int j = 1; j <= X; ++j){
				ans += DP[Y][j];
				if(map[Y-1][j] == JUMP) ans += DP[Y-1][j];
			}
			System.out.println(ans);
		}
	}
}
