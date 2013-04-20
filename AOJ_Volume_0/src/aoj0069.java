import java.util.*;
public class aoj0069 {
	static final Scanner stdin = new Scanner(System.in);
	static final boolean[][] map = new boolean[30][12];
	public static void main(String[] args) { 
		while(true){
			int n = stdin.nextInt();
			if(n==0) break;
			int m = stdin.nextInt();
			int goal = stdin.nextInt();
			int d = stdin.nextInt();
			for(int i = 0; i < d; ++i){
				Arrays.fill(map[i], false);
				char[] line = stdin.next().toCharArray();
				for(int j = 0; j < n-1; ++j)
					map[i][j+1] = (line[j]=='1');
			}
			System.out.println(solve(n, m, goal, d));
		}
	}
	static String solve(int n, int m, int goal, int d){
		if(search(n,m,goal,d)) return "0";
		for(int i = 0; i < d; ++i){
			for(int j = 1; j < n; ++j){
				if(!map[i][j] && !map[i][j-1] && !map[i][j+1]){
					map[i][j] = true;
					if(search(n,m,goal,d)) return (i+1)+" "+j;
					map[i][j] = false;
				}
			}
		}
		return "1";
	}
	static boolean search(int n, int m, int goal, int d){
		for(int i = 0; i < d; ++i){
			if(map[i][m]) m++;
			else if(map[i][m-1]) m--;
		}
		return m == goal;
	}
}
