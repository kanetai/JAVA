import java.util.*;
public class aoj0082 {
	static final Scanner stdin = new Scanner(System.in);
	static final int[] RIDE = {4, 1, 4, 1, 2, 1, 2, 1};
	static final int[][] PAT = new int[8][8];
	static final String[] sPAT = new String[8];
	public static void main(String[] args) {
		int[] c = new int[8];
		for(int i = 0; i < 8; ++i){
			StringBuilder s = new StringBuilder();
			for(int j = 0; j < 8; ++j){
				PAT[i][j] = RIDE[(i+j)%8];
				s.append(' ');
				s.append(PAT[i][j]);
			}
			sPAT[i] = s.substring(1);
		}
		while(stdin.hasNext()){
			String minstr = sPAT[0];
			int min = 0;
			for(int i = 0; i < 8; ++i){
				c[i] = stdin.nextInt();
				min += (RIDE[i]-c[i] < 0 ? c[i] - RIDE[i] : 0);
			}		
			for(int i = 1; i < 8; ++i){
				int temp = 0;
				for(int j = 0; j < 8; ++j)
					temp += (PAT[i][j]-c[j] < 0 ? c[j] - PAT[i][j] : 0);
				if(temp < min || (temp == min && sPAT[i].compareTo(minstr) < 0)){
					minstr = sPAT[i];
					min = temp;
				}
			}
			System.out.println(minstr);
		}
	}
}
