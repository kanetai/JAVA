import java.util.*;
public class aoj0066 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int[][] pat = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, 
				{1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
		while(stdin.hasNext()){
			char[] l = stdin.nextLine().toCharArray();
			char out = 'd';
			for(int i = 0; i<pat.length; ++i){
				if(l[pat[i][0]] != 's' && l[pat[i][0]] == l[pat[i][1]]&& l[pat[i][1]] == l[pat[i][2]]){
					out = l[pat[i][0]];
					break;
				}
			}
			System.out.println(out);
		}
	}
}
