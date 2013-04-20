import java.util.*;
public class aoj0062 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(stdin.hasNext()){
			char[] a = stdin.nextLine().toCharArray();
			for(int i =  1; i < a.length; ++i)
				for(int j = 0; j <= a.length - i - 1; ++j)
					a[j] = (char)((a[j]-'0'+a[j+1]-'0')%10 + '0');
			System.out.println(a[0]);
		}
	}
}
