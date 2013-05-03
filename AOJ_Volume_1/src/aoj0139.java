import java.util.*;
import java.util.regex.*;
public class aoj0139 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		Pattern A = Pattern.compile(">\'(=+)#\\1~");
		Pattern B = Pattern.compile(">\\^(Q=)+~~");
		int n = stdin.nextInt();
		while(n-- > 0){
			String s = stdin.next();
			Matcher AM = A.matcher(s), BM = B.matcher(s);
			System.out.println(
					AM.find() && AM.group().equals(s) ? "A" : 
					BM.find() && BM.group().equals(s) ? "B" : "NA");
		}
	}
}
