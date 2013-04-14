import java.util.*;
public class aoj0028 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
		int max = 0;
		while(stdin.hasNext()){
			int k = stdin.nextInt();
			t.put(k, t.containsKey(k) ? t.get(k)+1 : 1);
			max = Math.max(max, t.get(k));
		}
		for(Integer k: t.keySet()) if( t.get(k) == max ) System.out.println(k);
	}
}
