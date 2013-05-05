import java.util.*;
public class aoj0183 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 3;
	static final char[][] board = new char[N][];
	public static void main(String[] args) {
		String s;
		while(!(s = stdin.nextLine()).equals("0")){
			board[0] = s.toCharArray(); board[1] = stdin.nextLine().toCharArray(); board[2] = stdin.nextLine().toCharArray();
			String ans = "NA";
			boolean d = true, D = true;
			for(int i = 0; i < N; ++i){
				boolean r = true, c = true;
				if(board[0][0] == '+' || board[0][0] != board[i][i]) d = false;
				if(board[0][N-1] == '+' || board[0][N-1] != board[i][N-1-i]) D = false;
				for(int j = 1; j < N; ++j){
					if(board[i][0] == '+' || board[i][0] != board[i][j]) r = false;
					if(board[0][i] == '+' || board[0][i] != board[j][i]) c = false;
				}
				if(r){ ans = ""+board[i][0]; break; }
				if(c){ ans = ""+board[0][i]; break; }
			}
			System.out.println( d ? board[0][0] : D ? board[0][N-1] : ans);
		}
	}
}
