import java.util.*;
public class aoj0126 {
	static final Scanner stdin = new Scanner(System.in);
	static final boolean[][] flag = new boolean[9][9];
	static final int[][] p = new int[9][9];
	static final int[] freq = new int[10];
	static void checkRow(int i){
		Arrays.fill(freq, 0);
		for(int x: p[i]) freq[x]++;
		for(int j = 0; j < 9; ++j) if(freq[p[i][j]] > 1) flag[i][j] = false;
	}
	static void checkCol(int j){
		Arrays.fill(freq, 0);
		for(int i = 0; i < 9; ++i) freq[p[i][j]]++;
		for(int i = 0; i < 9; ++i) if(freq[p[i][j]] > 1) flag[i][j] = false;
	}
	static void checkSq(int i, int j){
		Arrays.fill(freq, 0);
		for(int I = i; I < i + 3; ++I)
			for(int J = j; J < j + 3; ++J)
				freq[p[I][J]]++;
		for(int I = i; I < i + 3; ++I)
			for(int J = j; J < j + 3; ++J)
				if(freq[p[I][J]] > 1) flag[I][J] = false;
	}
	public static void main(String[] args) {
		int n = stdin.nextInt();
		while(true){
			for(int i = 0; i < 9; ++i){
				for(int j = 0; j < 9; ++j)
					p[i][j] = stdin.nextInt();
				Arrays.fill(flag[i], true);
			}
			for(int i = 0; i < 9; ++i){
				checkCol(i);
				checkRow(i);
			}
			for(int i = 0; i < 9; i+=3)
				for(int j = 0; j < 9; j+=3)
					checkSq(i,j);
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++)
					System.out.print((flag[i][j] ? " " : "*") + p[i][j]);
				System.out.println();
			}
			if(--n != 0) System.out.println();
			else break;
		}
	}
}
