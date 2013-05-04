import java.util.*;
public class aoj0141 {
	static final Scanner stdin = new Scanner(System.in);
	static final int dx[] = {0, 1, 0, -1};
	static final int dy[] = {-1, 0, 1, 0};
	static char[][] map;
	static boolean check(int x, int y, int dir){
		int nx = x + dx[dir], ny = y + dy[dir];
		if(map[ny][nx] == '#') return false; //この条件多分いらない
		for(int i = 0; i < 4; ++i){
			if(nx + dx[i] == x && ny + dy[i] == y) continue;
			if(map[ny + dy[i]][nx + dx[i]] == '#') return false;
		}
		return true;
	}
	public static void main(String[] args) {
		int d = stdin.nextInt();
		while(d-- > 0){
			int n = stdin.nextInt();
			map = new char[n+4][n+4];
			for(int i = 0; i < n+4; ++i)
				for(int j = 0; j < n+4; ++j)
					map[i][j] = (i == 0 || j ==0 || i == n+3 || j == n+3) ? '#' : ' ';

			int dir = 0, x = 2, y = n+2;
			boolean flag = false; //方向転換したかどうか
			while(true){
				if(check(x, y, dir)){
					x += dx[dir]; y += dy[dir];
					flag = false; map[y][x] = '#';
				}else if(flag){
					break;
				}else{
					dir = (dir + 1) % 4;
					flag = true;
				}			
			}
			for(int i = 2; i < n+2; ++i){
				for(int j = 2; j < n+2; ++j) System.out.print(map[i][j]);
				System.out.println();
			}
			if(d > 0) System.out.println();
		}
	}
}
