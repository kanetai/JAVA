import java.util.*;
public class aoj0064 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		int ans = 0;
		while(stdin.hasNext()){
			char[] line = stdin.nextLine().toCharArray();
			int temp = 0;
			for(char ch: line){
				if(Character.isDigit(ch)){
					temp = 10*temp + ch-'0';
				}else{
					ans += temp;
					temp = 0;
				}
			}
			ans += temp;
		}
		System.out.println(ans);
	}
}
