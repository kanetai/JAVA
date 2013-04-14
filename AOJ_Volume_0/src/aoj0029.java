import java.util.*;
public class aoj0029 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		HashMap<String,Integer> t = new HashMap<String,Integer>();
		int max_len = 0, max_freq=0;
		String longstr="", mode="";
		while(stdin.hasNext()){
			String k = stdin.next();
			t.put(k, t.containsKey(k) ? t.get(k) + 1 : 1);
			if(max_len < k.length()){ max_len = k.length(); longstr = k; }
			if(max_freq < t.get(k)){ max_freq = t.get(k); mode = k; }
		}
		System.out.println(mode+" "+longstr);
	}
}
