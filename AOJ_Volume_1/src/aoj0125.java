import java.util.*;
public class aoj0125 {
	static final Scanner stdin = new Scanner(System.in);
	public static void main(String[] args) {
		while(true){
			int y1 = stdin.nextInt(), m1 = stdin.nextInt(), d1 = stdin.nextInt();
			int y2 = stdin.nextInt(), m2 = stdin.nextInt(), d2 = stdin.nextInt();
			if(y1 == -1) break;
			System.out.println(GregToJD(y2, m2, d2, true) - GregToJD(y1, m1, d1, true));
		}
	}
	public static final int GregToJD(int y, int m, int d, boolean modflag){ // Ver. 2
		int a  = (14-m)/12;
		y = y + 4800 - a;
		m = m + 12*a -3;
		return d + (153*m+2)/5 + 365 * y + y/4 - y/100 + y/400 - 32045 - (modflag ?  2400001 : 0);
	}
}
