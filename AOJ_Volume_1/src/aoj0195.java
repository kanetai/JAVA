import java.util.*;
public class aoj0195 {
	static final Scanner stdin = new Scanner(System.in);
	static final int N = 5;
	public static void main(String[] args) {
		Map<Integer, Character> S = new TreeMap<Integer, Character>();
		int s;
		while((s = stdin.nextInt()+stdin.nextInt()) != 0){
			S.clear();
			S.put(-s, 'A');
			for(int i = 1; i < N; ++i) S.put(-stdin.nextInt()-stdin.nextInt(), (char)('A'+i));
			int k = S.keySet().iterator().next();
			System.out.println(S.get(k) + " " + -k);
		}
	}
}
