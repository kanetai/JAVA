import java.util.*;
public class aoj0267 {
	static final Scanner stdin = new Scanner(System.in);
	static final int LIMIT = 10000, N = 100;
	@SuppressWarnings("serial") static final List<Integer> TRI = new ArrayList<Integer>(N){
		{ add(-1); for (int i = 0; i < N; ++i) add((i+1)*(i+2)/2); }
	};
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt(), s = 0;
			if (n == 0) break;
			List<Integer> l = new ArrayList<Integer>(n);
			for (int i = 0; i < n; ++i) {
				l.add(stdin.nextInt());
				s += l.get(i);
			}
			int i = -1;
			if (TRI.contains(s)) {
				for (i = 0; i <= LIMIT &&  !check(l); ++i) {
					int sz = l.size();
					for (int j = 0; j < sz; ++j) l.set(j, l.get(j)-1);
					int idx = -1;
					while((idx = l.indexOf(0)) != -1) l.remove(idx);
					l.add(sz);
				}
			}
			System.out.println(i > LIMIT ? -1 : i);
		}
	}
	static boolean check(List<Integer> l) {
		for (int i = 0; i < l.size(); ++i) if (l.get(i) != i+1) return false;
		return true;
	}
}
