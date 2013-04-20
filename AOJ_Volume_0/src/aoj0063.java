import java.util.*;
public class aoj0063 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int ans = 0;
		while(stdin.hasNext()){
			char[] str = stdin.nextLine().toCharArray();
			boolean flag = true;
			for(int i = 0; i < str.length/2; ++i)
				if(str[i] != str[str.length-i-1]) flag = false;
			if(flag) ans++;
		}
		System.out.println(ans);
	}
}
