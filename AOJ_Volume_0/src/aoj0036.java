import java.util.*;
public class aoj0036 {
	static final Scanner stdin = new Scanner(System.in);
	static boolean[][] map = new boolean[11][11];
	public static void main(String[] args) {
		while(stdin.hasNext()){
			for(boolean[] r : map) Arrays.fill(r, false);
			int sx = -1, sy = -1;
			for(int i=0; i<8; ++i){
				String l = stdin.next();
				for(int j=0; j<8; ++j){
					if(l.charAt(j) == '1'){
						map[i][j] = true;
						if( sx < 0 ){ sx=j; sy=i;  }
					}
				}
			}
			System.out.println( solve(sx, sy) );
		}
	}
	static String solve(int sx, int sy){
		if( map[sy][sx+1] ){
			if( map[sy][sx+2] ) return "C";
			if( map[sy+1][sx+2] ) return "E";
			if( map[sy+1][sx+1] ) return "A";
			return "G";
		}
		if(map[sy+1][sx+1]) return "F";
		if(map[sy+2][sx]) return "B";
		return "D";
	}
}
