import java.util.*;
public class aoj0242 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while (true) {
			int n = stdin.nextInt();
			if (n == 0) break;
			stdin.nextLine();
			Map<String, Integer> freq = new HashMap<String, Integer>();
			while (n-- > 0) for (String s : stdin.nextLine().split(" ")) freq.put(s, (freq.containsKey(s) ? freq.get(s) : 0) + 1);
			char ch = stdin.next().charAt(0);
			List<T> l = new ArrayList<T>();
			for (Map.Entry<String, Integer> e : freq.entrySet()) if (e.getKey().charAt(0) == ch) l.add(new T(e.getKey(), e.getValue()));
			StringBuilder sb = new StringBuilder();
			if (l.isEmpty()) sb.append(" NA");
			else Collections.sort(l);
			for (T t : l) sb.append(" ").append(t.str);
			System.out.println(sb.substring(1));
		}
	}
	static class T implements Comparable<T>{
		String str; int freq;
		T(String s, int f) { str = s; freq = f; }
		@Override public int compareTo(T o) { return freq != o.freq ? o.freq - freq : str.compareTo(o.str); }
	}
}
