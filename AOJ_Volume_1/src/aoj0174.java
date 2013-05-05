import java.util.*;
public class aoj0174 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int s = 0, game = 2;
		int[] d = new int[2];
		String str;
		while(!(str=stdin.nextLine()).equals("0")){
			if((game = (game + 1)%3) == 0) s = 0;
			Arrays.fill(d, 0);
			for(char c : str.toCharArray()) if(c == 'A') d[0]++; else d[1]++;
			d[s]--;
			if(d[0] > d[1]){ d[0]++; s = 0; }
			else{ d[1]++; s = 1; }
			System.out.println(d[0] + " " + d[1]);
		}
	}
}
