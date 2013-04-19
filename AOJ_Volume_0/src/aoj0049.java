import java.util.*;
public class aoj0049 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		@SuppressWarnings("serial") HashMap<String, Integer> freq = new HashMap<String, Integer>(){
			{ put("A", 0); put("B", 0); put("AB", 0); put("O", 0); }
		};
		while(stdin.hasNext()){
			String b = stdin.nextLine().split(",")[1];
			freq.put(b, freq.get(b)+1);
		}
		System.out.println(freq.get("A"));
		System.out.println(freq.get("B"));
		System.out.println(freq.get("AB"));
		System.out.println(freq.get("O"));
	}
}
