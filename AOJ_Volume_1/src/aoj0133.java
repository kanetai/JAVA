import java.util.*;
public class aoj0133 {
	static final Scanner stdin = new Scanner(System.in);
	static final char[][][] pat = new char[4][8][8]; 
	public static void main(String[] args) {
		for(int i = 0; i < 8; i++)
			pat[0][i] = stdin.next().toCharArray();
		for(int k = 1; k < 4; ++k){
			System.out.println(k*90);
			for(int i = 0; i < 8; ++i){
				for(int j = 0; j < 8; ++j) pat[k][i][j] = pat[k-1][8-j-1][i];
				System.out.println(pat[k][i]);
			}
		}
	}
}
