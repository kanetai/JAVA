import java.util.*;
public class aoj0113 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			int p = stdin.nextInt(), q = stdin.nextInt();
			StringBuilder ans = new StringBuilder();
			HashMap<Integer, Integer> found = new HashMap<Integer, Integer>();
			found.put(0,0);
			while(true){
				ans.append(p / q);
				p %= q;
				if(found.containsKey(p)) break;
				else found.put(p, ans.length());
				p *= 10;
			}
			System.out.println(ans.substring(1));
			if(p == 0) continue;
			for(int i = 1; i < ans.length(); ++i) System.out.print( i < found.get(p) ? ' ' : '^');
			System.out.println("");
		}
	}
}
