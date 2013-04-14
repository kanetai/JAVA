import java.util.*;
public class aoj0026 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 12;
	static int[][] map = new int[N+2][N+2];
	public static void main(String[] args) {
		for(int i=2; i < N; ++i) Arrays.fill(map[i], 2, N, 0);
		while(stdin.hasNext()){
			String[] a = stdin.nextLine().split(",");
			int x = Integer.parseInt(a[0]) + 2, y = Integer.parseInt(a[1]) + 2;
			switch( Integer.parseInt(a[2]) ){
			case 1: small(x,y); break;
			case 2: medium(x,y); break;
			case 3: large(x,y); 
			}
		}
		int zero = 0, max = 0;
		for(int i=2; i<N; ++i){
			for(int j=2; j<N; ++j){
				if(map[i][j] == 0) ++zero;
				max = Math.max(max, map[i][j]);
			}
		}
		System.out.println(zero + "\n" + max);
	}
	static void small(int x, int y){
		for(int i=-1; i<=1; ++i) ++map[y][x+i];
		++map[y+1][x]; ++map[y-1][x];
	}
	static void medium(int x, int y){
		small(x,y);
		++map[y+1][x+1]; ++map[y+1][x-1]; ++map[y-1][x+1]; ++map[y-1][x-1];
	}
	static void large(int x, int y){
		medium(x,y);
		++map[y+2][x]; ++map[y-2][x]; ++map[y][x+2]; ++map[y][x-2];
	}
}
