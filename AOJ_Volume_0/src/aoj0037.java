import java.util.*;
public class aoj0037 {
	static final Scanner stdin = new Scanner(System.in);
	//→↑←↓
	static int D = 4; // 4 directions
	static int dx[] = {1, 0,  -1, 0};
	static int dy[] = {0, -1, 0,  1};
	static char out[] = {'R', 'U', 'L', 'D'};
	static int WIDTH = 5;
	public static void main(String[] args) {
		boolean[][] g = new boolean[25][25]; //adjacency matrix
		String str;
		for(int i=0; i<4; ++i){
			str = stdin.nextLine();
			for(int j=0; j<WIDTH-1; ++j)
				if( str.charAt(j) == '1' )
					g[yx2pos(i,j)][yx2pos(i,j)+1] = g[yx2pos(i,j)+1][yx2pos(i,j)] = true;
			str = stdin.nextLine();
			for(int j=0; j<WIDTH; ++j)
				if( str.charAt(j) == '1' )
					g[yx2pos(i,j)][yx2pos(i,j)+WIDTH] = g[yx2pos(i,j)+WIDTH][yx2pos(i,j)] = true;
		}
		str = stdin.nextLine();
		for(int j=0; j<WIDTH-1; ++j)
			if( str.charAt(j) == '1' )
				g[yx2pos(WIDTH-1,j)][yx2pos(WIDTH-1,j)+1] = g[yx2pos(WIDTH-1,j)+1][yx2pos(WIDTH-1,j)] = true;
		System.out.println( solve(g) );
	}
	static int yx2pos(int y, int x){ return WIDTH*y + x;}
	//current coord(y,x), next direction of movement(nd), adjacency matrix(g)
	static boolean check(int y, int x, int nd, boolean[][] g){
		int ny = y + dy[nd], nx = x + dx[nd];
		return ny>=0 && ny < WIDTH && nx>=0 && nx < WIDTH && g[yx2pos(y,x)][yx2pos(ny,nx)];
	}
	static String solve(boolean[][] g){
		int x = 0, y = 0; //current coord
		int d = 0; //current direction of movement →
		StringBuilder res = new StringBuilder();
		do{
			if( check(y, x, (d+1)%D, g) ){ //d + ←
				d = (d+1)%D;
			}else if( check(y, x, d, g) ){ //d + ↑
				;
			}else if( check(y, x, (d+3)%D, g)){ //d + →
				d = (d+3)%D;
			}else{ //d　+ ↓
				d = (d+2)%D;
			}
			x += dx[d]; y += dy[d]; res.append(out[d]);
		}while( !(x==0 && y==0) );
		return res.toString();
	}
}
