import java.util.*;
public class aoj0074 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int h = stdin.nextInt(), m = stdin.nextInt(), s = stdin.nextInt();
			if(h == -1 && m == -1 && s == -1) break;
			int diff = 7200 - (h*3600 + m*60 + s), temp = diff;
			h = temp/3600; temp %= 3600; m = temp/60; s = temp % 60;
			System.out.printf("%02d:%02d:%02d\n", h,m,s);
			temp = diff*3;
			h = temp/3600; temp %= 3600; m = temp/60; s = temp % 60;
			System.out.printf("%02d:%02d:%02d\n", h,m,s);
		}
	}
}
