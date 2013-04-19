import java.util.*;
public class aoj0050 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			StringBuilder res = new StringBuilder();
			String[] line = stdin.nextLine().split(" ");
			for(String s: line){
				if(s.indexOf("peach") >= 0) s = s.replace("peach", "apple");
				else if(s.indexOf("apple") >= 0 ) s = s.replace("apple", "peach");
				res.append(' '); res.append(s);
			}
			System.out.println(res.substring(1));
		}
	}
}
