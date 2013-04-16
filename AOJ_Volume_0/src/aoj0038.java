import java.util.*;
public class aoj0038 {
	static final Scanner stdin = new Scanner(System.in);
	static Integer[] count = new Integer[13];
	public static void main(String[] args) {
		int[] deal = new int[5];
		while(stdin.hasNext()){
			String[] str = stdin.nextLine().split(",");
			for(int i=0; i<5; ++i) deal[i] = Integer.parseInt(str[i]);
			System.out.println( solve(deal) );
		}
	}
	static String solve(int[] deal){
		Arrays.fill(count, 0);
		for(int n: deal) count[n-1]++;
		StringBuilder str = new StringBuilder("");
		for(int c: count) str.append(""+c);
		str.append( str.charAt(0) );
		Arrays.sort(count);
		switch(count[12]){
		case 4: return "four card";
		case 3: return count[11] == 2 ? "full house" : "three card";
		case 2: return count[11] == 2 ? "two pair" : "one pair";
		case 1: if( str.indexOf("11111") >= 0 ) return "straight";
		}
		return "null";
	}
}
