import java.util.*;
public class aoj0093 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		boolean ini = true;
		while(true){
			int a = stdin.nextInt(), b = stdin.nextInt();
			if(a==0 && b==0) break;
			if(!ini) System.out.println("");
			ini = false;
			boolean flag = false;
			for(int y = a; y <= b; ++y)
				if(isLeapYear(y)){
					System.out.println(y);
					flag = true;
				}
			if(!flag) System.out.println("NA");
		}
	}
	public static final boolean isLeapYear(int y){
		return y % 4 == 0 && y % 100 != 0 || y % 400 == 0;
	}
}
