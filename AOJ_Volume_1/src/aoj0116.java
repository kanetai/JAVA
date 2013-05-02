import java.util.*;
public class aoj0116 {
	static final Scanner stdin = new Scanner(System.in);
	static final int MAX = 500;
	static final int[][] rowCount = new int[MAX][MAX];
	public static void main(String[] args) {
		while(true){
			int H = stdin.nextInt(), W = stdin.nextInt(); stdin.nextLine();
			if(H == 0 && W == 0) break;
			for(int i = 0; i < H; ++i){
				char[] line = stdin.nextLine().toCharArray();
				for(int j = W-1; j >= 0; --j){
					if(line[j] == '.') rowCount[i][j] = 1 + (j == W - 1 ? 0 : rowCount[i][j+1]);
					else rowCount[i][j] = 0;
				}
			}
			int ans = 0;
			for(int i = 0; i < H; ++i){
				for(int j = 0; j < W; ++j){
					if(rowCount[i][j] == 0) continue;
					int m = Integer.MAX_VALUE;
					for(int k = i; k < W; ++k){
						m = Math.min(m, rowCount[k][j]);
						if(m==0) break;
						ans = Math.max(ans, (k-i+1)*m);
					}
				}
			}
			System.out.println(ans);
		}
	}
}
