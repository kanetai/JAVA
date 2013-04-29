import java.util.*;
public class aoj0105 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		TreeMap<String, TreeSet<Integer>> m = new TreeMap<String, TreeSet<Integer>>();
		while(stdin.hasNext()){
			String w = stdin.next();
			int n = stdin.nextInt();
			if(m.containsKey(w)) m.get(w).add(n);
			else{
				TreeSet<Integer> s = new TreeSet<Integer>();
				s.add(n);
				m.put(w, s);
			}
		}
		for(String w: m.keySet()){
			System.out.println(w);
			StringBuilder o = new StringBuilder();
			for(int i: m.get(w)) o.append(' ').append(i);
			System.out.println(o.substring(1));
		}
	}
}
